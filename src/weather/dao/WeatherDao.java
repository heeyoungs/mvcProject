package weather.dao;

import weather.annotation.Component;
import weather.vo.Location;
import weather.vo.Weather;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Component("weatherDao")
public class WeatherDao {

    private DataSource ds;

    public void setDs(DataSource ds) {
        this.ds = ds;
    }

    public Location getXY(Long locationCode) {
        Location location = null;
        String query = "select x,y from locationInfo where locationCode = " + locationCode;

        try (Connection connection = ds.getConnection();
             PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery();
        ) {
            rs.next();
            location = new Location();
            location.setLocationCode(locationCode);
            location.setX(rs.getString(1));
            location.setY(rs.getString(2));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return location;
    }

    public int updateWeatherInfo(Weather weather) {
        int success = 0;
        String query = "update member_location set pty = ?, reh =?,rn1 =?,t1h=?,uuu=?,vvv=?,vec=?,wsd=? where locationCode =" + weather.getLocationCode();
        try (Connection connection = ds.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setDouble(1, weather.getPTY());
            ps.setDouble(2, weather.getREH());
            ps.setDouble(3, weather.getRN1());
            ps.setDouble(4, weather.getTH1());
            ps.setDouble(5, weather.getUUU());
            ps.setDouble(6, weather.getVVV());
            ps.setDouble(7, weather.getVEC());
            ps.setDouble(8, weather.getWSD());
            success = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return success;
    }

    public int insertWeatherInfo(Weather weather) {
        int success = 0;
        String query = "insert into member_location values (?,?,?,?,?,?,?,?,?)";

        try (Connection connection = ds.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, weather.getLocationCode());
            ps.setDouble(2, weather.getPTY());
            ps.setDouble(3, weather.getREH());
            ps.setDouble(4, weather.getRN1());
            ps.setDouble(5, weather.getTH1());
            ps.setDouble(6, weather.getUUU());
            ps.setDouble(7, weather.getVVV());
            ps.setDouble(8, weather.getVEC());
            ps.setDouble(9, weather.getWSD());
            success = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return success;
    }

    public Location getLocationInfo(String big, String mid, String small) {
        Location locationCode = null;
        String query = "select locationCode,x,y from locationInfo where lev1 = ? and lev2= ? and lev3 = ?";

        try (Connection connection = ds.getConnection();
             PreparedStatement ps = connection.prepareStatement(query);) {
            locationCode = new Location();
            ps.setString(1, big);
            ps.setString(2, mid);
            ps.setString(3, small);
            ResultSet rs = ps.executeQuery();
            rs.next();

            locationCode.setLocationCode(rs.getLong(1));
            locationCode.setX(rs.getString(2));
            locationCode.setY(rs.getString(3));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return locationCode;
    }

}
