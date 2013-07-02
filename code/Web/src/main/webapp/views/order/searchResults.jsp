<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ taglib prefix="nk" uri="/WEB-INF/custom-tags.tld" %>
<div class="row well">
    <div class="span10 search-edit-errors"></div>
    <c:choose>
        <c:when test="${fn:length(orderFormList) > 0}">
            <table class="nk-table table table-bordered table-striped">
                <thead>
                <th></th>
                <th>Order Id</th>
                <th>Amount</th>
                <th>Order Status</th>
                <th>Order Date</th>
                </thead>
                <tbody>
                <c:forEach var="order" items="${orderFormList}">
                    <tr>
                        <td><input type='radio' name='row' value='${order.id}:${order.orderStatus}'/></td>
                        <td><input type='hidden' name='state' value='${order.orderStatus}'/><label>${order.id}</label></td>
                        <fmt:setLocale value="en_IN" scope="session"/>
                        <td><label><fmt:formatNumber value="${order.totalAmount}" type="currency"/></label></td>
                        <td><label>${order.orderStatusDescription}</label></td>
                        <td><label><fmt:formatDate pattern="dd-MM-yyyy" value="${order.orderDate}"/></label></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <legend/>
            <div class="nk-form-section">
                <div class="span10">
                    <a href="<%=request.getContextPath()%>/order/orderEditForm/" class="btn btn-primary edit-order"><i class = 'icon-edit'></i> &nbsp Edit Order</a>
                    <a href="<%=request.getContextPath()%>/order/viewOrder" class="btn  btn-info view-order"><i class = 'icon-eye-open'></i> &nbsp View Order Details</a>
                </div>
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
            var selectedOrderAndStatus = $('input:radio[name=row]:checked').val();
            var selectedOrder = selectedOrderAndStatus.split(":")[0];
            var selectedOrderStatus = selectedOrderAndStatus.split(":")[1];

                    var errors = "";
                    var errorMessageTemplate = "<label class='label label-important'>:message</label>"
                    $(".search-edit-errors").html("");
                    if(selectedOrderStatus == 'D'){
                        errors += errorMessageTemplate.replace(/:message/g, "Received Order Cannot be Edited");
                    }
                    if (errors) {
                        $(".search-edit-errors").html(errors + "<br/><br/>");
                        return false;
                    }

            if (selectedOrder) document.location.href = ($(this).attr('href') + "/" + selectedOrder);
            else return false;
        });
        $('.view-order').click(function (event) {
            event.preventDefault();
            var selectedOrderAndStatus = $('input:radio[name=row]:checked').val();
            var selectedOrder = selectedOrderAndStatus.split(":")[0];
            if (selectedOrder) document.location.href = $(this).attr('href') + "/" + selectedOrder;
            else return false;
        });
    });
</script>



