package booking.task.taxi_fleet.taxi.model.mapper;

import booking.task.taxi_fleet.taxi.domain.Taxi;
import booking.task.taxi_fleet.taxi.model.LocationDto;
import booking.task.taxi_fleet.taxi.model.TaxiDto;
import org.springframework.stereotype.Component;

@Component
public class TaxiMapper {

    public TaxiDto mapEntityToDto(Taxi taxi) {
        TaxiDto dto = new TaxiDto();
        dto.setId(taxi.getId());
        dto.setNumber(taxi.getNumber());
        dto.setStatus(taxi.getStatus());
        dto.setLocation(new LocationDto(taxi.getLng(), taxi.getLat()));
        return dto;
    }

}
