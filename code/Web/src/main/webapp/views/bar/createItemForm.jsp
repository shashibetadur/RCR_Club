<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="row well">
    <form id="create-item-form" method="POST" action="<%=request.getContextPath()%>/bar/item/save">
        <legend>Item Form</legend>
        <form:hidden path="item.id"/>
        <div class="span10 create-item-errors"></div>
        <div class="nk-form-section">
            <div class="span10">
                <span class="nk-filed-label"><label for="item-name">Name</label></span>
                <span class="nk-filed"><form:input type="text" id="item-name" path="item.name" maxlength="100"/></span>
            </div>
        </div>
        <div class="nk-form-section">
            <div class="span10">
                <span class="nk-filed-label"><label for="item-description">Description</label></span>
                <span class="nk-filed"><form:textarea type="text" id="item-description" path="item.Description" maxlength="100"/></span>
            </div>
        </div>
        <div class="nk-form-section">
            <div class="span10">
                <span class="nk-filed-label"><label for="item-type">Item Type</label></span>
                <span class="nk-filed">
                    <form:select path="item.type" items="${itemTypes}"/>
                </span>
            </div>
        </div>
        <div class="nk-form-section">
            <div class="span10">
                <span class="nk-filed-label"><label for="item-price">Price</label></span>
                <span class="nk-filed"><form:input type="text" id="item-price" path="item.price" maxlength="100"/></span>
            </div>
        </div>
        <div class="span8 nk-form-section">
            <button type="submit" class="btn btn-primary">Save changes</button>
            <button type="reset" class="btn">Cancel</button>
        </div>
    </form>
</div>

<script type="text/javascript">

        $("#create-item-form").submit(function () {
            var errors = "";
            var errorMessageTemplate = "<label class='label label-important'>:message</label>"
            $(".create-item-errors").html("");
            if (isEmpty(jqVal("#item-name"))) {
                errors += errorMessageTemplate.replace(/:message/g, "Item Name cannot be empty")
            }
            if (isEmpty(jqVal("#item-description"))) {
                errors += errorMessageTemplate.replace(/:message/g, "Item Description cannot be empty")
            }
            if (isEmpty(jqVal("#item-unit"))) {
                errors += errorMessageTemplate.replace(/:message/g, "Item Unit cannot be empty")
            }
            if (isEmpty(jqVal("#item-price"))) {
                errors += errorMessageTemplate.replace(/:message/g, "Item Price cannot be empty")
            }
            if (!isEmpty(jqVal("#item-price")) && !canParseDecimal(jqVal("#item-price"))) {
                errors += errorMessageTemplate.replace(/:message/g, "Item Price should be a number")
            }
            if (errors) {
                $(".create-item-errors").html(errors + "<br/><br/>");
                return false;
            }
            return true;
        });

</script>