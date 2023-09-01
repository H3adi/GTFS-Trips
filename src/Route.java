public class Route {
    String routeId;
    String agencyId;
    String routeShortName;
    String routeLongName;
    String routeDesc;
    String routeType;
    String routeUrl;
    String routeColor;
    String routeTextColor;

    public Route(String routeId, String agencyId, String routeShortName, String routeLongName, String routeDesc, String routeType, String routeUrl, String routeColor, String routeTextColor) {
        this.routeId = routeId;
        this.agencyId = agencyId;
        this.routeShortName = routeShortName;
        this.routeLongName = routeLongName;
        this.routeDesc = routeDesc;
        this.routeType = routeType;
        this.routeUrl = routeUrl;
        this.routeColor = routeColor;
        this.routeTextColor = routeTextColor;
    }
}
