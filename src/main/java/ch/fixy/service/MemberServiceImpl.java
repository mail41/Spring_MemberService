package ch.fixy.service;

import ch.fixy.member.Member;
import ch.fixy.repository.MemberRepository;
import ch.fixy.repository.MemoryMemberRepository;

public class MemberServiceImpl implements MemberService{

    // 인터페이스에 구현체를 넣어준다
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
