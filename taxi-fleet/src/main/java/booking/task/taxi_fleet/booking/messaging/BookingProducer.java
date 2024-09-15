package booking.task.taxi_fleet.booking.messaging;

import booking.task.taxi_fleet.booking.model.BookingDto;
import booking.task.taxi_fleet.config.rabbitmq.RabbitMqInitConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class BookingProducer {

    private final RabbitTemplate rabbitTemplate;

    public void onNewBooking(BookingDto booking) {
        try {
            rabbitTemplate.convertAndSend(RabbitMqInitConfig.BOOKING_TOPIC_EXCHANGE,
                RabbitMqInitConfig.NEW_BOOKING_ROUTING_KEY, booking);
        } catch (Exception ex) {
            log.error("Could not send new booking message", ex);
        }
    }

}
