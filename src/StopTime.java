import java.time.LocalTime;

public class StopTime {
    String trip_id;
    LocalTime arrival_time;
    LocalTime departure_time;
    String stop_id;
    String stop_sequence;
    String stop_headsign;
    String pickup_type;
    String drop_off_type;
    String shape_dist_traveled;
    String timepoint;

    public StopTime(String trip_id, String arrival_time, String departure_time, String stop_id, String stop_sequence, String stop_headsign, String pickup_type, String drop_off_type, String shape_dist_traveled, String timepoint) {
        this.trip_id = trip_id;
        this.arrival_time = LocalTime.parse(arrival_time);
        this.departure_time = LocalTime.parse(departure_time);
        this.stop_id = stop_id;
        this.stop_sequence = stop_sequence;
        this.stop_headsign = stop_headsign;
        this.pickup_type = pickup_type;
        this.drop_off_type = drop_off_type;
        this.shape_dist_traveled = shape_dist_traveled;
        this.timepoint = timepoint;
    }
}
