package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public class MemberService {
    private final MemberRepository memberRepository;

    // MemberRepository를 Service에서 직접 new 해서 만들지 않고 외부에서 injection함.
    // => Dependency Injection
    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member) {
        
        // 같은 이름이 있는 중복 회원x
        // optional을 통해서 ifPresent 등의 method를 사용할 수 있음 
        // get보다는 orElseGet을 많이 사용한다. 
        // Optional을 따로 선언해서 사용하는 것 보다는 아래처럼 바로 method를 사용하는 것이 더 깔끔하다.
        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent( m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    // service method name은 business에 가깝게 네이밍 해야함
    // repository method name은 기계에 가깝게 네이밍
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
