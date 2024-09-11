package booking.task.taxi_fleet.taxi.model;

import booking.task.taxi_fleet.taxi.domain.TaxiStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaxiDto {

    private Long id;

    private String number;

    private TaxiStatus status;

    private LocationDto location;

}
