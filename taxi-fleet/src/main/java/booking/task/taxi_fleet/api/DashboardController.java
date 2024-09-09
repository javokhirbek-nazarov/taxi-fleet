package booking.task.taxi_fleet.api;

import booking.task.taxi_fleet.model.DashboardStatisticsDto;
import booking.task.taxi_fleet.service.DashboardService;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    private static final int DEFAULT_TIMEOUT = 60; //seconds

    @GetMapping("/dashboard")
    public CompletableFuture<ResponseEntity<DashboardStatisticsDto>> getDashboard(
        @RequestParam String clientId, @RequestParam(defaultValue = "false") boolean immediate) {

        CompletableFuture<DashboardStatisticsDto> futureData = dashboardService.getDashboardInfo(
            clientId);
        if (immediate) {
            dashboardService.notifyClients(clientId);
        }
        return futureData.orTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .handle((data, ex) -> {
                if (ex != null) {
                    return ResponseEntity.ok(new DashboardStatisticsDto());
                }
                return ResponseEntity.ok(data);
            });
    }
}

