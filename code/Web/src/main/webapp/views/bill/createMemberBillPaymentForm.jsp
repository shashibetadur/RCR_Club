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
        <div class="span10 search-edit-errors"></div>
        <c:choose>
            <c:when test="${fn:length(memberBillList) > 0}">
                <table class="nk-table table table-bordered table-striped">
                    <thead>
                    <th></th>
                    <th>Bill Id</th>
                    <th>Member</th>
                    <th>Amount</th>
                    <th>Bill Date</th>
                    <th>Bill Status</th>
                    </thead>
                    <tbody>
                    <c:forEach var="order" items="${memberBillList}">
                        <tr>
                            <td><input type='radio' name='row' value='${order.id}:${order.billStatus}'/></td>
                            <td><input type='hidden' name='state' value='${order.id}'/><label>${order.id}</label></td>
                            <td><label>${order.member.personalDetails.firstName} &nbsp ${order.member.personalDetails.lastName}</label></td>
                            <td><label>${order.totalAmount}</label></td>
                            <td><label><fmt:formatDate pattern="dd-MM-yyyy" value="${order.billDate}"/></label></td>
                            <td><label>${order.billStatus}</label></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <br/><br/>
            </c:when>
            <c:otherwise>
                <label>Search Results not found!!!</label>
            </c:otherwise>
        </c:choose>

    <form method="POST" action="<%=request.getContextPath()%>/bill/payment/save">
        <legend>Member Bill Payment</legend>
        <form:hidden path="memberBillPayment.transactionId"/>
        <form:hidden path="memberBillPayment.memberId"/>
        <div class="nk-form-section">
            <div class="span10">
                <span class="nk-filed-label"><label for="account">Account</label></span>
                <span class="nk-filed">
                    <form:select type="text" id="account" path="memberBillPayment.accountId">
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
                <span class="nk-filed"><form:input type="text" id="amount" path="memberBillPayment.amount" maxlength="100"/></span>
            </div>
        </div>
        <br/>

        <div class="nk-form-section">
            <div class="span10">
                <span class="nk-filed-label"><label for="notes">Notes</label></span>
                <span class="nk-filed"><form:textarea type="text" id="notes" path="memberBillPayment.notes" maxlength="100"/></span>
            </div>
        </div>
        <br/>

        <div class="span8 nk-form-section">
            <button type="submit" class="btn btn-primary"><i class = 'icon-ok'></i> &nbsp Save</button>
            <button type="reset" class="btn btn-warning"><i class = 'icon-refresh'></i> &nbsp Clear</button>
        </div>
    </form>
</div>




