package booking.task.taxi_fleet.taxi.model;

import booking.task.taxi_fleet.taxi.domain.TaxiStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangeStatusDto {

    private TaxiStatus status;

}
