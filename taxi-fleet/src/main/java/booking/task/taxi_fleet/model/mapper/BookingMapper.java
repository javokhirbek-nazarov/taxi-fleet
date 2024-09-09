package booking.task.taxi_fleet.model.mapper;

import booking.task.taxi_fleet.domain.Booking;
import booking.task.taxi_fleet.model.BookingDto;
import org.springframework.stereotype.Component;

@Component
public class BookingMapper {

    public BookingDto mapEntityToDto(Booking booking) {
        BookingDto dto = new BookingDto();
        dto.setId(booking.getId());
        dto.setClient(booking.getClient());
        dto.setState(booking.getState());
        dto.setAddress(booking.getAddress());
        dto.setCreatedAt(booking.getCreatedAt());
        return dto;
    }
}
