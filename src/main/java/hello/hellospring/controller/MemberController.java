package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


// controller가 service를 통해서 join하고 조회할 수 있도록 하는 것을 의존관계가 있다고 표현함
@Controller
public class MemberController {

    // 다른 controller에서도 memverService를 사용할 수도 있음
    //private final MemberService memberService = new MemberService();

    private final MemberService memberService;

    // 생성자에 이 @가 있다면 spring container에 있는 memberService와 연결 시켜줌
    // 의존관계 주입
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
