package weather.controller;

import weather.annotation.Component;
import weather.bind.DataBinding;
import weather.dao.MemberDao;
import weather.dao.WeatherDao;
import weather.vo.Member;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Component("/member/info.do")
public class MemberInfoController implements Controller, DataBinding {

    MemberDao memberDao;

    public MemberInfoController setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
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
        Member member = (Member) ((HttpSession)model.get("session")).getAttribute("member");
        if (member != null){
            return "/member/MemberInfo.jsp";
        }else{
            return "redirect:/member/login.do";
        }
    }
}
