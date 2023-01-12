package hello.core;

import hello.core.discount.DiscountPoilcy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 애플리케이션의 구성정보 설정정보를 기재합니다.
@Configuration
public class AppConfig {
    // AppConfig가 객체의 생성과 연결을 담당하며 MemberServiceImpl은 MemberRepository인 추상에만 의존합니다. MemberServiceImple 객체, MemoryMemberRepository등을 생성해서 MemoryMemberRepository를 MemberServiceImpl에 넣어줍니다.

    /* AppConfig에서 역할과 구현 클래스가 한 눈에 들어오도록 표현
    MemberService 역할(현재 MemberService는 MemberServiceImpl을 사용), MemberRepository 역할(현재 MemberRepository는 MemoryMemberRepository를 사용), OrderService 역할
     */
    //@Bean memberService -> new MemoryMemberRepository()
    //@Bean orderService -> new MemoryMemberRepository()

    /* 예상 결과 */
    // call AppConfig.memberService
    // call AppConfig.memberRepository
    // call AppConfig.memberRepository
    // call AppConfig.orderService
    // call AppConfig.memberRepository

    /* 실제 결과 */
    //call AppConfig.memberService
    //call AppConfig.memberRepository
    //call AppConfig.orderService
    // memberRepository를 스프링 빈으로 등록하고 빈으로 등록된 것을 스프링이 자동으로 주입합니다.
    //@Autowired MemberRepository memberRepository;

    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        //return new OrderServiceImpl(memberRepository(), discountPoilcy());
        return null;
    }

    // 할인 정책을 변경해도 애플리케이션의 구성 역할을 담당하는 AppConfig만 변경하면 됩니다. 구성 역할을 담당하는 AppConfig 공연 기획자가 공연 참여자인 구현 객체들을 모두 알고 구성 영역을 변경시킵니다. DiscountPolicy 추상화에 의존하고 구현체를 받아서 쓰기 때문에 클라이언트 코드인 OrderServiceImpl 등 사용영역의 코드는 변경하지 않습니다.
    @Bean
    public DiscountPoilcy discountPoilcy() {

//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
