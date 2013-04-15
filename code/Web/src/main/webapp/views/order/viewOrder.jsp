<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="nk" uri="/WEB-INF/custom-tags.tld" %>
<div class="row well">
    <legend>Order Details</legend>
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
                        <td>${orderDetail.price}</td>
                        <td>${orderDetail.qty}</td>
                        <td>${orderDetail.total}</td>
                    </tr>
                </c:forEach>
                <tr>
                    <td colspan='3'>Grand Total</td>
                    <td colspan='2'>${order.totalAmount}</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
    <nk:security operations="order-edit">
        <a class="btn" href="<%=request.getContextPath()%>/order/orderEditForm/${order.id}">Edit</a>
    </nk:security>
</div>



