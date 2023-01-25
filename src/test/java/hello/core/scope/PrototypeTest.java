package hello.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.assertThat;

public class PrototypeTest {
    @Test
    void prototypeBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        System.out.println("find prototypeBean1");
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class); // getBean 호출할 때 Bean 생성 및 init 호출
        System.out.println("find prototypeBean2");
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        System.out.println("prototypeBean1 = " + prototypeBean1);
        System.out.println("prototypeBean2 = " + prototypeBean2);
        assertThat(prototypeBean1).isNotSameAs(prototypeBean2);

        prototypeBean1.destroy();
        prototypeBean2.destroy();
        ac.close(); // 프로토타입 스코프 빈은 스프링 컨테이너에서 빈을 조회할 때 생성되고 초기화 메서드도 실행됩니다. 생성, 의존관계 주입, 초기화 까지만 관여하고 더는 관리하지 않기 때문에 컨테이너가 종료될 때 @PreDestroy가 호출되지 않습니다.
    }

    @Scope("prototype")
    static class PrototypeBean{

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }
}
