package ch.fixy.discount;

import ch.fixy.member.Grade;
import ch.fixy.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {

    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    // 성공 테스트
    @Test
    @DisplayName("VIP는 10% 할인이 적용되어야 한다")
    public void vip_ok() {
        //given
        Member member = new Member(1L, "memberVIP", Grade.VIP);

        //when
        int discount = discountPolicy.discount(member, 10000);

        //then
        assertThat(discount).isEqualTo(1000);
    }

    // 실패 테스트
//    @Test
//    @DisplayName("VIP가 아니면 할인이 적용되지 않아야 한다")
//    public void vip_no() {
//        //given
//        Member member = new Member(2L, "memberBASIC", Grade.BASIC);
//
//        //when
//        int discount = discountPolicy.discount(member, 10000);
//
//        //then
//        assertThat(discount).isEqualTo(1000);
//
//        // 결과
//        // (기대한값) Expected :1000
//        // (실제 값) A ctual   :0
//
//    }
}