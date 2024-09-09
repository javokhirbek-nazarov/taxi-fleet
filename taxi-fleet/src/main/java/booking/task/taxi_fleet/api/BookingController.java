package booking.task.taxi_fleet.api;

import booking.task.taxi_fleet.model.BookingDto;
import booking.task.taxi_fleet.model.TaxiDto;
import booking.task.taxi_fleet.model.request.BookingCreateDto;
import booking.task.taxi_fleet.service.BookingService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = BookingController.PATH)
@RequiredArgsConstructor
public class BookingController {

    public static final String PATH = "/bookings";

    private final BookingService bookingService;

    @GetMapping
    public ResponseEntity<List<BookingDto>> getAll() {
        return ResponseEntity.ok(bookingService.getAll());
    }

    @PostMapping
    public ResponseEntity<BookingDto> changeStatus(@RequestBody BookingCreateDto body) {
        return ResponseEntity.ok(bookingService.create(body));
    }

}
