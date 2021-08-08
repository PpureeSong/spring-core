package hello.springcore.order;

import hello.springcore.discount.DiscountPolicy;
import hello.springcore.member.Member;
import hello.springcore.member.MemberRepository;

// 의존관계에 대한 고민은 외부(AppConfig)에 맡기고 실행에만 집중
public class OrderServiceImpl implements OrderService {

    // 인터페이스에 대해서만 의존!
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
