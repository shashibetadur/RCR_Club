package com.rcr.repository.material;

import com.rcr.domain.Material;
import com.rcr.domain.member.Member;
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
public class MaterialRepository extends BaseRepository<Material> {

    @Autowired
    public MaterialRepository(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
        super(Material.class);
        setSessionFactory(sessionFactory);
    }

    @Override
    public void save(Material material) {
        if (material.isNew())
            getHibernateTemplate().save(material);
        else
            getHibernateTemplate().update(material);
        super.save(material);
    }

    public List<Material> searchMaterials(List<String> searchTokens) {
        Criteria criteria = getSession().createCriteria(Material.class);
        for (String searchToken : searchTokens) {
            criteria.add(Restrictions.ilike("name", searchToken, MatchMode.ANYWHERE));
        }
        return criteria.list();
    }
}
