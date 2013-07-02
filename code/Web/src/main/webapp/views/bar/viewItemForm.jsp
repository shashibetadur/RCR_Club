<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
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
            <fmt:setLocale value="en_IN" scope="session"/>
            <span class="nk-filed"><label><fmt:formatNumber value="${item.price}" type="currency"/></label></span>
        </div>
    </div>
    <div class="span8 nk-form-section">
            <a href="<%=request.getContextPath()%>/bar/item/editForm/${item.id}" class="btn btn-primary"><i class = 'icon-edit'></i> &nbsp Edit</a>
    </div>
</div>