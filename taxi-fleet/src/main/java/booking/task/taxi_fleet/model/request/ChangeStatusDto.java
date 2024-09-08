package booking.task.taxi_fleet.model.request;

import booking.task.taxi_fleet.domain.TaxiStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangeStatusDto {

    private TaxiStatus status;

}
