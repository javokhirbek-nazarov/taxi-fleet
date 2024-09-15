package booking.task.taxi_fleet.taxi.messaging;

import booking.task.taxi_fleet.booking.model.BookingDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TaxiListener {

    //TODO: enable if you want to implement some logic here
    //@RabbitListener(queues = {RabbitMqInitConfig.TAXI_NEW_BOOKING_QUEUE})
    public void onNewBooking(BookingDto booking) {
        // Here each taxi gets new message about new booking
    }

}
