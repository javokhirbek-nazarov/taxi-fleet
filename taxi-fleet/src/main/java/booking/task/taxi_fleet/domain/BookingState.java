package booking.task.taxi_fleet.domain;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum BookingState {

    NEW("NEW"),
    TAKEN("TAKEN");

    @JsonValue
    private final String code;

    BookingState(String code) {
        this.code = code;
    }
}
