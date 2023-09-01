import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Calendar {
    String serviceId;
    LocalDate startDateString;
    LocalDate endDateString;
    String monday;
    String tuesday;
    String wednesday;
    String thursday;
    String friday;
    String saturday;
    String sunday;

    public Calendar(String serviceId, String startDateString, String endDateString, String monday, String tuesday, String wednesday, String thursday, String friday, String saturday, String sunday) {
        this.serviceId = serviceId;
        this.startDateString = LocalDate.parse(startDateString, DateTimeFormatter.ofPattern("yyyyMMdd"));
        this.endDateString = LocalDate.parse(endDateString, DateTimeFormatter.ofPattern("yyyyMMdd"));
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
        this.saturday = saturday;
        this.sunday = sunday;
    }
}
