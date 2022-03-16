package weather.controller;

import weather.annotation.Component;
import weather.wheatherapi.WeatherAPI;
import weather.bind.DataBinding;
import weather.dao.MemberDao;
import weather.dao.WeatherDao;
import weather.vo.City;
import weather.vo.Member;
import weather.vo.Weather;
import weather.vo.Location;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Component("/member/weatherInfoAdd.do")
public class WeatherInfoAddController implements Controller, DataBinding {
    MemberDao memberDao;
    WeatherDao weatherDao;

    public WeatherInfoAddController setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
        return this;
    }

    public WeatherInfoAddController setWeatherDao(WeatherDao weatherDao) {
        this.weatherDao = weatherDao;
        return this;
    }

    @Override
    public Object[] getDataBinders() {
        return new Object[]{
                "city", City.class
        };
    }

    @Override
    public String execute(Map<String, Object> model) throws Exception {
        Member member = (Member) ((HttpSession) model.get("session")).getAttribute("member");
        City city = (City) model.get("city");
        Location locationInfo = null;
        // 정보 입력
        if (city.getBigLocation() != null) {
            locationInfo = weatherDao.getLocationInfo(
                    city.getBigLocation(),
                    city.getMiddleLocation(),
                    city.getSmallLocation());

            // weather 객체 가져오기
            WeatherAPI getWeatherInfo = new WeatherAPI();
            Location location = weatherDao.getXY(locationInfo.getLocationCode());
            Weather weatherInfo = getWeatherInfo.useApi(location);

            // db 테이블에 날씨 정보 등록
            weatherDao.insertWeatherInfo(weatherInfo);

            // member locationCode 값 등록
            memberDao.updateLocationCode(locationInfo.getLocationCode(), member.getNo());

            // member session 값 초기화
            member.setLocationCode(locationInfo.getLocationCode());
            ((HttpSession) model.get("session"))
                    .setAttribute("member", member);
            // 세션에 날씨정보 등록
            ((HttpSession) model.get("session"))
                    .setAttribute("weather", weatherInfo);

        }

        if (member != null) {
            if (member.getLocationCode() == 0) {
                return "/member/WeatherInfoAdd.jsp";
            } else {
                return "redirect:/member/info.do";
            }
        } else {
            return "redirect:/member/login.do";
        }
    }
}
