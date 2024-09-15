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
    public static final String NEW_BOOKING_ROUTING_KEY = "booking.new-booking";
    public static final String TAXI_NEW_BOOKING_QUEUE = "taxi.new-booking-queue";
    public static final String NEW_BOOKING_TO_TAXIS_BINDING = "booking.new-booking";
    public static final String DASHBOARD_NEW_BOOKING_QUEUE = "dashboard.new-booking-queue";
    public static final String NEW_BOOKING_TO_DASHBOARD_BINDING = "booking.new-booking";

    @Bean
    TopicExchange bookingTopicExchange() {
        var exchange = new TopicExchange(TOPIC_EXCHANGE, false, false);
        exchange.setShouldDeclare(true);
        return exchange;
    }

    @Bean
    Queue taxiNewBookingQueue() {
        return new Queue(TAXI_NEW_BOOKING_QUEUE, true, false, false);
    }

    @Bean
    Binding newBookingTaxiBinding(Queue taxiNewBookingQueue, TopicExchange bookingTopicExchange) {
        return BindingBuilder.bind(taxiNewBookingQueue)
            .to(bookingTopicExchange)
            .with(NEW_BOOKING_TO_TAXIS_BINDING);
    }

    @Bean
    Queue dashboardNewBooking() {
        return new Queue(DASHBOARD_NEW_BOOKING_QUEUE, true, false, false);
    }

    @Bean
    Binding newBookingDashboardBinding(Queue dashboardNewBooking,
        TopicExchange bookingTopicExchange) {
        return BindingBuilder.bind(dashboardNewBooking)
            .to(bookingTopicExchange)
            .with(NEW_BOOKING_TO_DASHBOARD_BINDING);
    }
}
