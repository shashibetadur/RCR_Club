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
            <th>Unit</th>
            <th>Price</th>
            <th>Action</th>
            </thead>
            <tbody>
                <c:forEach var="material" items="${materials}">
                    <tr>
                        <td><label>${material.name}</label></td>
                        <td><label>${material.description}</label></td>
                        <td><label>${material.unit}</label></td>
                        <td><label>${material.price}</label></td>
                        <td>
                            <a target="_blank" href="<%=request.getContextPath()%>/bar/material/convertToItem/${material.id}">Convert</a>
                            <a name="material${material.id}"></a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
</div>
