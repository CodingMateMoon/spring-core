package hello.core;

import hello.core.discount.DiscountPoilcy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class AppConfig {
    // AppConfig가 객체의 생성과 연결을 담당하며 MemberServiceImple은 MemberRepository인 추상에만 의존합니다. MemberServiceImple 객체, MemoryMemberRepository등을 생성해서 MemoryMemberRepository를 MemberServiceImpl에 넣어줍니다.

    /* AppConfig에서 역할과 구현 클래스가 한 눈에 들어오도록 표현
    MemberService 역할(현재 MemberService는 MemberServiceImpl을 사용), MemberRepository 역할(현재 MemberRepository는 MemoryMemberRepository를 사용), OrderService 역할
     */
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPoilcy());
    }

    private MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public DiscountPoilcy discountPoilcy() {
        return new FixDiscountPolicy();
    }
}
