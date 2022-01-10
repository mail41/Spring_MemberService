package ch.fixy.repository;

import ch.fixy.member.Member;

public interface MemberRepository {

    public void save(Member member);

    public Member findById(Long memberId);
}
