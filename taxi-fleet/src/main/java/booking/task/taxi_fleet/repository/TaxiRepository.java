package booking.task.taxi_fleet.repository;

import booking.task.taxi_fleet.domain.Taxi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxiRepository extends JpaRepository<Taxi, Long> {

}
