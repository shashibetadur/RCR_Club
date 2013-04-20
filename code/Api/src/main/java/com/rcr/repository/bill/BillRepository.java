package com.rcr.repository.bill;

import com.rcr.domain.Bill;
import com.rcr.domain.BillSearchCriteria;
import com.rcr.domain.Member;
import com.rcr.repository.BaseRepository;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BillRepository extends BaseRepository {

    @Autowired
    public BillRepository(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
        super(Bill.class);
        setSessionFactory(sessionFactory);
    }

    public void save(Bill bill) {
        if (bill.isNew())
            getHibernateTemplate().save(bill);
        else
            getHibernateTemplate().update(bill);
        super.save(bill);
    }

    public List<Bill> findBy(BillSearchCriteria billSearchCriteria, List<Member> memberList) {
        Criteria criteria = getSession().createCriteria(Bill.class);
        if (memberList != null && !memberList.isEmpty()) {
            criteria.add(Restrictions.in("member", memberList));
        }
        if (billSearchCriteria.hasDateRange()) {
            criteria.add(Restrictions.between("date", billSearchCriteria.getFromDate(), billSearchCriteria.getToDate()));
        }
/*        if (!billSearchCriteria.getOrderTypeList().isEmpty()) {
            criteria.add(Restrictions.in("orderType", billSearchCriteria.getOrderTypeList()));
        }
        if (!billSearchCriteria.getOrderStatusList().isEmpty()) {
            criteria.add(Restrictions.in("orderStatus", billSearchCriteria.getOrderStatusList()));
        }  */
        return criteria.list();
    }
}
