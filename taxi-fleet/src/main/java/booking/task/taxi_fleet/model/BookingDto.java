package booking.task.taxi_fleet.model;

import booking.task.taxi_fleet.domain.BookingState;
import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime createdAt;

}
