package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component // Component scan 대상이 되어 scan 후 스프링 빈으로 등록
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    // 생성자를 통해서 객체를 주입. 생성자 주입
    @Autowired // MemberRepository 타입에 맞는 구현체를 찾아서 Autowired 자동으로 의존관계 주입 / ac.getBean(MemberRepository.class)
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /* MemberServiceImpl은 MemberService 추상화에도 의존하고 MemoryMemberRepository 구체화에도 의존하고 있습니다. DIP 위반
    private final MemberRepository memberRepository = new MemoryMemberRepository();

     */

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
