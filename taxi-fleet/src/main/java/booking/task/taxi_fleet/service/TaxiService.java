package booking.task.taxi_fleet.service;

import booking.task.taxi_fleet.domain.TaxiStatus;
import booking.task.taxi_fleet.model.TaxiDto;
import booking.task.taxi_fleet.model.mapper.TaxiMapper;
import booking.task.taxi_fleet.repository.TaxiRepository;
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
        return taxiMapper.mapEntityToDto(taxi);
    }

}
