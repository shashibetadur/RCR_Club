package com.rcr.repository.account;

import com.rcr.domain.account.Account;
import com.rcr.repository.BaseRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class AccountRepository extends BaseRepository<Account> {

    @Autowired
    public AccountRepository(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
        super(Account.class);
        setSessionFactory(sessionFactory);
    }
}
