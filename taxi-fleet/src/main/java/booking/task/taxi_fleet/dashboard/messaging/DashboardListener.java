package booking.task.taxi_fleet.dashboard.messaging;

import booking.task.taxi_fleet.booking.model.BookingDto;
import booking.task.taxi_fleet.config.rabbitmq.RabbitMqInitConfig;
import booking.task.taxi_fleet.dashboard.service.DashboardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DashboardListener {

    private final DashboardService dashboardService;

    @RabbitListener(queues = {RabbitMqInitConfig.DASHBOARD_NEW_BOOKING_QUEUE})
    public void onNewBooking(BookingDto booking) {
        log.info("New booking created with id = {}", booking.getId());
        dashboardService.notifyClients();
    }

}
