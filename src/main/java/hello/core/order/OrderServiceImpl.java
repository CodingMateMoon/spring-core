package hello.core.order;

import hello.core.discount.DiscountPoilcy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;

public class OrderServiceImpl implements OrderService{

    // 주문 서비스는 저장소, 가격정책 2개가 필요합니다.
    private final MemberRepository memberRepository;
    private final DiscountPoilcy discountPoilcy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPoilcy discountPoilcy) {
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
}
