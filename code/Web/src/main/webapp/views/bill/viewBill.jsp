<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ taglib prefix="nk" uri="/WEB-INF/custom-tags.tld" %>
<div class="row well">
    <legend>Bill Details</legend>
    <form:hidden path="bill.member.personalDetails.id"/>
    <c:if test="${bill.deleteFlag == 'Y'}">
        <div class="alert alert-error">
                <a class="close" data-dismiss="alert"></a>This Bill is Marked for Deletion!!
        </div>
    </c:if>
    <div class="nk-form-section">
        <div class="span10">
            <span class="nk-filed-label"><label>Order Id:</label></span>
            <span class="nk-filed"><label>${bill.id}</label></span>
        </div>
    </div>
    <div class="nk-form-section">
        <div class="span10">
            <span class="nk-filed-label"><label>Name :</label></span>
            <span class="nk-filed"><label>${bill.member.personalDetails.firstName}${bill.member.personalDetails.lastName}</label></span>
        </div>
    </div>
    <div class="nk-form-section">
        <div class="span10">
            <span class="nk-filed-label"><label>Bill Date:</label></span>
            <span class="nk-filed"><fmt:formatDate pattern="dd-MM-yyyy" value="${bill.billDate}"/></span>
        </div>
    </div>
    <div class="nk-form-section">
        <div class="span10">
            <span class="nk-filed-label"><label>Bill Status:</label></span>
            <span class="nk-filed"><label>${bill.billStatus}</label></span>
        </div>
    </div>
    <div class="nk-form-section span8">
        <h4>Items</h4>

        <div class="order-details">
            <table class="nk-table table table-striped table-bordered">
                <thead>
                <th>Item Name</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Total</th>
                </thead>
                <tbody>
                <c:forEach var="billDetail" items="${bill.displayItemList}" varStatus="index">
                    <tr>
                        <td>${billDetail.value}</td>
                        <td>${billDetail.price}</td>
                        <td>${billDetail.qty}</td>
                        <td>${billDetail.total}</td>
                    </tr>
                </c:forEach>
                <c:forEach var="taxDetail" items="${bill.billTaxDetails}" varStatus="index">
                    <tr>
                        <td colspan='3'>${taxDetail.tax.taxType}(${taxDetail.percentage})</td>
                        <td colspan='2'>${taxDetail.taxAmount}</td>
                    </tr>
                </c:forEach>
                <tr>
                    <td colspan='3'>Grand Total</td>
                    <td colspan='2'>${bill.totalAmount}</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
    <nk:security operations="order-edit">
        <c:if test="${bill.deleteFlag != 'Y' && bill.billStatus != 'CLOSED'}">
            <a class="btn" href="<%=request.getContextPath()%>/bill/billEdit/${bill.id}">Edit</a>
        </c:if>
    </nk:security>
</div>



