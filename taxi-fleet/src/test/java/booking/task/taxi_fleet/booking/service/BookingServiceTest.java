package booking.task.taxi_fleet.booking.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import booking.task.taxi_fleet.Utils;
import booking.task.taxi_fleet.booking.domain.BookingState;
import booking.task.taxi_fleet.booking.messaging.BookingProducer;
import booking.task.taxi_fleet.booking.model.mapper.BookingMapper;
import booking.task.taxi_fleet.booking.repository.BookingRepository;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

@ExtendWith(MockitoExtension.class)
public class BookingServiceTest {

    @InjectMocks
    private BookingService bookingService;
    @Mock
    private BookingRepository bookingRepository;
    @Spy
    private BookingMapper bookingMapper;
    @Mock
    private BookingProducer bookingProducer;

    @Test
    void create_savesSuccessfullyWithValidData() {
        bookingService.create(Utils.getCreateDto("John", "Address"));
        verify(bookingRepository).save(any());
        verify(bookingMapper).mapEntityToDto(any());
        verify(bookingProducer).onNewBooking(any());
    }

    @Test
    void create_throwsWithInvalidClient() {
        Assertions.assertThrows(ResponseStatusException.class,
            () -> bookingService.create(Utils.getCreateDto("", "Address")));
    }

    @Test
    void create_throwsWithInvalidAddress() {
        Assertions.assertThrows(ResponseStatusException.class,
            () -> bookingService.create(Utils.getCreateDto("John Doe", null)));
    }

    @Test
    void getStatistics_withBothNewAndTakenBookingsExisting() {
        var mockBookings = List.of(
            Utils.getEntity(1L, "Test Name1", "Address 1", BookingState.NEW),
            Utils.getEntity(2L, "Test Name2", "Address 2", BookingState.NEW),
            Utils.getEntity(3L, "Test Name3", "Address 3", BookingState.NEW),
            Utils.getEntity(4L, "Test Name4", "Address 4", BookingState.NEW),
            Utils.getEntity(5L, "Test Name5", "Address 5", BookingState.NEW),
            Utils.getEntity(6L, "Test Name6", "Address 6", BookingState.TAKEN)
        );
        when(bookingRepository.findAll()).thenReturn(mockBookings);

        var statistics = bookingService.getStatistics();

        Assertions.assertEquals(5, statistics.getNewCount());
        Assertions.assertEquals(1, statistics.getTakenCount());
        Assertions.assertEquals(6, statistics.getTotalCount());
    }

}