package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


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
}
