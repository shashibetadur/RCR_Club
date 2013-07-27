<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="nk" uri="/WEB-INF/custom-tags.tld" %>
<ul class="nk-side-menu nav nav-tabs nav-pills nav-stacked">
    <li class="nk-side-menu-sep"><a class="btn" href="#">Context Menu</a></li>
    <nk:security operations="account-list">
        <li class="nk-side-menu-sep"><a href="<%=request.getContextPath()%>/account/list">Account List</a></li>
        <li class="nk-side-menu-sep"><a href="<%=request.getContextPath()%>/account/expense/createForm">Add Expense</a></li>
        <li class="nk-side-menu-sep"><a href="<%=request.getContextPath()%>/account/expense/searchForm">View Expenses</a></li>
        <li class="nk-side-menu-sep"><a href="<%=request.getContextPath()%>/account/transaction/transferForm">Account Transfer</a></li>
    </nk:security>
</ul>