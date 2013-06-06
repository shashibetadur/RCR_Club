<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="nk" uri="/WEB-INF/custom-tags.tld" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery-ui-1.9.0.custom.css" type="text/css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui-1.9.0.custom.min.js"></script>
<div class="row well">
    <legend>Inventory List</legend>
        <div class="menu-list-location">
            <table class="nk-table table table-striped table-bordered">
                <thead>
                <th>Name</th>
                <th>Description</th>
                <th>Current Stock</th>
                <th>Date</th>
                </thead>
                <tbody>
                    <c:forEach var="inventory" items="${inventoryList}">
                        <tr>
                            <td><label>${inventory.material.name}</label></td>
                            <td><label>${inventory.material.description}</label></td>
                            <td><label>${inventory.currentStock}</label></td>
                            <td><label><fmt:formatDate pattern="dd-MM-yyyy" value="${inventory.date}"/></label></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
</div>
