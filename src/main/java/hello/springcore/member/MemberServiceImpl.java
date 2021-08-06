package hello.springcore.member;

public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    // 생성자를 통해서 memberRespository의 구현체에 어떤것이 들어갈지 결정
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
