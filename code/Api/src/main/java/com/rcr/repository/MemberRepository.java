package com.rcr.repository;

import com.rcr.domain.Member;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Repository
public class MemberRepository extends BaseRepository<Member> {

    private static Map<Long, Member> memberMap = new HashMap<Long, Member>();
    private static long id = 1;

    @Autowired
    public MemberRepository(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
        super(Member.class);
        setSessionFactory(sessionFactory);
    }

    @Override
    public void save(Member member) {
        member.setId(id);
        memberMap.put(id, member);
        id++;
    }

    @Override
    public Member get(Serializable id) {
        return memberMap.get(id);
    }
}
