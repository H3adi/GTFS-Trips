public class Trip {
    String route_id;
    String service_id;
    String trip_id;
    String trip_headsign;
    String trip_short_name;
    String direction_id;
    String block_id;
    String shape_id;
    String wheelchair_accessible;
    String bikes_allowed;
    String duty;
    String duty_sequence_number;
    String run_sequence_number;

    public Trip(String route_id, String service_id, String trip_id, String trip_headsign, String trip_short_name, String direction_id, String block_id, String shape_id, String wheelchair_accessible, String bikes_allowed, String duty, String duty_sequence_number, String run_sequence_number) {
        this.route_id = route_id;
        this.service_id = service_id;
        this.trip_id = trip_id;
        this.trip_headsign = trip_headsign;
        this.trip_short_name = trip_short_name;
        this.direction_id = direction_id;
        this.block_id = block_id;
        this.shape_id = shape_id;
        this.wheelchair_accessible = wheelchair_accessible;
        this.bikes_allowed = bikes_allowed;
        this.duty = duty;
        this.duty_sequence_number = duty_sequence_number;
        this.run_sequence_number = run_sequence_number;
    }
}
