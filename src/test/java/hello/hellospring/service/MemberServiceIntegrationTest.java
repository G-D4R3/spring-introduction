package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.JDBCMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional // AfterEach로 clean 해주지 않아도 되는 이유. test를 위한 DB를 따로 생성하기 때문. 기존 DB에 반영되지 않음
class MemberServiceIntegrationTest {

    @Autowired MemberRepository memberRepository;
    @Autowired MemberService memberService;

    @Test
    // @Commit // DB에 반영됨
    void 회원가입() { // 헐 한글 가능
        // given
        Member member = new Member();
        member.setName("spring");

        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");


/*        // memberService.join(member2); // 여기서 예외가 터져야 함 try/catch 대신 assertThrows
        try {
            memberService.join(member2);
            fail();
        } catch(IllegalStateException e) {
            Assertions.assertThat(e.getMessage(), "이미 존재하는 회원입니다.");
        }
*/

    }

}