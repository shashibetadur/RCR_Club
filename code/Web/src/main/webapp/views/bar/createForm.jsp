<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="row well">
    <form method="POST" action="<%=request.getContextPath()%>/bar/material/save">
        <legend>Material Form</legend>
        <form:hidden path="material.id"/>

        <div class="nk-form-section">
            <div class="span10">
                <span class="nk-filed-label"><label for="material-materialType">Type</label></span>
                <span class="nk-filed">
                    <select class="material-materialType" path="material.materialType">
                        <c:forEach var="materialType" items="${materialTypes}">
                            <option value="${materialType}">${materialType}</option>
                        </c:forEach>
                    </select>
                </span>
            </div>
        </div>

        <div class="nk-form-section">
            <div class="span10">
                <span class="nk-filed-label"><label for="material-name">Name</label></span>
                <span class="nk-filed"><form:input type="text" id="material-name" path="material.name" maxlength="100"/></span>
            </div>
        </div>

        <div class="nk-form-section">
            <div class="span10">
                <span class="nk-filed-label"><label for="material-description">Description</label></span>
                <span class="nk-filed"><form:textarea type="text" id="material-description" path="material.description" maxlength="100"/></span>
            </div>
        </div>

        <div class="nk-form-section">
            <div class="span10">
                <span class="nk-filed-label"><label for="material-unit">Unit</label></span>
                <span class="nk-filed"><form:input type="text" id="material-unit" path="material.unit" maxlength="100"/></span>
            </div>
        </div>

        <div class="nk-form-section">
            <div class="span10">
                <span class="nk-filed-label"><label for="material-price">Price</label></span>
                <span class="nk-filed"><form:input type="text" id="material-price" path="material.price" maxlength="100"/></span>
            </div>
        </div>

        <div class="span8 nk-form-section">
            <button type="submit" class="btn btn-primary">Save changes</button>
            <button type="reset" class="btn">Cancel</button>
        </div>
    </form>
</div>