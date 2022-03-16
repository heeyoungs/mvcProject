package weather.controller;
// V
import weather.annotation.Component;
import weather.bind.DataBinding;
import weather.dao.MemberDao;
import weather.vo.Member;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Component("/member/delete.do")
public class MemberDeleteController implements Controller, DataBinding {
    MemberDao memberDao;

    public MemberDeleteController setMemberDao(MemberDao memberDao){
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
        Member member = (Member) model.get("member");
        if (member != null){
            HttpSession session = (HttpSession) model.get("session");
            session.invalidate();
            memberDao.delete(member.getNo());
        }
        return "redirect:/member/login.do";
    }
}
