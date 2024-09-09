package booking.task.taxi_fleet.repository;

import booking.task.taxi_fleet.domain.Booking;
import booking.task.taxi_fleet.domain.Taxi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

}
