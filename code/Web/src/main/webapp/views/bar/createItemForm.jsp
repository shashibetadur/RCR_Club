<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="row well">
    <form method="POST" action="<%=request.getContextPath()%>/bar/item/save">
        <legend>Item Form</legend>
        <form:hidden path="item.id"/>
        <div class="nk-form-section">
            <div class="span10">
                <span class="nk-filed-label"><label for="item-name">Name</label></span>
                <span class="nk-filed"><form:input type="text" id="item-name" path="item.name" maxlength="100"/></span>
            </div>
        </div>
        <div class="nk-form-section">
            <div class="span10">
                <span class="nk-filed-label"><label for="item-name">Description</label></span>
                <span class="nk-filed"><form:textarea type="text" id="item-Description" path="item.Description" maxlength="100"/></span>
            </div>
        </div>

        <div class="nk-form-section">
            <div class="span10">
                <span class="nk-filed-label"><label for="item-type">Item Type</label></span>
                <span class="nk-filed"><form:input type="text" id="item-type" path="item.type" maxlength="100"/></span>
            </div>
        </div>

        <div class="nk-form-section">
            <div class="span10">
                <span class="nk-filed-label"><label for="item-category">Category Type</label></span>
                <span class="nk-filed"><form:input type="text" id="item-category" path="item.category" maxlength="100"/></span>
            </div>
        </div>

        <div class="nk-form-section">
            <div class="span10">
                <span class="nk-filed-label"><label for="item-price">Price</label></span>
                <span class="nk-filed"><form:input type="text" id="item-price" path="item.price" maxlength="100"/></span>
            </div>
        </div>

        <div class="span8 nk-form-section">
            <button type="submit" class="btn btn-primary">Save changes</button>
            <button type="reset" class="btn">Cancel</button>
        </div>
    </form>
</div>