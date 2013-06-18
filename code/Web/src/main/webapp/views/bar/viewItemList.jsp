<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="nk" uri="/WEB-INF/custom-tags.tld" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery-ui-1.9.0.custom.css" type="text/css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/extra.css" type="text/css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui-1.9.0.custom.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/plugin_paging.js"></script>

<div class="row well">
    <legend>Item List</legend>
        <table id="itemList" class="nk-table table table-striped table-bordered">
            <thead>
            <th>Name</th>
            <th>Description</th>
            <th>Type</th>
            <th>Price</th>
            <th>Edit</th>
            </thead>
            <tbody>
                <c:forEach var="item" items="${items}">
                    <tr>
                        <td><label>${item.name}</label></td>
                        <td><label>${item.description}</label></td>
                        <td><label>${item.itemTypeDescription}</label></td>
                        <td><label>${item.price}</label></td>
                        <td>
                            <a target="_blank" href="<%=request.getContextPath()%>/bar/item/editForm/${item.id}"><i class = 'icon-edit'></i></a>&nbsp
                            <a name="item${item.id}"></a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
</div>

<script type="text/javascript">

    $(document).ready(function() {
        $('#itemList').dataTable({
            "sPaginationType": "bootstrap"
        });
    });
</script>
