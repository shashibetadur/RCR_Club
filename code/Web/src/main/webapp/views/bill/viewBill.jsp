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
            <span class="nk-filed"><label>${bill.member.personalDetails.firstName} &nbsp ${bill.member.personalDetails.lastName}</label></span>
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
                        <fmt:setLocale value="en_IN" scope="session"/>
                        <td><fmt:formatNumber value="${billDetail.price}" type="currency"/></td>
                        <td>${billDetail.qty}</td>
                        <td><fmt:formatNumber value="${billDetail.total}" type="currency"/></td>
                    </tr>
                </c:forEach>
                <c:forEach var="taxDetail" items="${bill.billTaxDetails}" varStatus="index">
                    <tr>
                        <td colspan='3'>${taxDetail.tax.taxType}(${taxDetail.percentage}%)</td>
                        <td colspan='2'><fmt:formatNumber value="${taxDetail.taxAmount}" type="currency"/></td>
                    </tr>
                </c:forEach>
                <tr>
                    <td colspan='3'>Total</td>
                    <td colspan='2'><fmt:formatNumber value="${bill.totalAmount}" type="currency"/></td>
                </tr>
                <tr>
                    <td colspan='3'>Rounding</td>
                    <td colspan='2'>${bill.rounding}</td>
                </tr>
                <tr>
                    <td colspan='3'>Grand Total</td>
                    <td colspan='2'><fmt:formatNumber value="${bill.grandTotalAmount}" type="currency"/></td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
    </br></br><legend></legend>
    <c:if test="${bill.deleteFlag != 'Y'}">
        <div class="nk-form-section">
            <div class="span10">
                <c:if test="${bill.billStatus != 'CLOSED'}">
                    <a class="btn btn-primary" href="<%=request.getContextPath()%>/bill/billEdit/${bill.id}"><i class = 'icon-edit'></i> &nbsp Edit</a>
                </c:if>
                <a class="btn btn-success" target="_blank" href="<%=request.getContextPath()%>/bill/payment/createBillPayment/${bill.member.personalDetails.id}">
                <i class = 'icon-book'> </i> &nbsp Make Payment</a>
            </div>
        </div>
    </c:if>
</div>



