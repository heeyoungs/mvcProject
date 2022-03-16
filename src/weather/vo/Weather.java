package weather.vo;

public class Weather {

    private Long locationCode;
    private double PTY;
    private double REH;
    private double RN1;
    private double TH1;
    private double UUU;
    private double VVV;
    private double VEC;
    private double WSD;

    public Long getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(Long locationCode) {
        this.locationCode = locationCode;
    }

    public void setPTY(double PTY) {
        this.PTY = PTY;
    }

    public void setREH(double REH) {
        this.REH = REH;
    }

    public void setRN1(double RN1) {
        this.RN1 = RN1;
    }

    public void setT1H(double T1H) {
        this.TH1 = T1H;
    }

    public void setUUU(double UUU) {
        this.UUU = UUU;
    }

    public void setVVV(double VVV) {
        this.VVV = VVV;
    }

    public void setVEC(double VEC) {
        this.VEC = VEC;
    }

    public void setWSD(double WSD) {
        this.WSD = WSD;
    }

    public double getREH() {
        return REH;
    }

    public double getRN1() {
        return RN1;
    }

    public double getTH1() {
        return TH1;
    }

    public double getUUU() {
        return UUU;
    }

    public double getVVV() {
        return VVV;
    }

    public double getVEC() {
        return VEC;
    }

    public double getWSD() {
        return WSD;
    }

    public double getPTY() {
        return PTY;
    }

    @Override
    public String toString() {
        return "locationCode = " + locationCode +
                "\nPTY = " + PTY +
                "\nREH = " + REH +
                "\nRN1 = " + RN1 +
                "\nT1H = " + TH1 +
                "\nUUU = " + UUU +
                "\nVEC = " + VEC +
                "\nVVV = " + VVV +
                "\nWSD = " + WSD;
    }
}
