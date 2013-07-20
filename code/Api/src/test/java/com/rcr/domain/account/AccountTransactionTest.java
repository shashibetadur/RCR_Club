package com.rcr.domain.account;


import org.junit.Test;

import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AccountTransactionTest {

    @Test
    public void shouldBuildMembershipPayment() {
        AccountTransaction accountTransaction = new AccountTransaction();
        accountTransaction.setAccount(new Account(1));
        accountTransaction.setAmount(100);
        accountTransaction.setCreditDebit(AccountTransaction.CREDIT);
        accountTransaction.setNotes("NA");
        accountTransaction.setTransactionDate(new Date(System.currentTimeMillis()));
        accountTransaction.getTransactionDetails().add(new AccountTransactionDetail(CategoryTypes.MembershipFees.MEMBER_ID, "1"));
        Payment payment = accountTransaction.buildPayment();
        assertThat(payment instanceof MembershipPayment, is(true));
        assertThat(payment.getAccountId(), is(1L));
        assertThat(payment.getAmount(), is(100D));
        assertThat(payment.creditDebitFlag(), is(AccountTransaction.CREDIT));
        assertThat(payment.getNotes(), is("NA"));
        assertThat(((MembershipPayment) payment).getMemberId(), is(1L));
    }

    /*@Test
    public void shouldBuildMemberBillPayment() {
        AccountTransaction accountTransaction = new AccountTransaction();
        accountTransaction.setAccount(new Account(1));
        accountTransaction.setAmount(100);
        accountTransaction.setCreditDebit(AccountTransaction.CREDIT);
        accountTransaction.setNotes("NA");
        accountTransaction.setTransactionDate(new Date(System.currentTimeMillis()));
        accountTransaction.getTransactionDetails().add(new AccountTransactionDetail(CategoryTypes.MemberBill.MEMBER_ID, "1"));
        Payment payment = accountTransaction.buildPayment();
        assertThat(payment instanceof MemberBillPayment, is(true));
        assertThat(payment.getAccountId(), is(1L));
        assertThat(payment.getAmount(), is(100D));
        assertThat(payment.creditDebitFlag(), is(AccountTransaction.CREDIT));
        assertThat(payment.getNotes(), is("NA"));
        assertThat(((MemberBillPayment) payment).getMemberId(), is(1L));
    } */

    @Test
    public void shouldBuildExpensePayment() {
        AccountTransaction accountTransaction = new AccountTransaction();
        accountTransaction.setAccount(new Account(1));
        accountTransaction.setAmount(100);
        accountTransaction.setCreditDebit(AccountTransaction.DEBIT);
        accountTransaction.setNotes("NA");
        accountTransaction.setTransactionDate(new Date(System.currentTimeMillis()));
        accountTransaction.getTransactionDetails().add(new AccountTransactionDetail(CategoryTypes.Expense.EXPENSE_TYPE, "XYZ"));
        Payment payment = accountTransaction.buildPayment();
        assertThat(payment instanceof ExpensePayment, is(true));
        assertThat(payment.getAccountId(), is(1L));
        assertThat(payment.getAmount(), is(100D));
        assertThat(payment.creditDebitFlag(), is(AccountTransaction.DEBIT));
        assertThat(payment.getNotes(), is("NA"));
        assertThat(((ExpensePayment) payment).getExpenseType(), is("XYZ"));
    }

    @Test
    public void shouldBuildPurchaseOrderPayment() {
        AccountTransaction accountTransaction = new AccountTransaction();
        accountTransaction.setAccount(new Account(1));
        accountTransaction.setAmount(100);
        accountTransaction.setCreditDebit(AccountTransaction.DEBIT);
        accountTransaction.setNotes("NA");
        accountTransaction.setTransactionDate(new Date(System.currentTimeMillis()));
        accountTransaction.getTransactionDetails().add(new AccountTransactionDetail(CategoryTypes.PurchaseOrder.PURCHASE_ORDER_ID, "1"));
        Payment payment = accountTransaction.buildPayment();
        assertThat(payment instanceof PurchaseOrderPayment, is(true));
        assertThat(payment.getAccountId(), is(1L));
        assertThat(payment.getAmount(), is(100D));
        assertThat(payment.creditDebitFlag(), is(AccountTransaction.DEBIT));
        assertThat(payment.getNotes(), is("NA"));
        assertThat(((PurchaseOrderPayment) payment).getPurchaseOrderId(), is(1L));
    }


}
