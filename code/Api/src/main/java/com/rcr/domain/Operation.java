package com.rcr.domain;


public enum Operation {

    HOME_PAGE("home-page"),
    ALL("all"),
    REPORTS("reports"),
    MEMBER_CREATE("member-create"),
    MEMBER_EDIT("member-edit"),
    MEMBER_VIEW("member-view"),
    MEMBER_SEARCH("member-search"),
    MEMBERSHIP_TYPE_LIST("membership-type-list"),
    MEMBERSHIP_TYPE_CREATE("membership-type-create"),
    MEMBERSHIP_TYPE_EDIT("membership-type-edit"),
    MEMBERSHIP_TYPE_DELETE("membership-type-delete"),
    MEMBERSHIP_RENEW("membership-renew"),
    MEMBER_VISIT_VIEW("member-visit-view"),
    MEMBER_VISIT_EDIT("member-visit-edit"),

    ACCOUNT_LIST("account-list"),
    ACCOUNT_CREATE("account-create"),
    ACCOUNT_EDIT("account-edit"),
    ACCOUNT_DELETE("account-delete"),

    EXPENSE_LIST("expense-list"),
    EXPENSE_CREATE("expense-create"),
    EXPENSE_EDIT("expense-edit"),
    EXPENSE_DELETE("expense-delete");

    Operation(String operationName) {
        this.operationName = operationName;
    }

    private String operationName;

    public String getOperationName() {
        return operationName;
    }
}
