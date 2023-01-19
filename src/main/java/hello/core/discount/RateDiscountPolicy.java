package hello.core.discount;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
//@Qualifier("mainDiscountPolicy")
//@Qualifier("mainnDiscountPolicy") -- 오타가 나도 컴파일에서 잡아주지 못해서 발견하기 어렵습니다. -> custom annotation을 만들어서 활용
@MainDiscountPolicy
//@Primary
public class RateDiscountPolicy implements DiscountPoilcy{
    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        } else {
            return 0;
        }
    }
}
