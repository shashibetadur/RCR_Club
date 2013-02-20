<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="row well">
    <legend>Item Details</legend>
    <div class="nk-form-section">
        <div class="span10">
            <span class="nk-filed-label"><label>Name</label></span>
            <span class="nk-filed"><label>${item.name}</label></span>
        </div>
    </div>
    <div class="nk-form-section">
        <div class="span10">
            <span class="nk-filed-label"><label>Description</label></span>
            <span class="nk-filed"><label>${item.description}</label></span>
        </div>
    </div>
    <div class="nk-form-section">
        <div class="span10">
            <span class="nk-filed-label"><label>Item Type</label></span>
            <span class="nk-filed"><label>${item.itemTypeDescription}</label></span>
        </div>
    </div>
    <div class="nk-form-section">
        <div class="span10">
            <span class="nk-filed-label"><label>Price</label></span>
            <span class="nk-filed"><label>${item.price}</label></span>
        </div>
    </div>
    <div class="span8 nk-form-section">
            <a href="<%=request.getContextPath()%>/bar/item/editForm/${item.id}" class="btn">Edit</a>
    </div>
</div>