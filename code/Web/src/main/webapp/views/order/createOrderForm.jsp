<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="row well">
    <form id="order-creation-form" method="POST" action="<%=request.getContextPath()%>/order/saveOrder">
        <legend>Create Purchase Order</legend>
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
        <jsp:include page="materialSelection.jsp"/>
        <div class="span8 nk-form-section">
            <button type="submit" class="btn btn-primary">Save changes</button>
        </div>
    </form>
</div>
<script type="text/javascript">
    $(document).ready(function () {

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
                errors += errorMessageTemplate.replace(/:message/g, "Quantity field can  only have digits");
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