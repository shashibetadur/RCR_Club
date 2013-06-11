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
                        <td><input type='radio' name='row' value='${order.id}'/></td>
                        <td><input type='hidden' name='state' value='${order.orderStatus}'/>
                        <label>${order.id}</label></td>
                        <td><label>${order.totalAmount}</label></td>
                        <td><label>${order.orderStatusDescription}</label></td>
                        <td><label><fmt:formatDate pattern="dd-MM-yyyy" value="${order.orderDate}"/></label></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div class="btn-group">
                    <a href="<%=request.getContextPath()%>/order/orderEditForm/" class="btn  edit-order">Edit Order</a>
                    <a href="<%=request.getContextPath()%>/order/viewOrder" class="btn  view-order">View Order Details</a>
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
            var selectedOrderStatus = $('input:hidden[name=state]').val();

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
            var selectedOrder = $('input:radio[name=row]:checked').val();
            if (selectedOrder) document.location.href = $(this).attr('href') + "/" + selectedOrder;
            else return false;
        });
    });
</script>



