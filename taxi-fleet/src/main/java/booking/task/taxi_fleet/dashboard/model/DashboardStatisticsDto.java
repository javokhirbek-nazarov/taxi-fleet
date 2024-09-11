package booking.task.taxi_fleet.dashboard.model;

import booking.task.taxi_fleet.booking.model.BookingStatisticsDto;
import booking.task.taxi_fleet.taxi.model.TaxiStatisticsDto;
import lombok.Getter;

@Getter
public class DashboardStatisticsDto {

    private final TaxiStatisticsDto taxiStatistics;
    private final BookingStatisticsDto bookingStatistics;

    public DashboardStatisticsDto() {
        this(null, null);
    }

    public DashboardStatisticsDto(TaxiStatisticsDto taxiStatistics,
        BookingStatisticsDto bookingStatistics) {
        this.taxiStatistics = taxiStatistics;
        this.bookingStatistics = bookingStatistics;
    }
}
