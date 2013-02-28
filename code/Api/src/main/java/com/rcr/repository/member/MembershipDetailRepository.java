package com.rcr.repository.member;

import com.rcr.domain.MembershipDetail;
import com.rcr.repository.BaseRepository;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MembershipDetailRepository extends BaseRepository<MembershipDetail> {

    @Autowired
    public MembershipDetailRepository(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
        super(MembershipDetail.class);
        setSessionFactory(sessionFactory);
    }

    public List<MembershipDetail> findMembershipDetailsByMemberId(long memberId) {
        Criteria criteria = getSession().createCriteria(MembershipDetail.class);
        return criteria.add(Restrictions.eq("memberId", memberId)).list();
    }
}
