<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="nk" uri="/WEB-INF/custom-tags.tld" %>
<div class="row well">
    <c:choose>
        <c:when test="${fn:length(orderFormList) > 0}">
            <table class="nk-table table table-bordered table-striped">
                <thead>
                <th></th>
                <th>Order Id</th>
                <th>Amount</th>
                <th>Order Status</th>
                </thead>
                <tbody>
                <c:forEach var="order" items="${orderFormList}">
                    <tr>
                        <td><input type='radio' name='row' value='${order.id}'/></td>
                        <td><label>${order.id}</label></td>
                        <td><label>${order.totalAmount}</label></td>
                        <td><label>${order.orderStatusDescription}</label></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div class="btn-group">
                <nk:security operations="order-edit">
                    <a href="<%=request.getContextPath()%>/order/editOrder/" class="btn  edit-order">Edit Order</a>
                </nk:security>
                <nk:security operations="order-view">
                    <a href="<%=request.getContextPath()%>/order/viewOrder" class="btn  view-order">View Order Details</a>
                </nk:security>
            </div>
            <br/><br/>
        </c:when>
        <c:otherwise>
            <label>Search Results not found!!!</label>
        </c:otherwise>
    </c:choose>
</div>
<script type="text/javascript">
    $(function () {
        $('.edit-order').click(function (event) {
            event.preventDefault();
            var selectedOrder = $('input:radio[name=row]:checked').val();
            if (selectedOrder) document.location.href = ($(this).attr('href') + "/" + selectedOrder);
            else return false;
        });
        $('.view-order').click(function (event) {
            event.preventDefault();
            var selectedOrder = $('input:radio[name=row]:checked').val();
            if (selectedOrder) document.location.href = $(this).attr('href') + "/" + selectedOrder;
            else return false;
        });
    });
</script>



