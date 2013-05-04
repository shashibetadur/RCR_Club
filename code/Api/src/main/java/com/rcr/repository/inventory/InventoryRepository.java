package com.rcr.repository.inventory;

import com.rcr.domain.Inventory;
import com.rcr.domain.Material;
import com.rcr.repository.BaseRepository;
import com.rcr.repository.material.MaterialRepository;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class InventoryRepository extends BaseRepository {

    private MaterialRepository materialRepository;

    @Autowired
    public InventoryRepository(@Qualifier("sessionFactory") SessionFactory sessionFactory, MaterialRepository materialRepository) {
        super(Inventory.class);
        this.materialRepository = materialRepository;
        setSessionFactory(sessionFactory);
    }

    public long getMaterialQty(long id) {
        Material material = materialRepository.get(id);
        Criteria criteria = getSession().createCriteria(Inventory.class);
        List<Inventory> inventoryList = criteria.add(Restrictions.eq("material", material))
                .addOrder(Order.desc("id")).list();
        if (inventoryList.size() != 0)
            return inventoryList.get(0).getQuantity();
        else
            return 0;
    }

    public List<Inventory> getCurrentStock() {
        List<Object> invList = new ArrayList<Object>();
        List<Inventory> inventoryList = new ArrayList<Inventory>();
        /*DetachedCriteria subQuery = DetachedCriteria.forClass(Inventory.class)
                .setProjection(Projections.projectionList().add(Projections.max("id"))
                        .add(Projections.groupProperty("material")));

        Criteria criteria = getSession().createCriteria(Inventory.class)
                .add(Subqueries.in("id",subQuery));

        inventoryList = criteria.list();*/
        Query qry = getSession().getNamedQuery("getAllCurrentStock");
        invList = qry.list();
        Iterator iterator = invList.iterator();
        while(iterator.hasNext()){
            Inventory inv = (Inventory) iterator.next();
            inventoryList.add(inv);
        }
        return inventoryList;
    }
}
