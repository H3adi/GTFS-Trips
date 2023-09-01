import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Uporaba: busTrips <id postaje> <število naslednjih avtobusov> <relative|absolute>");
            return;
        }

        String stationId = args[0];
        int numTrips = Integer.parseInt(args[1]);
        String timeFormat = args[2];

        List<Calendar> calendars = new ArrayList<>();
        List<Route> routes = new ArrayList<>();
        List<Stop> stops = new ArrayList<>();
        List<StopTime> stopTimes = new ArrayList<>();
        List<Trip> trips = new ArrayList<>();

        File dir = new File("Trips");
        File[] files = dir.listFiles();

        for (File file : files) {
            if (file.isFile()) {
                BufferedReader reader;
                String delimiter = ",";
                String line;
                try {
                    reader = new BufferedReader(new FileReader(file));
                    reader.readLine(); // skip first line

                    switch (file.getName()) {
                        case "calendar.txt":
                            while ((line = reader.readLine()) != null) {
                                String[] data = line.split(delimiter, -1);
                                String serviceId = data[0];
                                String monday = data[1];
                                String tuesday = data[2];
                                String wednesday = data[3];
                                String thursday = data[4];
                                String friday = data[5];
                                String saturday = data[6];
                                String sunday = data[7];
                                String startDateString = data[8];
                                String endDateString = data[9];

                                Calendar calendar = new Calendar(serviceId, startDateString, endDateString, monday, tuesday, wednesday, thursday, friday, saturday, sunday);
                                calendars.add(calendar);
                            }
                            break;
                        case "routes.txt":
                            while ((line = reader.readLine()) != null) {
                                String[] data = line.split(delimiter, -1);
                                String routeId = data[0];
                                String agencyId = data[1];
                                String routeShortName = data[2];
                                String routeLongName = data[3];
                                String routeDesc = data[4];
                                String routeType = data[5];
                                String routeUrl = data[6];
                                String routeColor = data[7];
                                String routeTextColor = data[8];

                                Route route = new Route(routeId, agencyId, routeShortName, routeLongName, routeDesc, routeType, routeUrl, routeColor, routeTextColor);
                                routes.add(route);
                            }
                            break;
                        case "stops.txt":
                            while ((line = reader.readLine()) != null) {
                                String[] data = line.split(delimiter, -1);
                                String stopId = data[0];
                                String stopCode = data[1];
                                String stopName = data[2];
                                String stopDesc = data[3];
                                String stopLat = data[4];
                                String stopLon = data[5];
                                String zoneId = data[6];
                                String stopUrl = data[7];
                                String locationType = data[8];
                                String parentStation = data[9];
                                String stopTimezone = data[10];
                                String wheelchairBoarding = data[11];

                                Stop stop = new Stop(stopId, stopCode, stopName, stopDesc, stopLat, stopLon, zoneId, stopUrl, locationType, parentStation, stopTimezone, wheelchairBoarding);
                                stops.add(stop);
                            }
                            break;
                        case "stop_times.txt":
                            while ((line = reader.readLine()) != null) {
                                String[] data = line.split(delimiter, -1);
                                String trip_id = data[0];
                                String arrival_time = data[1];
                                String departure_time = data[2];
                                String stop_id = data[3];
                                String stop_sequence = data[4];
                                String stop_headsign = data[5];
                                String pickup_type = data[6];
                                String drop_off_type = data[7];
                                String shape_dist_traveled = data[8];
                                String timepoint = data[9];

                                StopTime stopTime = new StopTime(trip_id, arrival_time, departure_time, stop_id, stop_sequence, stop_headsign, pickup_type, drop_off_type, shape_dist_traveled, timepoint);
                                stopTimes.add(stopTime);
                            }
                            break;
                        case "trips.txt":
                            while ((line = reader.readLine()) != null) {
                                String[] data = line.split(delimiter, -1);
                                String route_id = data[0];
                                String service_id = data[1];
                                String trip_id = data[2];
                                String trip_headsign = data[3];
                                String trip_short_name = data[4];
                                String direction_id = data[5];
                                String block_id = data[6];
                                String shape_id = data[7];
                                String wheelchair_accessible = data[8];
                                String bikes_allowed = data[9];
                                String duty = data[10];
                                String duty_sequence_number = data[11];
                                String run_sequence_number = data[12];

                                Trip trip = new Trip(route_id, service_id, trip_id, trip_headsign, trip_short_name, direction_id, block_id, shape_id, wheelchair_accessible, bikes_allowed, duty, duty_sequence_number, run_sequence_number);
                                trips.add(trip);
                            }
                            break;
                    }
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        LocalTime now = LocalTime.now();
        LocalTime twoHoursFromNow = now.plusHours(2);

        Map<String, List<LocalTime>> routeTimes = new HashMap<>();
        for (Stop stop : stops) {
            for (StopTime stopTime : stopTimes) {
                if (stop.stopId.equals(stopTime.stop_id)) {
                    if (stopTime.arrival_time.isAfter(now) && stopTime.arrival_time.isBefore(twoHoursFromNow) && stopTime.stop_id.equals(stationId)) {
                        for (Trip trip : trips) {
                            if (trip.trip_id.equals(stopTime.trip_id)) {
                                for (Route route : routes) {
                                    if (route.routeId.equals(trip.route_id)) {
                                        String routeShortName = route.routeShortName;
                                        LocalTime arrivalTime = stopTime.arrival_time;
                                        if (!routeTimes.containsKey(routeShortName)) {
                                            routeTimes.put(routeShortName, new ArrayList<>());
                                        }
                                        routeTimes.get(routeShortName).add(arrivalTime);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        String stopName = "";
        for (Stop stop : stops) {
            if (stationId.equals(stop.stopId)) {
                stopName = stop.stopName;
                break;
            }
        }

        System.out.printf("%s %n", stopName);
        for (Map.Entry<String, List<LocalTime>> entry : routeTimes.entrySet()) {
            String routeShortName = entry.getKey();
            List<LocalTime> arrivalTimes = entry.getValue();
            arrivalTimes.sort(LocalTime::compareTo);
            System.out.printf("%s: ", routeShortName);
            int count = 0;
            for (LocalTime arrivalTime : arrivalTimes) {
                if (count >= numTrips) {
                    break;
                }
                if (arrivalTime.isAfter(now)) {
                    Duration duration = Duration.between(now, arrivalTime);
                    long minutes = duration.toMinutes();
                    String timeString;
                    if (timeFormat.equals("absolute")) {
                        timeString = arrivalTime.format(DateTimeFormatter.ofPattern("HH:mm"));
                    } else {
                        timeString = String.format("%dmin", minutes);
                    }
                    System.out.printf("%s", timeString);
                    count++;
                    if (count < numTrips && count < arrivalTimes.size()) {
                        System.out.print(", ");
                    }
                }
            }
            System.out.println();
        }
    }
}