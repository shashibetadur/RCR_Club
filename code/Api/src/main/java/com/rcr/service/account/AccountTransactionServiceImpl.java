package com.rcr.service.account;


import com.rcr.domain.account.*;
import com.rcr.repository.account.AccountRepository;
import com.rcr.repository.account.AccountTransactionRepository;
import org.apache.commons.collections.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.apache.commons.collections.CollectionUtils.collect;

@Service
public class AccountTransactionServiceImpl implements AccountTransactionService {

    private AccountRepository accountRepository;
    private AccountTransactionRepository accountTransactionRepository;

    AccountTransactionServiceImpl() {
    }

    @Autowired
    public AccountTransactionServiceImpl(AccountRepository accountRepository, AccountTransactionRepository accountTransactionRepository) {
        this.accountRepository = accountRepository;
        this.accountTransactionRepository = accountTransactionRepository;
    }

    @Override
    public void savePaymentDetails(Payment payment) {
        AccountTransaction accountTransaction = payment.buildAccountTransaction();
        accountTransactionRepository.save(accountTransaction);
        payment.setTransactionId(accountTransaction.getId());
    }

    @Override
    public Payment getPaymentDetails(long transactionId) {
        AccountTransaction accountTransaction = accountTransactionRepository.get(transactionId);
        return accountTransaction.buildPayment();
    }

    @Override
    @Transactional
    public void transferAmount(AccountTransfer accountTransfer) {
        AccountTransaction fromAccountTransaction = accountTransfer.getFromAccountTransaction();
        AccountTransaction toAccountTransaction = accountTransfer.getToAccountTransaction();
        accountTransactionRepository.save(fromAccountTransaction);
        accountTransactionRepository.save(toAccountTransaction);
    }

    @Override
    public List<ExpensePayment> getExpensePaymentDetails(ExpenseSearchCriteria expenseSearchCriteria) {
        List<AccountTransaction> accountTransactions = accountTransactionRepository.getAllTransactionsFor(expenseSearchCriteria);
        return (List<ExpensePayment>) collect(accountTransactions, new Transformer() {
            @Override
            public Object transform(Object input) {
                AccountTransaction accountTransaction = (AccountTransaction) input;
                return accountTransaction.buildPayment();
            }
        });
    }
}
