<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<ul class="nk-side-menu nav nav-tabs nav-pills nav-stacked">
    <li class="nk-side-menu-sep"><a class="btn" href="#">Procurement Context</a></li>
    <li class="nk-side-menu-sep"><a href="<%=request.getContextPath()%>/order/searchOrder">Search Order</a></li>
    <li class="nk-side-menu-sep"><a href="<%=request.getContextPath()%>/order/createOrder">Create Order</a></li>
    <li class="nk-side-menu-sep"><a href="<%=request.getContextPath()%>/bar/material/list">Material List</a></li>
    <li class="nk-side-menu-sep"><a href="<%=request.getContextPath()%>/bar/material/createForm">New Material</a></li>
    <li class="nk-side-menu-sep"><a href="<%=request.getContextPath()%>/bar/material/convertMaterials">Convert Materials</a></li>
    <li class="nk-side-menu-sep"><a href="<%=request.getContextPath()%>/tax/order/configuration">Tax Configuration</a></li>
    <li class="nk-side-menu-sep"><a href="<%=request.getContextPath()%>/parameter/configuration">Parameters</a></li>
</ul>