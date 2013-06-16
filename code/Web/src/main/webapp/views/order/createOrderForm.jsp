<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/datepicker.css" type="text/css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap-datepicker.js"></script>
<div class="row well">
    <form id="order-creation-form" method="POST" action="<%=request.getContextPath()%>/order/saveOrder">
        <legend>Purchase Order</legend>
        <form:hidden path="processOrderForm.id"/>
        <div class="span10 order-creation-errors"></div>
        <div class="nk-form-section">
            <div class="span10">
                <span class="nk-filed-label"><label for="order-status"><b>Order Status</b></label></span>
                <span class="nk-filed">
                    <form:select path="processOrderForm.orderStatus" items="${orderStates}"/>
                </span>
            </div>
        </div>
        <div class="nk-form-section">
            <div class="span10">
                <span class="nk-filed-label"><label for="order-date"><b>Order Date</b></label></span>
                <form:input type="text" class="order-date" path="processOrderForm.orderDate" maxlength="100"/>
            </div>
        </div>

        <jsp:include page="materialSelection.jsp"/>
        <div class="span8 nk-form-section">
            <button type="submit" class="btn btn-primary"><i class = 'icon-ok'></i> &nbsp Save</button>
            <c:if test="${processOrderForm.id > 0}">
                <a class="btn btn-danger" href="<%=request.getContextPath()%>/order/delete/${processOrderForm.id}"><i class = 'icon-trash'></i>&nbsp Delete</a>
            </c:if>
        </div>
    </form>
</div>
<script type="text/javascript">
    $(document).ready(function () {
            $(".order-date").datepicker({
                format:"dd-mm-yyyy"
            });
    });
    $("#order-creation-form").submit(function () {
        var errors = "";
        var errorMessageTemplate = "<label class='label label-important'>:message</label>"
        $(".order-creation-errors").html("");
        $(".item-row input[type='text']").each(function () {
            var value = $(this).val();
            if (isEmpty(value)) {
                errors += errorMessageTemplate.replace(/:message/g, "Quantity field cannot be empty");
            }
            if (!isEmpty(value) && !canParseNumber(value)) {
                errors += errorMessageTemplate.replace(/:message/g, "Quantity field can only have digits");
            }
        });
        if (errors) {
            $(".order-creation-errors").html(errors + "<br/><br/>");
            return false;
        }
        return true;
    });
    $("input[type='text']").blur(function () {
        $(this).val(specialTrim($(this).val()));
    });
</script>