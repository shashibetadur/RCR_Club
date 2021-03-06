package com.rcr.repository.bill;

import com.rcr.domain.Bill;
import com.rcr.domain.BillSearchCriteria;
import com.rcr.domain.member.Member;
import com.rcr.repository.BaseRepository;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.hibernate.criterion.Restrictions.*;

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
        if(billSearchCriteria.getBillStatusList().size() > 0){
            criteria.add(Restrictions.in("status", billSearchCriteria.getBillStatusList()));
        }
        criteria.add(or(isNull("deleteFlag"), not(eq("deleteFlag", 'Y'))));
        return criteria.list();
    }

    public List<Bill> getBillsByPaymentId(long transactionId) {
        Criteria criteria = getSession().createCriteria(Bill.class);
        criteria.add(Restrictions.eq("transactionId", transactionId));
        return criteria.list();
    }
}
