public class Stop {
    String stopId;
    String stopCode;
    String stopName;
    String stopDesc;
    String stopLat;
    String stopLon;
    String zoneId;
    String stopUrl;
    String locationType;
    String parentStatio;
    String stopTimezone;
    String wheelchairBoarding;

    public Stop(String stopId, String stopCode, String stopName, String stopDesc, String stopLat, String stopLon, String zoneId, String stopUrl, String locationType, String parentStatio, String stopTimezone, String wheelchairBoarding) {
        this.stopId = stopId;
        this.stopCode = stopCode;
        this.stopName = stopName;
        this.stopDesc = stopDesc;
        this.stopLat = stopLat;
        this.stopLon = stopLon;
        this.zoneId = zoneId;
        this.stopUrl = stopUrl;
        this.locationType = locationType;
        this.parentStatio = parentStatio;
        this.stopTimezone = stopTimezone;
        this.wheelchairBoarding = wheelchairBoarding;
    }
}
