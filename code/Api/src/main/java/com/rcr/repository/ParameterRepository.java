package com.rcr.repository;

import com.rcr.domain.Parameters;
import com.rcr.domain.account.Account;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class ParameterRepository extends BaseRepository<Parameters>{
    @Autowired
    public ParameterRepository(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
        super(Parameters.class);
        setSessionFactory(sessionFactory);
    }

    public Parameters getParameterValue(String parameterName) {
        Criteria criteria = getSession().createCriteria(Parameters.class);
        criteria.add(Restrictions.eq("parameterName",parameterName));
        return (Parameters) criteria.list().get(0);
    }
}
