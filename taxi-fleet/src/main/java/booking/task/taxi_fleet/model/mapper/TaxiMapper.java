package booking.task.taxi_fleet.model.mapper;

import booking.task.taxi_fleet.domain.Taxi;
import booking.task.taxi_fleet.model.LocationDto;
import booking.task.taxi_fleet.model.TaxiDto;
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
