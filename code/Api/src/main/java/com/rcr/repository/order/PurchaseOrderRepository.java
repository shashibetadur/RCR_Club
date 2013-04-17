package com.rcr.repository.order;

import com.rcr.domain.OrderSearchCriteria;
import com.rcr.domain.PurchaseOrder;
import com.rcr.repository.BaseRepository;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PurchaseOrderRepository extends BaseRepository {

    @Autowired
    public PurchaseOrderRepository(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
        super(PurchaseOrder.class);
        setSessionFactory(sessionFactory);
    }

    public void save(PurchaseOrder purchaseOrder) {
        if (purchaseOrder.isNew())
            getHibernateTemplate().save(purchaseOrder);
        else
            getHibernateTemplate().update(purchaseOrder);
        super.save(purchaseOrder);
    }

    public List<PurchaseOrder> findBy(OrderSearchCriteria orderSearchCriteria) {
        Criteria criteria = getSession().createCriteria(PurchaseOrder.class);
        if (orderSearchCriteria.hasDateRange()) {
            criteria.add(Restrictions.between("date", orderSearchCriteria.getFromDate(), orderSearchCriteria.getToDate()));
        }
        if (!orderSearchCriteria.getOrderStatusList().isEmpty()) {
            criteria.add(Restrictions.in("orderStatus", orderSearchCriteria.getOrderStatusList()));
        }
        return criteria.list();
    }
}
