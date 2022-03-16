package weather.vo;

public class Location {
    private Long locationCode;
    private String x;
    private String y;

    public void setLocationCode(Long locationCode) {
        this.locationCode = locationCode;
    }

    public void setX(String x) {
        this.x = x;
    }

    public void setY(String y) {
        this.y = y;
    }

    public Long getLocationCode() {
        return locationCode;
    }

    public String getX() {
        return x;
    }

    public String getY() {
        return y;
    }

}
