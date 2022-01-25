package ch.fixy;

import ch.fixy.member.Grade;
import ch.fixy.member.Member;
import ch.fixy.member.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
        //MemberService memberService = new MemberServiceImpl();

        /* AppConfig를 스프링을 사용해서 생성해보자
           ApplicationContext == 스프링 컨테이너(인터페이스)
           스프링 컨테이너는 @Configuration이 붙은 Appconfig를 설정(구성)정보로 사용한다.
           @Bean이라 적힌 메서드를 모두 호출해서 반환된 객체를 스프링 컨테이너에 등록한다.
           이렇게 스프링 컨테이너에 등록된 객체를 스프링 빈이라 한다.
         */
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = ac.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());

    }
}
