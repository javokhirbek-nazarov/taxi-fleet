package booking.task.taxi_fleet.model;

import booking.task.taxi_fleet.domain.BookingState;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingDto {

    private Long id;

    private String client;

    private BookingState state;

    private String address;

    private LocalDateTime createdAt;

}
