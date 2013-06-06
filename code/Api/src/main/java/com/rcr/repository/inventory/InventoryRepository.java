package com.rcr.repository.inventory;

import com.rcr.domain.Inventory;
import com.rcr.domain.Material;
import com.rcr.repository.BaseRepository;
import com.rcr.repository.material.MaterialRepository;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class InventoryRepository extends BaseRepository<Inventory> {

    private MaterialRepository materialRepository;

    @Autowired
    public InventoryRepository(@Qualifier("sessionFactory") SessionFactory sessionFactory, MaterialRepository materialRepository) {
        super(Inventory.class);
        this.materialRepository = materialRepository;
        setSessionFactory(sessionFactory);
    }

    public long getMaterialQty(long id) {

        Query qry = getSession().getNamedQuery("getCurrentStock");
        qry.setParameter("materialId", id);

        List<Inventory> inventoryList = qry.list();

        if (inventoryList.size() != 0)
            return inventoryList.get(0).getCurrentStock();
        else
            return 0;
    }

    public List<Inventory> getCurrentStock() {
        return getSession().getNamedQuery("getAllCurrentStock").list();
    }

    public Long getQtyAtDate(long id, Date onDate) {

        Query qry = getSession().getNamedQuery("getStockAtDate");
        qry.setParameter("materialId", id);
        qry.setParameter("onDate", onDate);

        List<Inventory> inventoryList = qry.list();

        if (inventoryList.size() != 0)
            return inventoryList.get(0).getCurrentStock();
        else
            return (long) 0;
    }
}
