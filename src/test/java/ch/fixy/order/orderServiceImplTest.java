package ch.fixy.order;

import ch.fixy.discount.FixDiscountPolicy;
import ch.fixy.member.Grade;
import ch.fixy.member.Member;
import ch.fixy.repository.MemberRepository;
import ch.fixy.repository.MemoryMemberRepository;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class orderServiceImplTest {

    @Test
    public void createOrder() {
        // 수정자 주입을 했을 때는, NPE가 발생한다.
        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L, "name", Grade.VIP));

        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, new FixDiscountPolicy());
        Order order = orderService.createOrder(1L, "iteamA", 10000);
        assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
