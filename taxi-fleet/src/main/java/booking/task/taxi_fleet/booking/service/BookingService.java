package booking.task.taxi_fleet.booking.service;

import booking.task.taxi_fleet.booking.domain.Booking;
import booking.task.taxi_fleet.booking.domain.BookingState;
import booking.task.taxi_fleet.booking.messaging.BookingProducer;
import booking.task.taxi_fleet.booking.model.BookingCreateDto;
import booking.task.taxi_fleet.booking.model.BookingDto;
import booking.task.taxi_fleet.booking.model.BookingStatisticsDto;
import booking.task.taxi_fleet.booking.model.mapper.BookingMapper;
import booking.task.taxi_fleet.booking.repository.BookingRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;
    private final BookingProducer bookingProducer;

    public List<BookingDto> getAll() {
        return bookingRepository.findAll().stream()
            .map(bookingMapper::mapEntityToDto)
            .collect(Collectors.toList());
    }

    @Transactional
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
        BookingDto newBooking = bookingMapper.mapEntityToDto(booking);
        bookingProducer.onNewBooking(newBooking);
        return newBooking;
    }

    public BookingStatisticsDto getStatistics() {
        var bookings = bookingRepository.findAll();
        long newCount = bookings.stream()
            .filter(b -> BookingState.NEW.equals(b.getState()))
            .count();

        long takenCount = bookings.stream()
            .filter(t -> BookingState.TAKEN.equals(t.getState()))
            .count();

        return new BookingStatisticsDto.Builder()
            .newCount(newCount)
            .takenCount(takenCount)
            .totalCount(bookings.size())
            .build();
    }
}
