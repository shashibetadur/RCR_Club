package com.rcr.repository.material;

import com.rcr.domain.Item;
import com.rcr.domain.Material;
import com.rcr.repository.BaseRepository;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    public List<Item> searchItems(List<String> searchTokens) {
        Criteria criteria = getSession().createCriteria(Item.class);
        for (String searchToken : searchTokens) {
            criteria.add(Restrictions.ilike("name", searchToken, MatchMode.ANYWHERE));
        }
        return criteria.list();
    }
}
