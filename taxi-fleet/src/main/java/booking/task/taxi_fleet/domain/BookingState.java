package booking.task.taxi_fleet.domain;

import com.fasterxml.jackson.annotation.JsonValue;

public enum BookingState {

    AVAILABLE("NEW"),
    BOOKED("TAKEN");

    @JsonValue
    private final String code;

    BookingState(String code) {
        this.code = code;
    }
}
