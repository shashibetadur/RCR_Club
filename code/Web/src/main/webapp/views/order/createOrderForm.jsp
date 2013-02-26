<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="row well">
    <form method="POST" action="<%=request.getContextPath()%>#">
        <legend>Create Purchase Order</legend>
        <div class="span10 order-creation-errors"></div>
        <div class="nk-form-section">
            <div class="span10">
                 <span class="nk-filed-label"><label for="order-status"><b>Order Status</b></label></span>
                <span class="nk-filed">
                      <form:select path="processOrderForm.orderStatus" items="${orderStates}"/>
                </span>
            </div>
        </div>
        <jsp:include page="materialSelection.jsp"/>
        <div class="span8 nk-form-section">
            <button type="submit" class="btn btn-primary">Save changes</button>
        </div>
    </form>
</div>