<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<ul class="nk-side-menu nav nav-tabs nav-pills nav-stacked">
    <li class="nk-side-menu-sep"><a class="btn" href="#">Bar Context</a></li>
    <li class="nk-side-menu-sep"><a href="<%=request.getContextPath()%>/inventory/currentStock">Current Stock</a></li>
    <li class="nk-side-menu-sep"><a href="<%=request.getContextPath()%>/inventory/updateStock">Update Stock</a></li>
    <li class="nk-side-menu-sep"><a href="<%=request.getContextPath()%>/inventory/searchStock">Search Stock</a></li>
</ul>