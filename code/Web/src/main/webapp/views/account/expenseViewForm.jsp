<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<div class="row well">
    <legend>Expense Details</legend>
    <div class="nk-form-section">
        <div class="span10">
            <span class="nk-filed-label"><label>Expense Type:</label></span>
            <span class="nk-filed"><label>${expensePayment.expenseType}</label></span>
        </div>
    </div>
    <div class="nk-form-section">
        <div class="span10">
            <span class="nk-filed-label"><label>Amount:</label></span>
            <span class="nk-filed"><label>${expensePayment.amount}</label></span>
        </div>
    </div>
    <div class="nk-form-section">
        <div class="span10">
            <span class="nk-filed-label"><label>Account:</label></span>
            <span class="nk-filed"><label>${expensePayment.accountName}</label></span>
        </div>
    </div>
    <div class="nk-form-section">
        <div class="span10">
            <span class="nk-filed-label"><label>Date:</label></span>
            <span class="nk-filed"><label><fmt:formatDate pattern="dd-MM-yyyy" value="${expensePayment.date}"/></label></span>
        </div>
    </div>
    <div class="nk-form-section">
        <div class="span10">
            <span class="nk-filed-label"><label>Notes:</label></span>
            <span class="nk-filed"><label>${expensePayment.notes}</label></span>
        </div>
    </div>
    <div class="span8 nk-form-section">
        <nk:security operations="expense-edit">
            <a href="<%=request.getContextPath()%>/account/expense/editForm/${expensePayment.transactionId}" class="btn">Edit</a>
        </nk:security>
    </div>
</div>
