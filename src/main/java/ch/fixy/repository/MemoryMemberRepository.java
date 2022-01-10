package ch.fixy.repository;

import ch.fixy.member.Member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository{

    // 동시성 이슈가 있을 수 있기 때문에 현업에서는 ConcurrentHashMap을 쓰는게 좋음
    private static Map<Long, Member> store = new HashMap<>();

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
