package booking.task.taxi_fleet.taxi.messaging;

import booking.task.taxi_fleet.config.rabbitmq.RabbitMqInitConfig;
import booking.task.taxi_fleet.taxi.model.TaxiDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TaxiProducer {

    private final RabbitTemplate rabbitTemplate;

    public void onStatusChange(TaxiDto taxi) {
        try {
            rabbitTemplate.convertAndSend(RabbitMqInitConfig.TAXI_TOPIC_EXCHANGE,
                RabbitMqInitConfig.TAXI_STATUS_CHANGE_ROUTING_KEY, taxi);
        } catch (Exception ex) {
            log.error("Could not send taxi status change message", ex);
        }
    }
}
