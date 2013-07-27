<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="row well">
    <div class="span10 account-transfer-errors"></div>
    <form id="account-transfer" method="POST" action="<%=request.getContextPath()%>/account/transaction/transfer">
        <div class="nk-form-section">
            <div class="span10">
                <span class="nk-filed-label"><label for="from-account">From Account</label></span>
                <span class="nk-filed">
                    <form:select type="text" id="from-account" path="accountTransfer.fromAccountId">
                        <form:option value="-1" label="--Please Select--"/>
                        <form:options items="${accounts}" itemValue="id" itemLabel="name"/>
                    </form:select>
                </span>
            </div>
        </div>

        <div class="nk-form-section">
            <div class="span10">
                <span class="nk-filed-label"><label for="to-account">To Account</label></span>
                <span class="nk-filed">
                    <form:select type="text" id="to-account" path="accountTransfer.toAccountId">
                        <form:option value="-1" label="--Please Select--"/>
                        <form:options items="${accounts}" itemValue="id" itemLabel="name"/>
                    </form:select>
                </span>
            </div>
        </div>

        <div class="nk-form-section">
            <div class="span10">
                <span class="nk-filed-label"><label for="amount">Amount</label></span>
                <span class="nk-filed"><form:input type="text" id="amount" path="accountTransfer.amount" maxlength="100"/></span>
            </div>
        </div>
        <br/>

        <div class="span8 nk-form-section">
            <button type="submit" class="btn btn-primary"><i class='icon-ok'></i> &nbsp Save Changes</button>
            <button type="reset" class="btn btn-warning"><i class='icon-refresh'></i> &nbsp Clear</button>
        </div>
    </form>
</div>

<script type="text/javascript">
    $("#account-transfer").submit(function (event) {
        var errors = "";
        var errorMessageTemplate = "<label class='label label-important'>:message</label>"
        $(".account-transfer-errors").html("");

        if (jqVal("#from-account") == "-1") {
            errors += errorMessageTemplate.replace(/:message/g, "From account not selected")
        }
        if (jqVal("#to-account") == "-1") {
            errors += errorMessageTemplate.replace(/:message/g, "To account not selected")
        }
        if (jqVal("#to-account") == jqVal("#from-account")) {
            errors += errorMessageTemplate.replace(/:message/g, "From account and To account cannot be same")
        }
        if (isEmpty(jqVal("#amount"))) {
            errors += errorMessageTemplate.replace(/:message/g, "Amount cannot be empty")
        }
        if (!isEmpty(jqVal("#amount")) && !canParseDecimal(jqVal("#amount"))) {
            errors += errorMessageTemplate.replace(/:message/g, "Last name can have only have alphabets and space ")
        }
        if (errors) {
            $(".account-transfer-errors").html(errors + "<br/><br/>");
            return false;
        }
        return true;
    });
    $("input[type='text']").blur(function () {
        $(this).val(specialTrim($(this).val()));
    });
</script>


