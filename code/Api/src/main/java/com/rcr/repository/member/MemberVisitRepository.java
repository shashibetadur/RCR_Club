package com.rcr.repository.member;

import com.rcr.domain.member.MemberVisit;
import com.rcr.repository.BaseRepository;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class MemberVisitRepository extends BaseRepository<MemberVisit> {

    @Autowired
    public MemberVisitRepository(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
        super(MemberVisit.class);
        setSessionFactory(sessionFactory);
    }

    public List<MemberVisit> findVisitDetailsByDate(Date date) {
        Criteria criteria = getSession().createCriteria(MemberVisit.class);
        return criteria.add(Restrictions.eq("visitDate", date)).list();
    }
}
