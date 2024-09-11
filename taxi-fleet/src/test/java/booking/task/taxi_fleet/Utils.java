package booking.task.taxi_fleet;

import booking.task.taxi_fleet.booking.domain.Booking;
import booking.task.taxi_fleet.booking.domain.BookingState;
import booking.task.taxi_fleet.booking.model.BookingCreateDto;
import booking.task.taxi_fleet.taxi.domain.Taxi;
import booking.task.taxi_fleet.taxi.domain.TaxiStatus;
import booking.task.taxi_fleet.taxi.model.ChangeStatusDto;
import java.time.LocalDateTime;

public class Utils {

    public static BookingCreateDto getCreateDto(String client, String address) {
        BookingCreateDto booking = new BookingCreateDto();
        booking.setClient(client);
        booking.setAddress(address);
        return booking;
    }

    public static Booking getEntity(Long id, String client, String address, BookingState state) {
        Booking booking = new Booking();
        booking.setClient(client);
        booking.setAddress(address);
        booking.setState(state);
        booking.setId(id);
        booking.setCreatedAt(LocalDateTime.now());
        return booking;
    }

    public static Taxi getEntity(Long id, String number, TaxiStatus status, Double lng,
        Double lat) {
        Taxi taxi = new Taxi();
        taxi.setId(id);
        taxi.setNumber(number);
        taxi.setStatus(status);
        taxi.setLng(lng);
        taxi.setLat(lat);
        return taxi;
    }

    public static ChangeStatusDto getChangeStatusDto(TaxiStatus status) {
        ChangeStatusDto dto = new ChangeStatusDto();
        dto.setStatus(status);
        return dto;
    }
}
