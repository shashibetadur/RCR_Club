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
    MEMBERSHIP_TYPE_DELETE("membership-type-delete");

    Operation(String operationName) {
        this.operationName = operationName;
    }

    private String operationName;

    public String getOperationName() {
        return operationName;
    }
}
