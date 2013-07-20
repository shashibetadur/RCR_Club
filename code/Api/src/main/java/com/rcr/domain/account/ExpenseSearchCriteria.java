package com.rcr.domain.account;

import java.util.Date;

public class ExpenseSearchCriteria {

    private String expenseType;

    private Date startDate;

    private Date endDate;

    public ExpenseSearchCriteria() {
    }

    public ExpenseSearchCriteria(String expenseType, Date startDate, Date endDate) {
        this.expenseType = expenseType;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(String expenseType) {
        this.expenseType = expenseType;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
