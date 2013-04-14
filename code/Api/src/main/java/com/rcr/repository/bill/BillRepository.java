package com.rcr.repository.bill;

import com.rcr.domain.Bill;
import com.rcr.repository.BaseRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class BillRepository extends BaseRepository{

    @Autowired
     public BillRepository(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
         super(Bill.class);
         setSessionFactory(sessionFactory);
     }
}
