package booking.task.taxi_fleet.taxi.model;

import lombok.Getter;

@Getter
public class TaxiStatisticsDto {

    private final long availableCount;
    private final long bookedCount;
    private final long totalCount;

    private TaxiStatisticsDto(Builder builder) {
        this.availableCount = builder.availableCount;
        this.bookedCount = builder.bookedCount;
        this.totalCount = builder.totalCount;
    }

    public static class Builder {

        private long availableCount;
        private long bookedCount;
        private long totalCount;

        public Builder availableCount(long availableCount) {
            this.availableCount = availableCount;
            return this;
        }

        public Builder bookedCount(long bookedCount) {
            this.bookedCount = bookedCount;
            return this;
        }

        public Builder totalCount(long totalCount) {
            this.totalCount = totalCount;
            return this;
        }

        public TaxiStatisticsDto build() {
            return new TaxiStatisticsDto(this);
        }
    }
}
