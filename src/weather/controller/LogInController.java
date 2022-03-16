package weather.controller;
// V
import weather.annotation.Component;
import weather.bind.DataBinding;
import weather.dao.MemberDao;
import weather.dao.WeatherDao;
import weather.vo.Location;
import weather.vo.Member;
import weather.vo.Weather;
import weather.wheatherapi.WeatherAPI;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Component("/member/login.do")
public class LogInController implements Controller, DataBinding {

    MemberDao memberDao;
    WeatherDao weatherDao;

    public LogInController setMemberDao(MemberDao memberDao){
        this.memberDao = memberDao;
        return this;
    }

    public LogInController setWeatherDao(WeatherDao weatherDao){
        this.weatherDao = weatherDao;
        return this;
    }

    @Override
    public Object[] getDataBinders() {
        return new Object[]{
                "member", Member.class
        };
    }

    @Override
    public String execute(Map<String, Object> model) throws Exception {
        Member member = (Member) model.get("member");
        if (member.getId() == null){
            return "/member/LogInForm.jsp";
        }else{
            Member existMember = memberDao.exist(member.getId(),member.getPassword());
            if (existMember != null){
                ((HttpSession) model.get("session"))
                        .setAttribute("member",existMember);

                if (existMember.getLocationCode() != 0){

                    WeatherAPI weatherAPI = new WeatherAPI();
                    Location location = weatherDao.getXY(existMember.getLocationCode());
                    Weather weatherInfo = weatherAPI.useApi(location);

                    weatherDao.updateWeatherInfo(weatherInfo);
                    ((HttpSession) model.get("session"))
                            .setAttribute("weather",weatherInfo);
                }
                return "redirect:/member/info.do";
            }else{
                return "/member/LogInFail.jsp";
            }
        }
    }

}
