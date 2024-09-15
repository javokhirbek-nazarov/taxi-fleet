package booking.task.taxi_fleet.taxi.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import booking.task.taxi_fleet.Utils;
import booking.task.taxi_fleet.taxi.domain.Taxi;
import booking.task.taxi_fleet.taxi.domain.TaxiStatus;
import booking.task.taxi_fleet.taxi.messaging.TaxiProducer;
import booking.task.taxi_fleet.taxi.model.mapper.TaxiMapper;
import booking.task.taxi_fleet.taxi.repository.TaxiRepository;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TaxiServiceTest {

    @InjectMocks
    private TaxiService taxiService;

    @Mock
    private TaxiRepository taxiRepository;

    @Mock
    private TaxiProducer taxiProducer;

    @Spy
    private TaxiMapper taxiMapper;

    @Test
    void changeStatus_validDataTaxiExists() {
        Long id = 1L;
        when(taxiRepository.findById(id))
            .thenReturn(Optional.of(Utils.getEntity(id, "TST12", TaxiStatus.AVAILABLE, 1.1, 1.1)));

        var result = taxiService.changeStatus(id, TaxiStatus.BOOKED);

        Assertions.assertEquals(TaxiStatus.BOOKED, result.getStatus());
    }

    @Test
    void getAll() {
        var mockResult = List.of(
            Utils.getEntity(1L, "TST11", TaxiStatus.AVAILABLE, 12.34, 34.56),
            Utils.getEntity(2L, "TST22", TaxiStatus.BOOKED, 72.34, 74.56)
        );
        when(taxiRepository.findAll()).thenReturn(mockResult);

        var result = taxiService.getAll();

        verify(taxiMapper, times(2)).mapEntityToDto(any());
        Assertions.assertEquals(2, result.size());
    }

    @Test
    void getStatistics_whenBothBookedAndAvailableTaxisExist() {
        var mockResult = List.of(
            Utils.getEntity(1L, "TST11", TaxiStatus.AVAILABLE, 12.34, 14.56),
            Utils.getEntity(2L, "TST22", TaxiStatus.AVAILABLE, 22.34, 24.56),
            Utils.getEntity(3L, "TST33", TaxiStatus.BOOKED, 32.34, 34.56),
            Utils.getEntity(4L, "TST44", TaxiStatus.BOOKED, 42.34, 44.56),
            Utils.getEntity(5L, "TST55", TaxiStatus.BOOKED, 52.34, 54.56)
        );
        when(taxiRepository.findAll()).thenReturn(mockResult);

        var statistics = taxiService.getStatistics();

        Assertions.assertEquals(2, statistics.getAvailableCount());
        Assertions.assertEquals(3, statistics.getBookedCount());
        Assertions.assertEquals(5, statistics.getTotalCount());
    }

    @Test
    void getStatistics_whenBothBookedAndAvailableTaxisDoNotExist() {
        List<Taxi> mockResult = Collections.emptyList();
        when(taxiRepository.findAll()).thenReturn(mockResult);

        var statistics = taxiService.getStatistics();

        Assertions.assertEquals(0, statistics.getAvailableCount());
        Assertions.assertEquals(0, statistics.getBookedCount());
        Assertions.assertEquals(0, statistics.getTotalCount());
    }

}