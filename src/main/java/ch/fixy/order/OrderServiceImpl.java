package ch.fixy.order;

import ch.fixy.annotation.MainDiscountPolicy;
import ch.fixy.discount.DiscountPolicy;
import ch.fixy.member.Member;
import ch.fixy.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("Service")
//@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    /*
    * OCP, DIP 같은 객체 지향 설계 원칙을 준수하지 못했다.
    *  -> 클래스 의존관계를 분석해보면, 추상(인터페이스)뿐만 아니라, 구체(구현)글래스에도 의존 하고 있다.
    *     > 추상(인터페이스)의존 : DiscountPolicy
    *     > 구체(구현) 클래스 : FixDiscountPolicy, RateDiscountPolicy
    *  -> 지금 코드는 기능을 확장해서 변경하면, 클라이언트 코드에 영향을 준다(OCP 위반 - 변경하지 않고 확장이 가능해야한다, 클라이언트에 영향을 주면 안됨)
    *  -> 클라이언트인 OrderServiceImpl이 DiscountPolicy 인터페이스 뿐만 아니라 FixDiscountPolicy인
    *     구현 클래스도 함께 의존하고 있다. (DIP 위반 - 추상에만 의존해야함)
    */

//    private final MemberRepository memberReㅇpository = new MemoryMemberRepository();
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    /*
        * 인터페이스에만 의존하도록 설계를 변경하려면?
        -> 누군가가 클라이언트인 OrderServiceImpl에 DiscountPolicy 구현객체를 대신 생성하고 주입해주어야 한다.

        * SRP 단일 책임 원칙 - 한 클래스는 하나의 책임만 가져야 한다.
        -> 클라이언트 객체인 OrderServiceImpl은 실행하는 책임만 담당하고,
           구현객체를 생성하고 연결하는 책임은 AppConfig가 담당한다.
     */
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;
    // 필드 주입
    // @Autowired private DiscountPolicy discountPolicy;

    // 수정자 주입(setter)
    // @Autowired(required = false) : 선택적으로 사용
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository){
//        System.out.println("memberRepository = " + memberRepository);
//        this.memberRepository = memberRepository;
//    }
//
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy){
//        System.out.println("discountPolicy = " + discountPolicy);
//        this.discountPolicy = discountPolicy;
//    }

    // final로 생성자 주입 : new OrderServiceImpl(memberRepository, discountPolicy)
    // lombok의 @RequiredArgsConstructor를 사용하면 알아서 생성자를 만들어준다.
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        System.out.println("첫번째 호출 OrderServiceImpl.OrderServiceImpl");
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    // 일반 메서드 주입
//    @Autowired
//    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy){
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member =  memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 싱글톤 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
