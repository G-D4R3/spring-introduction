package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository respository = new MemoryMemberRepository();

    // Test는 순서가 상관없다. 그러도록 설계해야한다.
    // 따라서 Test 하나가 끝나면 data를 clear 해줘야 한다.
    @AfterEach
    public void afterEach() {
        respository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        respository.save(member);

        Member result = respository.findById(member.getId()).get();
        //Assertions.assertEquals(result, member);
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        respository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        respository.save(member2);

        Member result = respository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
        //assertThat(result).isEqualTo(member2);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        respository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        respository.save(member2);

        List<Member> result = respository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }
}
