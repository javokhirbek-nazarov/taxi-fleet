package booking.task.taxi_fleet.taxi.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Taxi {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "number", nullable = false)
    private String number;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private TaxiStatus status;

    @Column(name = "lng", nullable = false)
    private Double lng;

    @Column(name = "lat", nullable = false)
    private Double lat;

}
