<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="row well">
    <div class="span10 expense-creation-errors"></div>
    <form id="expense-creation" method="POST" action="<%=request.getContextPath()%>/account/expense/save">
        <c:choose>
            <c:when test="${expensePayment.transactionId != 0}">
                <legend>Expense Edit Form</legend>
            </c:when>
            <c:otherwise>
                <legend>New Expense Form</legend>
            </c:otherwise>
        </c:choose>
        <form:hidden path="expensePayment.transactionId"/>
        <div class="nk-form-section">
            <div class="span10">
                <span class="nk-filed-label"><label for="expenseType">Expense Type</label></span>
                <span class="nk-filed"><form:input type="text" id="expenseType" path="expensePayment.expenseType" maxlength="100"/></span>
            </div>
        </div>
        <div class="nk-form-section">
            <div class="span10">
                <span class="nk-filed-label"><label for="account">Account</label></span>
                <span class="nk-filed">
                    <form:select type="text" id="account" path="expensePayment.accountId">
                        <form:option value="-1" label="--Please Select--"/>
                        <form:options items="${accounts}" itemValue="id" itemLabel="name"/>
                    </form:select>
                </span>
            </div>
        </div>

        <div class="nk-form-section">
            <div class="span10">
                <span class="nk-filed-label"><label for="amount">Amount</label></span>
                <span class="nk-filed"><form:input type="text" id="amount" path="expensePayment.amount" maxlength="100"/></span>
            </div>
        </div>
        <br/>

        <div class="nk-form-section">
            <div class="span10">
                <span class="nk-filed-label"><label for="notes">Notes</label></span>
                <span class="nk-filed"><form:textarea type="text" id="notes" path="expensePayment.notes"  maxlength="200"/></span>
            </div>
        </div>
        <div class="span8 nk-form-section">
            <button type="submit" class="btn btn-primary"><i class='icon-ok'></i> &nbsp Save Changes</button>
            <button type="reset" class="btn btn-warning"><i class='icon-refresh'></i> &nbsp Clear</button>
        </div>
    </form>
</div>

<script type="text/javascript">
    $("#expense-creation").submit(function () {
        var errors = "";
        var errorMessageTemplate = "<label class='label label-important'>:message</label>"
        $(".expense-creation-errors").html("");
         if (isEmpty(jqVal("#expenseType"))) {
            errors += errorMessageTemplate.replace(/:message/g, "Expense type cannot be empty")
         }
         if (!isEmpty(jqVal("#expenseType")) && !canParseOnlyAlphabetsWithSpace(jqVal("#expenseType"))) {
            errors += errorMessageTemplate.replace(/:message/g, "Expense type can have only have alphabets and space ")
         }
         if (jqVal("#account").val()=="-1") {
            errors += errorMessageTemplate.replace(/:message/g, "Account not selected")
         }
         if (isEmpty(jqVal("#amount"))) {
            errors += errorMessageTemplate.replace(/:message/g, "Amount cannot be empty")
         }
         if (!isEmpty(jqVal("#amount")) && !canParseDecimal(jqVal("#amount"))) {
            errors += errorMessageTemplate.replace(/:message/g, "Last name can have only have alphabets and space ")
         }
         if (errors) {
            $(".expense-creation-errors").html(errors + "<br/><br/>");
            return false;
         }
        return true;
    });
    $("input[type='text']").blur(function () {
        $(this).val(specialTrim($(this).val()));
    });
</script>


