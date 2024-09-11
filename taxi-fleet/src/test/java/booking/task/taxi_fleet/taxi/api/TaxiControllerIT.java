package booking.task.taxi_fleet.taxi.api;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import booking.task.taxi_fleet.Constants;
import booking.task.taxi_fleet.Utils;
import booking.task.taxi_fleet.taxi.domain.TaxiStatus;
import booking.task.taxi_fleet.taxi.model.ChangeStatusDto;
import booking.task.taxi_fleet.taxi.repository.TaxiRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
class TaxiControllerIT {

    @ServiceConnection
    @Container
    public static PostgreSQLContainer<?> db = new PostgreSQLContainer<>(Constants.POSTGRES_IMAGE);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TaxiRepository taxiRepository;

    @BeforeEach
    void setUp(){
        taxiRepository.deleteAll();
    }

    @Test
    public void getAll_shouldReturnTaxiList() throws Exception {
        taxiRepository.saveAll(List.of(
            Utils.getEntity(1L, "TST11", TaxiStatus.AVAILABLE, 12.34, 34.56),
            Utils.getEntity(2L, "TST22", TaxiStatus.BOOKED, 72.34, 74.56)
        ));

        mockMvc.perform(MockMvcRequestBuilders.get("/taxis")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()").value(2))
            .andExpect(jsonPath("$[0].id").exists())
            .andExpect(jsonPath("$[1].id").exists());
    }

    @Test
    public void changeStatus_shouldUpdateTaxiStatusForExistingTaxi() throws Exception {
        var taxi = taxiRepository.save(Utils.getEntity(null, "TST11", TaxiStatus.AVAILABLE, 12.34, 34.56));
        ChangeStatusDto body = Utils.getChangeStatusDto(TaxiStatus.BOOKED);

        mockMvc.perform(MockMvcRequestBuilders.put("/taxis/" + taxi.getId() + "/status")
                .content(objectMapper.writeValueAsString(body))
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(
                jsonPath("$.id").value(taxi.getId()))
            .andExpect(jsonPath("$.status").value("BOOKED"));

        var updatedTaxi = taxiRepository.findById(taxi.getId());
        Assertions.assertTrue(updatedTaxi.isPresent());
        Assertions.assertEquals(TaxiStatus.BOOKED, updatedTaxi.get().getStatus());
    }

    @Test
    public void changeStatus_shouldThrow404ForNonExistingTaxi() throws Exception {
        Long id = 1L;
        ChangeStatusDto body = Utils.getChangeStatusDto(TaxiStatus.BOOKED);

        mockMvc.perform(MockMvcRequestBuilders.put("/taxis/" + id + "/status")
                .content(objectMapper.writeValueAsString(body))
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());

        Assertions.assertTrue(taxiRepository.findById(id).isEmpty());
    }

}