package booking.task.taxi_fleet.model;

import lombok.Getter;

@Getter
public class BookingStatisticsDto {

    private final long newCount;
    private final long takenCount;
    private final long totalCount;

    private BookingStatisticsDto(Builder builder) {
        this.newCount = builder.newCount;
        this.takenCount = builder.takenCount;
        this.totalCount = builder.totalCount;
    }

    public static class Builder {

        private long newCount;
        private long takenCount;
        private long totalCount;

        public Builder newCount(long newCount) {
            this.newCount = newCount;
            return this;
        }

        public Builder takenCount(long takenCount) {
            this.takenCount = takenCount;
            return this;
        }

        public Builder totalCount(long totalCount) {
            this.totalCount = totalCount;
            return this;
        }

        public BookingStatisticsDto build() {
            return new BookingStatisticsDto(this);
        }
    }
}

