package com.rcr.repository.inventory;

import com.rcr.domain.Inventory;
import com.rcr.domain.Material;
import com.rcr.repository.BaseRepository;
import com.rcr.repository.material.MaterialRepository;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InventoryRepository extends BaseRepository{

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
            List<Inventory> inventoryList =  criteria.add(Restrictions.eq("material", material)).list();
            if(inventoryList.size() != 0)
                return inventoryList.get(0).getQuantity();
            else
                return 0;
    }
}
