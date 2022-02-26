package hello.hellospring;

import hello.hellospring.repository.JDBCMemberRepository;
import hello.hellospring.repository.JDBCTemplateMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        //return new JDBCMemberRepository(dataSource);
        return new JDBCTemplateMemberRepository(dataSource);
    } // MemoryMemberRepository에서 바꿈
    // 이 부분이 spring을 사용하는 이유 중 하나
    // spring container가 다형성을 지원하기 때문
}
