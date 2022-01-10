package ch.fixy.service;

import ch.fixy.discount.DiscountPolicy;
import ch.fixy.discount.FixDiscountPolicy;
import ch.fixy.member.Member;
import ch.fixy.order.Order;
import ch.fixy.repository.MemberRepository;
import ch.fixy.repository.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member =  memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
