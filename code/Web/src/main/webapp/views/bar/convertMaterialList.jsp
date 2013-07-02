<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="nk" uri="/WEB-INF/custom-tags.tld" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery-ui-1.9.0.custom.css" type="text/css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/extra.css" type="text/css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui-1.9.0.custom.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/plugin_paging.js"></script>

<div class="row well">
    <legend>Material List</legend>
        <table id="convertList" class="nk-table table table-striped table-bordered">
            <thead>
            <th>Name</th>
            <th>Description</th>
            <th>Unit</th>
            <th>Type</th>
            <th>Price</th>
            <th>Convert</th>
            </thead>
            <tbody>
                <c:forEach var="material" items="${materials}">
                    <tr>
                        <td><label>${material.name}</label></td>
                        <td><label>${material.description}</label></td>
                        <td><label>${material.unit}</label></td>
                        <td><label>${material.materialTypeDescription}</label></td>
                        <fmt:setLocale value="en_IN" scope="session"/>
                        <td><label><fmt:formatNumber value="${material.price}" type="currency"/> </label></td>
                        <td>
                            <a target="_blank" href="<%=request.getContextPath()%>/bar/material/convertToItem/${material.id}"><i class = 'icon-wrench'></i></a>
                            <a name="material${material.id}"></a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
</div>
<script type="text/javascript">
    $(document).ready(function() {
        $('#convertList').dataTable({
            "sPaginationType": "bootstrap"
        });
    });
</script>