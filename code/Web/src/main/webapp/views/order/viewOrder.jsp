<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ taglib prefix="nk" uri="/WEB-INF/custom-tags.tld" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/accounting.min.js"></script>
<div class="row well">
    <legend>Order Details</legend>

       <c:if test="${order.deleteFlag == 'Y'}">
                <div class="alert alert-error">
                        <a class="close" data-dismiss="alert"></a>This Order is Marked for Deletion!!
                </div>
       </c:if>
    <div class="nk-form-section">
        <div class="span10">
            <span class="nk-filed-label"><label>Order Id:</label></span>
            <span class="nk-filed"><label>${order.id}</label></span>
        </div>
    </div>
    <div class="nk-form-section">
        <div class="span10">
            <span class="nk-filed-label"><label>Order Status:</label></span>
            <span class="nk-filed"><label>${order.orderStatusDescription}</label></span>
        </div>
    </div>
    <div class="nk-form-section">
        <div class="span10">
            <span class="nk-filed-label"><label>Order Date:</label></span>
            <span class="nk-filed"><fmt:formatDate pattern="dd-MM-yyyy" value="${order.orderDate}"/></span>
        </div>
    </div>
    <div class="nk-form-section span8">
        <h4>Items</h4>

        <div class="order-details">
            <table class="nk-table table table-striped table-bordered">
                <thead>
                <th>Material Name</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Total</th>
                </thead>
                <tbody>
                <c:forEach var="orderDetail" items="${order.displayMaterialList}" varStatus="index">
                    <tr>
                        <td>${orderDetail.name}</td>
                        <fmt:setLocale value="en_IN" scope="session"/>
                        <td><fmt:formatNumber value="${orderDetail.price}" type="currency"/></td>
                        <td>${orderDetail.qty}</td>
                        <td><fmt:formatNumber value="${orderDetail.total}" type="currency"/></td>
                    </tr>
                </c:forEach>
                <c:forEach var="taxDetail" items="${order.orderTaxDetails}" varStatus="index">
                    <tr>
                        <td colspan='3'>${taxDetail.tax.taxType}(${taxDetail.percentage}%)</td>
                        <td colspan='2'><fmt:formatNumber value="${taxDetail.taxAmount}" type="currency"/></td>
                    </tr>
                </c:forEach>
                <tr>
                    <td colspan='3'>Total</td>
                    <td colspan='2'><fmt:formatNumber value="${order.totalAmount}" type="currency"/></td>
                </tr>
                <tr>
                    <td colspan='3'>Rounding</td>
                    <td colspan='2'>${order.rounding}</td>
                </tr>
                <tr>
                    <td colspan='3'>Grand Total</td>
                    <td colspan='2'><fmt:formatNumber value="${order.grandTotalAmount}" type="currency"/></td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
    <nk:security operations="order-edit">
        <c:if test="${order.deleteFlag != 'Y' && order.orderStatus != 'D'}">
            <div class="nk-form-section">
                <div class="span10">
                    <a class="btn btn-primary" href="<%=request.getContextPath()%>/order/orderEditForm/${order.id}"><i class = 'icon-edit'></i> &nbsp Edit</a>
                </div>
            </div>
        </c:if>
    </nk:security>
</div>



