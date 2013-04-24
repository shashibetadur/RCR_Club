package com.rcr.repository.account;

import com.rcr.domain.account.AccountTransaction;
import com.rcr.repository.BaseRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountTransactionRepository extends BaseRepository<AccountTransaction> {

    @Autowired
    public AccountTransactionRepository(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
        super(AccountTransaction.class);
        setSessionFactory(sessionFactory);
    }
}
