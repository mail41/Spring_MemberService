package ch.fixy.order;

import ch.fixy.AppConfig;
import ch.fixy.member.Grade;
import ch.fixy.member.Member;
import ch.fixy.service.MemberService;
import ch.fixy.service.MemberServiceImpl;
import ch.fixy.service.OrderService;
import ch.fixy.service.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    //MemberService memberService = new MemberServiceImpl();
    //OrderService orderService = new OrderServiceImpl();

    MemberService memberService;
    OrderService orderService;

    // 테스트 실행전 무조건 실행하는 코드
    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    public void createOrder() {
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
