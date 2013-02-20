package com.rcr.repository;

import com.rcr.domain.Entity;
import org.hibernate.Criteria;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.io.Serializable;
import java.util.List;

import static org.hibernate.criterion.Restrictions.*;

public abstract class BaseRepository<T extends Entity> extends HibernateDaoSupport {

    private Class<T> type;

    protected BaseRepository(Class<T> type) {
        this.type = type;
    }

    public void save(T object) {
        if (object.isNew())
            getHibernateTemplate().save(object);
        else
            getHibernateTemplate().merge(object);
    }

    public T get(Serializable id) {
        return (T) getHibernateTemplate().get(type, id);
    }


    public void delete(T object) {
        getHibernateTemplate().delete(object);
    }

    public List<T> getAll() {
        return getHibernateTemplate().loadAll(type);
    }

    public List<T> getAllActive() {
        Criteria criteria = getSession().createCriteria(type);
        criteria.add(or(isNull("deleteFlag"), not(eq("deleteFlag", Entity.DELETE))));
        return criteria.list();
    }

    private T getDetached(Serializable id) {
        T object = (T) getHibernateTemplate().get(type, id);
        getHibernateTemplate().evict(object);
        return object;
    }

}

