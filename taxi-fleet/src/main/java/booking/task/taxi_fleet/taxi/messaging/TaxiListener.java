package booking.task.taxi_fleet.taxi.messaging;

import booking.task.taxi_fleet.booking.model.BookingDto;
import booking.task.taxi_fleet.config.rabbitmq.RabbitMqInitConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TaxiListener {

    @RabbitListener(queues = {RabbitMqInitConfig.NEW_BOOKING_QUEUE})
    public void onNewBooking(BookingDto booking) {
        // Here we can implement any logic to deliver new bookings to be available taxis
    }

}
