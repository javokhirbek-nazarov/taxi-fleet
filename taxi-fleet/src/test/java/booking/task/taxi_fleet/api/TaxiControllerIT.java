package booking.task.taxi_fleet.api;

import booking.task.taxi_fleet.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
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

    @Test
    public void getAll_shouldReturnList() {

    }

}