package weather.dao;

import weather.annotation.Component;
import weather.vo.Member;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component("memberDao")
public class MemberDao {

    private DataSource ds;

    public void setDataSource(DataSource ds) {
        this.ds = ds;
    }

    public int insert(Member member) throws Exception {
        int success;
        String query = "insert into members (id, password, email, name) values" +
                " (?, ?, ?, ?)";

        try (Connection connection = ds.getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, member.getId());
            ps.setString(2, member.getPassword());
            ps.setString(3, member.getEmail());
            ps.setString(4, member.getName());
            success = ps.executeUpdate();
        }

        return success;
    }

    public int delete(int no) throws Exception {
        int success;
        String query = "delete from members where no=?";

        try (Connection connection = ds.getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, no);
            success = ps.executeUpdate();
        }

        return success;
    }

    public int update(Member member) throws Exception {
        int success = 0;
        String query = "update members set name=?, id =?, password=? where no=?";

        try (Connection connection = ds.getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, member.getName());
            ps.setString(2, member.getId());
            ps.setString(3, member.getPassword());
            ps.setInt(4, member.getNo());
            success = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
            System.out.println(e.getMessage());
        }

        return success;
    }

    public int updateLocationCode(Long locationCode, int id) throws Exception {
        int success = 0;
        String query = "update members set locationCode = ? where no=?";

        try (Connection connection = ds.getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, locationCode);
            ps.setInt(2, id);
            success = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
            System.out.println(e.getMessage());
        }

        return success;
    }

    public Member exist(String id, String password) throws Exception {
        Member member = null;
        String query = "select * from members" +
                " where id=? and password=?";

        try ( Connection connection = ds.getConnection();
              PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, id);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                member = new Member()
                        .setNo(rs.getInt("no"))
                        .setId(id)
                        .setPassword(password)
                        .setEmail(rs.getString("email"))
                        .setName(rs.getString("name"))
                        .setLocationCode(rs.getLong("locationcode"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return member;
    }

}