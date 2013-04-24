package com.rcr.repository.member;

import com.rcr.domain.member.MembershipType;
import com.rcr.repository.BaseRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class MembershipTypeRepository extends BaseRepository<MembershipType> {

    @Autowired
    public MembershipTypeRepository(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
        super(MembershipType.class);
        setSessionFactory(sessionFactory);
    }
}
