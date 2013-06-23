<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ taglib prefix="nk" uri="/WEB-INF/custom-tags.tld" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/datepicker.css" type="text/css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap-datepicker.js"></script>

<div class="row well">
<legend>Member Bill Payment</legend>
<label>Member Name: &nbsp ${memberBillList[0].member.personalDetails.firstName} &nbsp ${memberBillList[0].member.personalDetails.lastName}</label>
        <div class="span10 search-edit-errors"></div>
        <c:choose>
            <c:when test="${fn:length(memberBillList) > 0}">
                <table class="nk-table table table-bordered table-striped">
                    <thead>
                    <th>Bill Id</th>
                    <th>Amount</th>
                    <th>Bill Date</th>
                    <th>Bill Status</th>
                    </thead>
                    <tbody>
                    <c:forEach var="order" items="${memberBillList}">
                        <tr>
                            <td><input type='hidden' name='state' value='${order.id}'/><label>${order.id}</label></td>
                            <td><label>${order.totalAmount}</label></td>
                            <td><label><fmt:formatDate pattern="dd-MM-yyyy" value="${order.billDate}"/></label></td>
                            <td><label>${order.billStatus}</label></td>
                        </tr>
                    </c:forEach>
                        <tr><td colspan='3'><label>Grace Amount</label></td><td colspan='2'><label>${graceAmount}</label></td></tr>
                    </tbody>
                </table>
                <br/><br/>
            </c:when>
            <c:otherwise>
                <label>No Bills Found!</label>
            </c:otherwise>
        </c:choose>
        <div class="nk-form-section">
            <div class="span10">
                <span class="nk-filed-label"><label>Account:</label></span>
                <span class="nk-filed"><label>${memberBillPayment.accountId}</label></span>
            </div>
        </div>
        <div class="nk-form-section">
            <div class="span10">
                <span class="nk-filed-label"><label>Amount:</label></span>
                <span class="nk-filed"><label>${memberBillPayment.amount}</label></span>
            </div>
        </div>
        <div class="nk-form-section">
            <div class="span10">
                <span class="nk-filed-label"><label>Notes:</label></span>
                <span class="nk-filed"><label>${memberBillPayment.notes}</label></span>
            </div>
        </div>
</div>