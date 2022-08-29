package hello.core;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class AppConfig {
    // AppConfig가 객체의 생성과 연결을 담당하며 MemberServiceImple은 MemberRepository인 추상에만 의존합니다. MemberServiceImple 객체, MemoryMemberRepository등을 생성해서 MemoryMemberRepository를 MemberServiceImpl에 넣어줍니다.
    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }
}
