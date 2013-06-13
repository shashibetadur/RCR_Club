package com.rcr.repository.tax;

import com.rcr.domain.Tax;
import com.rcr.repository.BaseRepository;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaxRepository extends BaseRepository<Tax> {
    @Autowired
    public TaxRepository(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
        super(Tax.class);
        setSessionFactory(sessionFactory);
    }

    public List<Tax> getAllActive() {
        Criteria criteria = getSession().createCriteria(Tax.class);
        criteria.add(Restrictions.isNull("deleteFlag"));
        return criteria.list();
    }
}
