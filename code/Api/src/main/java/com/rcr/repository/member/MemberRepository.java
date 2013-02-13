package com.rcr.repository.member;

import com.rcr.domain.Member;
import com.rcr.domain.Person;
import com.rcr.repository.BaseRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository extends BaseRepository<Member> {

    @Autowired
    public MemberRepository(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
        super(Member.class);
        setSessionFactory(sessionFactory);
    }

    @Override
    public void save(Member member) {
        Person person = member.getPersonalDetails();
        if (person.isNew())
            getHibernateTemplate().save(person);
        else
            getHibernateTemplate().merge(person);
        super.save(member);
    }
}
