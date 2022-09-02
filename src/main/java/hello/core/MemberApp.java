package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
        /*
        스프링은 ApplicationContext(스프링 컨테이너)로 시작해서 모든 객체들을 관리합니다.
        AppConfig에 있는 설정 정보를 가지고(@Bean) 스프링 컨테이너에 객체를 생성하고 등록한 뒤 관리합니다.
         */
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        // Config에 있는 메서드 이름 memberService, 반환 타입 MemberService.class 를 파라미터로 넘겨서 MemberService를 가져옵니다.
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find member = " + findMember.getName());
    }
}
