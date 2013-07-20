package com.rcr.repository.account;

import com.rcr.domain.account.AccountTransaction;
import com.rcr.domain.account.ExpenseSearchCriteria;
import com.rcr.repository.BaseRepository;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class AccountTransactionRepository extends BaseRepository<AccountTransaction> {

    @Autowired
    public AccountTransactionRepository(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
        super(AccountTransaction.class);
        setSessionFactory(sessionFactory);
    }

    public List<AccountTransaction> getMemberPaymentTransactionDetails(long memberId) {
        return getSession().getNamedQuery("findMembershipPaymentDetails").setLong("memberId", memberId).list();
    }

    public List<AccountTransaction> getMemberLastBillPaymentTransactionDetails(long memberId) {
        return getSession().getNamedQuery("findMemberLastBillPaymentDetails").setLong("memberId", memberId).list();
    }

    public List<AccountTransaction> getMemberBillPayments(long memberId, Date fromDate, Date toDate) {
        return getSession().getNamedQuery("findMemberBillPayments").setLong("memberId", memberId)
                .setDate("fromDate", fromDate)
                .setDate("toDate", toDate).list();
    }

    public List<AccountTransaction> getAllTransactionsFor(ExpenseSearchCriteria expenseSearchCriteria) {
        Criterion expenseTypeCriteria = null;
        if (!StringUtils.isEmpty(expenseSearchCriteria.getExpenseType())) {
            expenseTypeCriteria = Restrictions.and(
                    Restrictions.eq("transactionDetails.categoryAttributeId.id", 4L),
                    Restrictions.eq("transactionDetails.value", expenseSearchCriteria.getExpenseType())
            );
        }

        Criterion dateCriteria = null;
        if (expenseSearchCriteria.getStartDate() != null && expenseSearchCriteria.getEndDate() != null) {
            dateCriteria = Restrictions.between("accountTransaction.transactionDate", expenseSearchCriteria.getStartDate(), expenseSearchCriteria.getEndDate());
        } else if (expenseSearchCriteria.getEndDate() != null) {
            dateCriteria = Restrictions.le("accountTransaction.transactionDate", expenseSearchCriteria.getEndDate());
        } else if (expenseSearchCriteria.getStartDate() != null) {
            dateCriteria = Restrictions.ge("accountTransaction.transactionDate", expenseSearchCriteria.getStartDate());
        }

        if (expenseTypeCriteria == null && dateCriteria == null) return new ArrayList<AccountTransaction>();

        Criteria criteria = getSession().createCriteria(AccountTransaction.class, "accountTransaction");
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.createAlias("transactionDetails", "transactionDetails");

        if (expenseTypeCriteria != null) criteria.add(expenseTypeCriteria);
        if (dateCriteria != null) criteria.add(dateCriteria);

        return criteria.list();
    }
}
