package ch.fixy;

import ch.fixy.discount.DiscountPolicy;
import ch.fixy.discount.FixDiscountPolicy;
import ch.fixy.repository.MemoryMemberRepository;
import ch.fixy.service.MemberService;
import ch.fixy.service.MemberServiceImpl;
import ch.fixy.service.OrderService;
import ch.fixy.service.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    /* AppConfig는 애플리케이션의 실제 동작에 필요한 구현 객체를 '생성' 한다.
       생성한 객체 인스턴스의 참조(레퍼런스)를 생성자를 통해서 '주입(연결)' 해준다.
       MemberServiceImpl -> MemoryMemberRepository
       OrderServiceImpl -> MemoryMemberRepository, FixDiscountPolicy
    */

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemoryMemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    /*
        할인 정책 역할을 담당하는 구현을 FixDiscountPolicy -> RateDiscountPolicy 객체로 변경하면?
        이제 할인 정책을 변경해도, 애플리케이션의 구성 역할을 담당하는 AppConfig만 변경하면 된다.
        클라이언트 코드인 OrderServiceImpl을 포함해서 사용영역의 어떤 코드도 변경할 필요 없다.
     */

    @Bean
    public DiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
//        return new RateDiscountPolicy();
    }
}
