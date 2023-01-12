package hello.core.order;

import hello.core.discount.DiscountPoilcy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component // 스프링 빈 등록을 위해 생성자를 호출할 때 @Autowired를 보고 스프링 컨테이너에서 MemberRepository, DiscountPolicy 빈을 꺼내서 넣습니다
public class OrderServiceImpl implements OrderService{

    // 주문 서비스는 저장소, 가격정책 2개가 필요합니다.
//    @Autowired private MemberRepository memberRepository;
//    @Autowired private DiscountPoilcy discountPoilcy;
     private MemberRepository memberRepository;
     private DiscountPoilcy discountPoilcy;

    public void setMemberRepository(MemberRepository memberRepository) {
        System.out.println("OrderServiceImpl.setMemberRepository");
        System.out.println("memberRepository = " + memberRepository);
        this.memberRepository = memberRepository;
    }

    public void setDiscountPoilcy(DiscountPoilcy discountPoilcy) {
        System.out.println("OrderServiceImpl.setDiscountPoilcy");
        System.out.println("discountPoilcy = " + discountPoilcy);
        this.discountPoilcy = discountPoilcy;
    }

    /*
    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        System.out.println("OrderServiceImpl.setMemberRepository");
        System.out.println("memberRepository = " + memberRepository);
        this.memberRepository = memberRepository;
    }
    
    @Autowired
    public void setDiscountPoilcy(DiscountPoilcy discountPoilcy) {
        System.out.println("OrderServiceImpl.setDiscountPoilcy");
        System.out.println("discountPoilcy = " + discountPoilcy);
        this.discountPoilcy = discountPoilcy;
    }
     */

    //@Autowired 생성자가 1개일 경우 @Autowired를 생략해도 가능합니다.
    //new OrderServiceImpl(memberRepository, discountPolicy);
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPoilcy discountPoilcy) {
        System.out.println("생성자 memberRepository = " + memberRepository);
        System.out.println("생성자 discountPoilcy = " + discountPoilcy);
        System.out.println("OrderServiceImpl.OrderServiceImpl");
        this.memberRepository = memberRepository;
        this.discountPoilcy = discountPoilcy;
    }

    @Autowired
    public void init(MemberRepository memberRepository, DiscountPoilcy discountPoilcy) {
        this.memberRepository = memberRepository;
        this.discountPoilcy = discountPoilcy;
    }

    /* 가격 정책 변경
    추상화, 구체화 둘 다 의존하는 경우에서 추상화에만 의존하는 경우로 변경. Memory, JDBC, JPA Repository 등 어떤 저장소가 오는 지 모릅니다.
    AppConfig를 통해 실제 동작에 필요한 구현 객체를 받아서 사용합니다.
    private final DiscountPoilcy discountPoilcy = new FixDiscountPolicy() -> private DiscountPoilcy discountPoilcy;

     */

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        // 저장소, 할인정책 잘 모르니까 memberRepository, discountPolicy한테 알아서 해달라고 넘깁니다.
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPoilcy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
