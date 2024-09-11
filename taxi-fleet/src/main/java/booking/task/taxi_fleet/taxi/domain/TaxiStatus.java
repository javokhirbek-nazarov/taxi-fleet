package booking.task.taxi_fleet.taxi.domain;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum TaxiStatus {
    AVAILABLE("AVAILABLE"),
    BOOKED("BOOKED");

    @JsonValue
    private final String code;

    TaxiStatus(String code) {
        this.code = code;
    }
}
