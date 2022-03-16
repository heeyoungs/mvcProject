package weather.controller;
// V
import weather.annotation.Component;
import weather.bind.DataBinding;
import weather.dao.MemberDao;
import weather.vo.Member;

import java.util.Map;

@Component("/member/add.do")
public class MemberAddController implements Controller, DataBinding {
    MemberDao memberDao;

    public MemberAddController setMemberDao(MemberDao memberDao){
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
        if (member.getEmail() == null && member.getId() == null){
            return "/member/MemberAdd.jsp";
        }else{
            memberDao.insert(member);
            return "redirect:login.do";
        }
    }
}
