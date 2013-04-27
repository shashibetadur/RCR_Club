<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/datepicker.css" type="text/css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap-datepicker.js"></script>

<div class="row well">
    <form method="POST" action="<%=request.getContextPath()%>/member/membership/payment/save">
        <legend>Membership Payment Form</legend>
        <form:hidden path="membershipPayment.transactionId"/>
        <form:hidden path="membershipPayment.memberId"/>
        <div class="nk-form-section">
            <div class="span10">
                <span class="nk-filed-label"><label for="account">Account</label></span>
                <span class="nk-filed">
                    <form:select type="text" id="account" path="membershipPayment.accountId">
                        <form:option value="-1" label="--Please Select--"/>
                        <form:options items="${accounts}" itemValue="id" itemLabel="name"/>
                    </form:select>
                </span>
            </div>
        </div>
        <br/>

        <div class="nk-form-section">
            <div class="span10">
                <span class="nk-filed-label"><label for="amount">Amount</label></span>
                <span class="nk-filed"><form:input type="text" id="amount" path="membershipPayment.amount" maxlength="100"/></span>
            </div>
        </div>
        <br/>

        <div class="nk-form-section">
            <div class="span10">
                <span class="nk-filed-label"><label for="notes">Notes</label></span>
                <span class="nk-filed"><form:input type="text" id="notes" path="membershipPayment.notes" maxlength="100"/></span>
            </div>
        </div>
        <br/>

        <div class="span8 nk-form-section">
            <button type="submit" class="btn btn-primary">Save changes</button>
            <button type="reset" class="btn">Cancel</button>
        </div>
    </form>
</div>




