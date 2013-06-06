<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ taglib prefix="nk" uri="/WEB-INF/custom-tags.tld" %>
<div class="row well">
    <c:choose>
        <c:when test="${fn:length(materials) > 0}">
                <tbody>
                <c:forEach var="material" items="${materials}">
                    <tr>
                        <td><input type='hidden' type='hidden' name='displayMaterialList[:index].id' value='${material.id}'/>
                        <label>${material.name}</label></td>
                        <td><label>${material.description}</label></td>
                        <td><label>${material.stock}</label></td>
                        <a href='javascript:deleteItem(:index)' class='delete-item'><i class = 'icon-trash'></i></a>
                    </tr>
                </c:forEach>
                </tbody>
            <br/><br/>
        </c:when>
        <c:otherwise>
            <label>Search Results not found!!!</label>
        </c:otherwise>
    </c:choose>
</div>




