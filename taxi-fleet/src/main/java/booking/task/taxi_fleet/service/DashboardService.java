package booking.task.taxi_fleet.service;

import booking.task.taxi_fleet.model.DashboardStatisticsDto;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final TaxiService taxiService;
    private final BookingService bookingService;

    private final ConcurrentHashMap<String, CompletableFuture<DashboardStatisticsDto>> clients = new ConcurrentHashMap<>();

    public CompletableFuture<DashboardStatisticsDto> getDashboardInfo(String clientId) {
        CompletableFuture<DashboardStatisticsDto> future = new CompletableFuture<>();
        clients.put(clientId, future);
        return future;
    }

    public void notify(DashboardStatisticsDto data) {
        for (CompletableFuture<DashboardStatisticsDto> future : clients.values()) {
            if (future != null) {
                future.complete(data);
            }
        }
        clients.clear();
    }

    public void timeout(String clientId) {
        CompletableFuture<DashboardStatisticsDto> future = clients.remove(clientId);
        if (future != null) {
            future.complete(new DashboardStatisticsDto());
        }
    }

    public DashboardStatisticsDto getDashboardInfoImmediate(String clientId) {
        return new DashboardStatisticsDto(taxiService.getStatistics(),
            bookingService.getStatistics());
    }
}
