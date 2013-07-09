package com.rcr.repository.account;

import com.rcr.domain.account.AccountTransaction;
import com.rcr.repository.BaseRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

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
}
