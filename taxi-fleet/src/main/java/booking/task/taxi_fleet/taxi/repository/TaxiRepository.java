package booking.task.taxi_fleet.taxi.repository;

import booking.task.taxi_fleet.taxi.domain.Taxi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxiRepository extends JpaRepository<Taxi, Long> {

}
