<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="nk" uri="/WEB-INF/custom-tags.tld" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery-ui-1.9.0.custom.css" type="text/css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui-1.9.0.custom.min.js"></script>
<div class="row well">
    <legend>Material List</legend>
        <jq>
            <div class="nk-form-section">
                <br/>
                <span class="nk-filed-label"><label for="material-name"><b>Search Material</b></label></span>
                <span class="nk-filed"><input type="text" id="material-name" maxlength="100"/><a target="_blank" class="new-tab hidden" href="<%=request.getContextPath()%>/bar/material/editForm"></a></span>
                <div class="menu-location"></div>
            </div>
        </jq> </br>
        <div class="menu-list-location">
            <table class="nk-table table table-striped table-bordered">
                <thead>
                <th>Name</th>
                <th style="width: 35%">Description</th>
                <th style="width: 10%">Unit</th>
                <th style="width: 15%">Type</th>
                <th style="width: 15%">Price</th>
                <th style="width: 10%">Edit</th>
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
                                <a target="_blank" href="<%=request.getContextPath()%>/bar/material/editForm/${material.id}"><i class = 'icon-edit'></i></a>
                                <a name="material${material.id}"></a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            </div>
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
            $($.find(".material-name-" + ui.item.id)).addClass('selected-background');
            $('.new-tab').trigger('click');
            document.location.href = document.location.pathname + "#material" + ui.item.id;
            return false;
        }
    });
</script>