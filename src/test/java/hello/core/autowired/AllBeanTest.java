package hello.core.autowired;

import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPoilcy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class AllBeanTest {

    @Test
    void findAllBean() {
        // 스프링 컨테이너 생성 및 AutoAppConfig, DiscountService를 스프링 빈으로 등록
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);

        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member = new Member(1L, "userA", Grade.VIP);
        int discountPrice = discountService.discount(member, 10000, "fixDiscountPolicy"); // VIP이고 10000원이면 얼마나 할인이 되는가

        assertThat(discountService).isInstanceOf(DiscountService.class);
        assertThat(discountPrice).isEqualTo(1000);
        int rateDiscountPrice = discountService.discount(member, 20000, "rateDiscountPolicy"); // VIP이고 10000원이면 얼마나 할인이 되는가
        assertThat(rateDiscountPrice).isEqualTo(2000);
    }

    static class DiscountService{
        private final Map<String, DiscountPoilcy> policyMap;
        private final List<DiscountPoilcy> poilcies;

        //@Autowired 생성자 1개일 때는 생략 가능
        public DiscountService(Map<String, DiscountPoilcy> poilcyMap, List<DiscountPoilcy> poilcies) {
            this.policyMap = poilcyMap;
            this.poilcies = poilcies;
            System.out.println("poilcyMap = " + poilcyMap);
            System.out.println("poilcies = " + poilcies);
        }

        public int discount(Member member, int price, String discountCode) {
            DiscountPoilcy discountPoilcy = policyMap.get(discountCode);
            return discountPoilcy.discount(member, price);
        }
    }
}
