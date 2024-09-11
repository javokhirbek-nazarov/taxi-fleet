package booking.task.taxi_fleet.dashboard.service;

import booking.task.taxi_fleet.booking.service.BookingService;
import booking.task.taxi_fleet.dashboard.model.DashboardStatisticsDto;
import booking.task.taxi_fleet.taxi.service.TaxiService;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import lombok.RequiredArgsConstructor;
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

    public void notifyClients() {
        var info = getDashboardInfoImmediate();
        for (CompletableFuture<DashboardStatisticsDto> future : clients.values()) {
            if (future != null) {
                future.complete(info);
            }
        }
        clients.clear();
    }

    public void notifyClients(String clientId) {
        var info = getDashboardInfoImmediate();
        var client = clients.remove(clientId);
        client.complete(info);
    }

    public void timeout(String clientId) {
        CompletableFuture<DashboardStatisticsDto> future = clients.remove(clientId);
        if (future != null) {
            future.complete(new DashboardStatisticsDto());
        }
    }

    public DashboardStatisticsDto getDashboardInfoImmediate() {
        return new DashboardStatisticsDto(taxiService.getStatistics(),
            bookingService.getStatistics());
    }
}
