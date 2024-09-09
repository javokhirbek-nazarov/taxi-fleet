package booking.task.taxi_fleet.config.rabbitmq;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqInitConfig {

    public static final String TOPIC_EXCHANGE = "booking.topic-exchange";
    public static final String NEW_BOOKING_QUEUE = "taxi.new-booking-queue";
    public static final String NEW_BOOKING_TO_TAXIS_BINDING = "booking.new-booking";
    public static final String NEW_BOOKING_ROUTING_KEY = "booking.new-booking";

    @Bean
    TopicExchange bookingTopicExchange() {
        var exchange = new TopicExchange(TOPIC_EXCHANGE, false, false);
        exchange.setShouldDeclare(true);
        return exchange;
    }

    @Bean
    Queue newBookingQueue() {
        return new Queue(NEW_BOOKING_QUEUE, true, false, false);
    }

    @Bean
    Binding DLQBinding(Queue newBookingQueue, TopicExchange bookingTopicExchange) {
        return BindingBuilder.bind(newBookingQueue)
            .to(bookingTopicExchange)
            .with(NEW_BOOKING_TO_TAXIS_BINDING);
    }
}
