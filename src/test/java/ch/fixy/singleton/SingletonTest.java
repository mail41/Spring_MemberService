package ch.fixy.singleton;

import ch.fixy.AppConfig;
import ch.fixy.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    public void pureContainer() {
        AppConfig ac = new AppConfig();

        // 1. 조회 : 호출할 때 마다 객체를 생성
        MemberService memberService1 = ac.memberService();

        // 2. 조회 : 호출할 때 마다 객체를 생성
        MemberService memberService2 = ac.memberService();

        // 참조값이 다른 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        // memberService1 != memberService2
        assertThat(memberService1).isNotSameAs(memberService2);

        /*
         이런식으로 되면, 고객 트래픽이 초당 100이 나오면 초당 100개 객체가 생성되고 소멸된다 -> 메모리가 낭비가 심하다
         해결방안은?
         -> 해당 객체가 딱 1개만 생성되고, 공유하도록 설계하면 된다 -> 바로 싱글톤 패턴

         싱글톤 패턴이란?
         -> 클래스의 인스턴스가 딱 1개만 생성되는 것을 보장하는 디자인 패턴이다.
            객체 인스턴스를 2개 이상 생성하지 못하도록 막아야 한다.
         */
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    public void singletonServiceTest() {

        // 1. 조회 : 호출할 때 마다 같은 객체를 반환
        SingletonServiceTest st1 = SingletonServiceTest.getInstance();
        // 2. 조회 : 호출할 때 마다 같은 객체를 반환
        SingletonServiceTest st2 = SingletonServiceTest.getInstance();

        // 같은 객체 인스턴스가 반환된 것을 확인 할 수 있다!
        System.out.println("st1 = " + st1);
        System.out.println("st2 = " + st2);

        // isSameAs : ==
        // isEqualTo : 자바의 equals
        assertThat(st1).isSameAs(st2);

        st1.logic();
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    public void springContainer() {

        // 스프링 컨테이너( == 싱글톤 컨테이너)는 싱글톤 패턴을 알아서 적용해준다!
        // 스프링 컨테이너를 사용하면 고객의 요청이 올 때 마다 객체를 생성하는 것이 아니라,
        // 이미 만들어진 객체를 공유해서 효율적으로 재사용 할 수 있다.

        // AppConfig ac = new AppConfig();
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        // 1. 조회 : 호출할 때 마다 객체를 생성
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);

        // 2. 조회 : 호출할 때 마다 객체를 생성
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        // 참조값이 다른 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        // memberService1 != memberService2
        assertThat(memberService1).isSameAs(memberService2);
    }
}
