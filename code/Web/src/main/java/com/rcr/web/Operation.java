package com.rcr.web;

@java.lang.annotation.Target({java.lang.annotation.ElementType.METHOD})
@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface Operation {

    //other
    public static String HOME_PAGE = "home-page";
    public static String ALL = "all";
    public static String REPORTS = "reports";

    //member related operations
    public static String MEMBER_CREATE = "member-create";
    public static String MEMBER_EDIT = "member-edit";
    public static String MEMBER_VIEW = "member-view";
    public static String MEMBER_SEARCH = "member-search";

    java.lang.String[] value() default {};
}


