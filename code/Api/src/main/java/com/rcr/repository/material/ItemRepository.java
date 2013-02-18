package com.rcr.repository.material;

import com.rcr.domain.Item;
import com.rcr.domain.Material;
import com.rcr.repository.BaseRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class ItemRepository extends BaseRepository<Item> {
    @Autowired
    public ItemRepository(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
        super(Item.class);
        setSessionFactory(sessionFactory);
    }

    @Override
    public void save(Item item) {
        if (item.isNew())
            getHibernateTemplate().save(item);
        else
            getHibernateTemplate().update(item);
        super.save(item);
    }
}
