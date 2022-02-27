package hello.hellospring;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    /*@PersistenceContext
    private EntityManager em;
     */

    private final MemberRepository memberRepository; // 이렇게 해놓으면 스프링 데이터 jpa가 알아서 구현체를 만들어서 스프링 빈에 등록해줌

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*
    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }*/

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }


    /*
    @Bean
    public MemberRepository memberRepository() {
        //return new JDBCMemberRepository(dataSource);
        //return new JDBCTemplateMemberRepository(dataSource);
        //return new JPAMemberRepository(em);
    } // MemoryMemberRepository에서 바꿈
    // 이 부분이 spring을 사용하는 이유 중 하나
    // spring container가 다형성을 지원하기 때문*/
}
