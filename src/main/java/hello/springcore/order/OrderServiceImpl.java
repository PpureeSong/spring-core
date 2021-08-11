package hello.springcore.order;

import hello.springcore.discount.DiscountPolicy;
import hello.springcore.member.Member;
import hello.springcore.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor // 필수값인 final이 붙은 필드를 파라미터로 받는 생성자를 만들어줌
// 의존관계에 대한 고민은 외부(AppConfig)에 맡기고 실행에만 집중
public class OrderServiceImpl implements OrderService {

    // 인터페이스에 대해서만 의존!
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    //

    /**
     * 생성자 위에 애노테이션 설정을 해주면 의존관계를 자동으로 주입
     * 생성자 주입 권장 이유
     *   - 불변하게 설계할 수 있음
     *   - 주입 방지 : 주입 데이터를 누락했을 때 컴파일 오류 발생
     *   - final 키워드를 사용할 수 있음
     */
//    @Autowired
//    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }

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
