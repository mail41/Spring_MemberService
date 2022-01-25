package ch.fixy.member;

import ch.fixy.member.Member;

public interface MemberService {

    public void join(Member member);

    public Member findMember(Long memberId);

}
