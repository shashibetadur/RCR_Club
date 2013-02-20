<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="nk" uri="/WEB-INF/custom-tags.tld" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery-ui-1.9.0.custom.css" type="text/css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui-1.9.0.custom.min.js"></script>
<div class="row well">
    <legend>Material List</legend>
        <table class="nk-table table table-striped table-bordered">
            <thead>
            <th>Name</th>
            <th>Description</th>
            <th>Type</th>
            <th>Price</th>
            <th>Action</th>
            </thead>
            <tbody>
                <c:forEach var="item" items="${items}">
                    <tr>
                        <td><label>${item.name}</label></td>
                        <td><label>${item.description}</label></td>
                        <td><label>${item.itemTypeDescription}</label></td>
                        <td><label>${item.price}</label></td>
                        <td>
                            <a target="_blank" href="<%=request.getContextPath()%>/bar/item/editForm/${item.id}">Edit</a>&nbsp;|
                            <a name="item${item.id}"></a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
</div>