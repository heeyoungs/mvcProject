package weather.controller;

import weather.annotation.Component;
import weather.bind.DataBinding;
import weather.dao.MemberDao;
import weather.vo.Member;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Component("/member/update.do")
public class MemberUpdateController implements Controller, DataBinding {

    MemberDao memberDao;

    public MemberUpdateController setMemberDao(MemberDao memberDao) {
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
        Member member = (Member) ((HttpSession) model.get("session")).getAttribute("member");
        Member jspMember = (Member) model.get("member");
        if (member != null) {
            if (jspMember.getPassword() == null) {
                return "/member/MemberUpdate.jsp";
            } else {
                jspMember.setLocationCode(0L);
                memberDao.update(jspMember);
                ((HttpSession) model.get("session"))
                        .setAttribute("member",jspMember);
                return "redirect:/member/info.do";
            }
        } else {
            return "redirect:/member/login.do";
        }
    }
}
