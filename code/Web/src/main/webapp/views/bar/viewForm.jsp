<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="row well">
    <legend>Material Details</legend>
    <div class="nk-form-section">
        <div class="span10">
            <span class="nk-filed-label"><label>Name</label></span>
            <span class="nk-filed"><label>${material.name}</label></span>
        </div>
    </div>
    <div class="nk-form-section">
        <div class="span10">
            <span class="nk-filed-label"><label>Description</label></span>
            <span class="nk-filed"><label>${material.description}</label></span>
        </div>
    </div>
    <div class="nk-form-section">
        <div class="span10">
            <span class="nk-filed-label"><label>Unit</label></span>
            <span class="nk-filed"><label>${material.unit}</label></span>
        </div>
    </div>
    <div class="nk-form-section">
        <div class="span10">
            <span class="nk-filed-label"><label>Type</label></span>
            <span class="nk-filed"><label>${material.materialTypeDescription}</label></span>
        </div>
    </div>
    <div class="nk-form-section">
        <div class="span10">
            <span class="nk-filed-label"><label>Price</label></span>
            <span class="nk-filed"><label>${material.price}</label></span>
        </div>
    </div>
    <div class="span8 nk-form-section">
            <a href="<%=request.getContextPath()%>/bar/material/editForm/${material.id}" class="btn btn-primary"><i class = 'icon-edit'></i> &nbsp Edit</a>
    </div>
</div>