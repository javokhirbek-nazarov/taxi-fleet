package booking.task.taxi_fleet.taxi.service;

import booking.task.taxi_fleet.taxi.domain.TaxiStatus;
import booking.task.taxi_fleet.taxi.model.TaxiDto;
import booking.task.taxi_fleet.taxi.model.TaxiStatisticsDto;
import booking.task.taxi_fleet.taxi.model.mapper.TaxiMapper;
import booking.task.taxi_fleet.taxi.repository.TaxiRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class TaxiService {

    private final TaxiRepository taxiRepository;
    private final TaxiMapper taxiMapper;

    public List<TaxiDto> getAll() {
        return taxiRepository.findAll().stream()
            .map(taxiMapper::mapEntityToDto)
            .collect(Collectors.toList());
    }

    @Transactional
    public TaxiDto changeStatus(Long id, TaxiStatus status) {
        var taxi = taxiRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        taxi.setStatus(status);
        taxiRepository.save(taxi);
        //TODO publish a message to rabbitmq
        return taxiMapper.mapEntityToDto(taxi);
    }

    public TaxiStatisticsDto getStatistics() {
        var taxis = taxiRepository.findAll();
        long availableCount = taxis.stream()
            .filter(t -> TaxiStatus.AVAILABLE.equals(t.getStatus()))
            .count();

        long bookedCount = taxis.stream()
            .filter(t -> TaxiStatus.BOOKED.equals(t.getStatus()))
            .count();

        return new TaxiStatisticsDto.Builder()
            .availableCount(availableCount)
            .bookedCount(bookedCount)
            .totalCount(taxis.size())
            .build();
    }

}
