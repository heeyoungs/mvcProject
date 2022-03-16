package weather.vo;

public class City {
    private String bigLocation;
    private String middleLocation;
    private String smallLocation;

    public void setBigLocation(String bigLocation) {
        this.bigLocation = bigLocation;
    }

    public String getBigLocation() {
        return bigLocation;
    }

    public void setMiddleLocation(String middleLocation) {
        this.middleLocation = middleLocation;
    }

    public String getMiddleLocation() {
        return middleLocation;
    }

    public void setSmallLocation(String smallLocation) {
        this.smallLocation = smallLocation;
    }

    public String getSmallLocation() {
        return smallLocation;
    }
}
