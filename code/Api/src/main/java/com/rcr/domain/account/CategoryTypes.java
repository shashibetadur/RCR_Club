package com.rcr.domain.account;


public class CategoryTypes {

    public static Class<? extends Payment> getPaymentType(AccountTransactionDetail accountTransactionDetail) {
        if (accountTransactionDetail.getCategoryAttributeId().getId() == PurchaseOrder.PURCHASE_ORDER_ID) {
            return PurchaseOrderPayment.class;
        }
        if (accountTransactionDetail.getCategoryAttributeId().getId() == MembershipFees.MEMBER_ID) {
            return MembershipPayment.class;
        }
        if (accountTransactionDetail.getCategoryAttributeId().getId() == MemberBill.MEMBER_ID) {
            return MemberBillPayment.class;
        }
        if (accountTransactionDetail.getCategoryAttributeId().getId() == Expense.EXPENSE_ID) {
            return ExpensePayment.class;
        }
        throw new RuntimeException("Invalid category attribute id");

    }

    class PurchaseOrder {
        public static final long PURCHASE_ORDER_ID = 3L;
    }

    class MembershipFees {
        public static final long MEMBER_ID = 2L;
    }

    class MemberBill {
        public static final long MEMBER_ID = 1L;
    }

    class Expense {
        public static final long EXPENSE_ID = 4L;
    }

    class Transfer {
        public static final long FROM_ACCOUNT_ID = 5L;
        public static final long TO_ACCOUNT_ID = 6L;
    }
}
