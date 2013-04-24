package com.rcr.repository.member;

import com.rcr.domain.member.Member;
import com.rcr.domain.member.MemberSearchCriteria;
import com.rcr.domain.Person;
import com.rcr.repository.BaseRepository;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang.StringUtils.isEmpty;

@Repository
public class MemberRepository extends BaseRepository<Member> {

    @Autowired
    public MemberRepository(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
        super(Member.class);
        setSessionFactory(sessionFactory);
    }

    @Override
    public void save(Member member) {
        Person person = member.getPersonalDetails();
        if (person.isNew())
            getHibernateTemplate().save(person);
        else
            getHibernateTemplate().merge(person);
        super.save(member);
    }

    public List<Member> findBy(MemberSearchCriteria memberSearchCriteria) {
        Criteria criteria = getSession().createCriteria(Member.class);
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        Criterion searchCriterion = null;
        criteria.createAlias("personalDetails", "personalDetails");
        Criterion nameCriterion = nameCriterion(memberSearchCriteria);
        if (!isEmpty(memberSearchCriteria.getPhone())) {
            criteria.createAlias("personalDetails.phoneList", "phone");
            searchCriterion = Restrictions.ilike("phone.phoneNumber", memberSearchCriteria.getPhone(), MatchMode.ANYWHERE);
        }
        if (nameCriterion != null && searchCriterion != null) {
            searchCriterion = Restrictions.or(searchCriterion, nameCriterion);
        } else if (nameCriterion != null) {
            searchCriterion = nameCriterion;
        }
        return searchCriterion == null ? new ArrayList<Person>() : criteria.add(searchCriterion).list();
    }

    private Criterion nameCriterion(MemberSearchCriteria memberSearchCriteria) {
        Criterion nameCriterion = null;
        if (!isEmpty(memberSearchCriteria.getFirstName()))
            nameCriterion = Restrictions.ilike("personalDetails.firstName", memberSearchCriteria.getFirstName(), MatchMode.ANYWHERE);
        if (!isEmpty(memberSearchCriteria.getLastName()) && nameCriterion != null)
            nameCriterion = Restrictions.and(nameCriterion, Restrictions.ilike("personalDetails.lastName", memberSearchCriteria.getLastName(), MatchMode.ANYWHERE));
        else if (!isEmpty(memberSearchCriteria.getLastName()))
            nameCriterion = Restrictions.ilike("personalDetails.lastName", memberSearchCriteria.getLastName(),MatchMode.ANYWHERE);
        return nameCriterion;
    }
}

