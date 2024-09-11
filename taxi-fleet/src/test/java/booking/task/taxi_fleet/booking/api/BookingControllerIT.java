package booking.task.taxi_fleet.booking.api;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import booking.task.taxi_fleet.Constants;
import booking.task.taxi_fleet.Utils;
import booking.task.taxi_fleet.booking.domain.BookingState;
import booking.task.taxi_fleet.booking.messaging.BookingProducer;
import booking.task.taxi_fleet.booking.model.BookingCreateDto;
import booking.task.taxi_fleet.booking.repository.BookingRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest
@AutoConfigureMockMvc
public class BookingControllerIT {

    @ServiceConnection
    @Container
    public static PostgreSQLContainer<?> db = new PostgreSQLContainer<>(Constants.POSTGRES_IMAGE);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookingRepository bookingRepository;

    @Mock
    private BookingProducer bookingProducer;

    @BeforeEach
    public void setup() {
        bookingRepository.deleteAll();
    }

    @Test
    public void getAll_shouldReturnList() throws Exception {

        bookingRepository.saveAll(List.of(
            Utils.getEntity(1L, "Test Name1", "Address 1", BookingState.NEW),
            Utils.getEntity(2L, "Test Name2", "Address 2", BookingState.TAKEN)
        ));

        mockMvc.perform(MockMvcRequestBuilders.get("/bookings")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()").value(2))
            .andExpect(jsonPath("$[0].id").exists())
            .andExpect(jsonPath("$[1].id").exists());
    }

    @Test
    public void create_shouldCreateBookingWithNewStatus() throws Exception {
        BookingCreateDto body = Utils.getCreateDto("John Doe", "Test address");

        mockMvc.perform(MockMvcRequestBuilders.post("/bookings")
                .content(objectMapper.writeValueAsString(body))
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").exists())
            .andExpect(jsonPath("$.state").value("NEW"));

        var allBookings = bookingRepository.findAll();
        Assertions.assertEquals(1, allBookings.size());
        Assertions.assertEquals(BookingState.NEW, allBookings.get(0).getState());

    }

}