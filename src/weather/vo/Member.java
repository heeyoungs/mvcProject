package weather.vo;

public class Member {

    private int no;
    private String id;
    private String password;
    private String email;
    private String name;
    private Long locationCode;

    public int getNo() {
        return no;
    }

    public Member setNo(int no) {
        this.no = no;
        return this;
    }

    public String getName() {
        return name;
    }

    public Member setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Member setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Member setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getId() {
        return id;
    }

    public Member setId(String id) {
        this.id = id;
        return this;
    }

    public Long getLocationCode() {
        return locationCode;
    }

    public Member setLocationCode(Long locationCode) {
        this.locationCode = locationCode;
        return this;
    }
}

