package booking.task.taxi_fleet.service;

import booking.task.taxi_fleet.domain.Booking;
import booking.task.taxi_fleet.domain.BookingState;
import booking.task.taxi_fleet.model.BookingDto;
import booking.task.taxi_fleet.model.mapper.BookingMapper;
import booking.task.taxi_fleet.model.request.BookingCreateDto;
import booking.task.taxi_fleet.repository.BookingRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;

    public List<BookingDto> getAll() {
        return bookingRepository.findAll().stream()
            .map(bookingMapper::mapEntityToDto)
            .collect(Collectors.toList());
    }

    public BookingDto create(BookingCreateDto body) {
        if (body.getClient() == null || body.getClient().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Client name is required");
        }
        if (body.getAddress() == null || body.getAddress().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pickup address is required");
        }
        Booking booking = new Booking();
        booking.setClient(body.getClient());
        booking.setState(BookingState.NEW);
        booking.setAddress(body.getAddress());
        booking.setCreatedAt(LocalDateTime.now());
        bookingRepository.save(booking);
        //TODO: publish new booking event
        return bookingMapper.mapEntityToDto(booking);
    }
}
