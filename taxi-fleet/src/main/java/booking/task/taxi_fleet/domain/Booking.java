package booking.task.taxi_fleet.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class Booking{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String client;

    @Column(name = "state", nullable = false)
    @Enumerated(EnumType.STRING)
    private BookingState state;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "taxi_id", nullable = true)
    private Taxi taxi;
}
