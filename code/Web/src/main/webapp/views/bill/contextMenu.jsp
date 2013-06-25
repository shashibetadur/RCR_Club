<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<ul class="nk-side-menu nav nav-tabs nav-pills nav-stacked">
    <li class="nk-side-menu-sep"><a class="btn" href="#">Sales Context</a></li>
    <li class="nk-side-menu-sep"><a href="<%=request.getContextPath()%>/bill/searchBill">Search Bill</a></li>
    <li class="nk-side-menu-sep"><a href="<%=request.getContextPath()%>/member/searchForm">Create Bill</a></li>
    <li class="nk-side-menu-sep"><a href="<%=request.getContextPath()%>/bar/item/list">Item List</a></li>
    <li class="nk-side-menu-sep"><a href="<%=request.getContextPath()%>/bar/item/createForm">Create Item</a></li>
    <li class="nk-side-menu-sep"><a href="<%=request.getContextPath()%>/member/searchForm">Create Payment</a></li>
</ul>