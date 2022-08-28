package hello.core.order;

import hello.core.discount.DiscountPoilcy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    // 주문 서비스는 저장소, 가격정책 2개가 필요합니다.
    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private DiscountPoilcy discountPoilcy;
    /* 가격 정책 변경
    추상화, 구체화 둘 다 의존하는 경우에서 추상화에만 의존하는 경우로 변경
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
