<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="nk" uri="/WEB-INF/custom-tags.tld" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery-ui-1.9.0.custom.css" type="text/css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui-1.9.0.custom.min.js"></script>
<div class="row well">
    <legend>Material List</legend>
        <div class="menu-list-location">
            <jq>
                <div class="span10 nk-form-section">
                    <br/>
                    <span class="nk-filed-label"><label for="material-name"><b>Search Material</b></label></span>
                    <span class="nk-filed"><input type="text" id="material-name" maxlength="100"/><a target="_blank" class="new-tab hidden" href="<%=request.getContextPath()%>/bar/material/editForm"></a></span>
                    <div class="menu-location"></div>
                </div>
            </jq>
            <table class="nk-table table table-striped table-bordered">
                <thead>
                <th>Name</th>
                <th>Description</th>
                <th>Unit</th>
                <th>Type</th>
                <th>Price</th>
                <th>Action</th>
                </thead>
                <tbody>
                    <c:forEach var="material" items="${materials}">
                        <tr>
                            <td><label>${material.name}</label></td>
                            <td><label>${material.description}</label></td>
                            <td><label>${material.unit}</label></td>
                            <td><label>${material.materialTypeDescription}</label></td>
                            <td><label>${material.price}</label></td>
                            <td>
                                <a target="_blank" href="<%=request.getContextPath()%>/bar/material/editForm/${material.id}">Edit</a>&nbsp;|
                                <a name="material${material.id}"></a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
</div>
<script type="text/javascript">
    $("#material-name").autocomplete({
        source:function (request, response) {
            $.getJSON("<%=request.getContextPath()%>/bar/material/search", {
                searchToken:request.term
            }, response);
        },
        appendTo:".menu-location",
        select:function (event, ui) {
            $($.find(".material-name-" + ui.material.id)).addClass('selected-background');
            $('.new-tab').trigger('click');
            document.location.href = document.location.pathname + "#material" + ui.material.id;
            return false;
        }
    });
</script>