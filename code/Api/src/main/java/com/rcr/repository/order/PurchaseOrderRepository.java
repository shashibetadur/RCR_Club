package com.rcr.repository.order;

import com.rcr.domain.PurchaseOrder;
import com.rcr.repository.BaseRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

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
}
