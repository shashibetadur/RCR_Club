<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<form class=" account-create-form">
    <form:hidden path="account.id"/>
    <div class="account-save-errors"></div>
    <div>
        <label for="name">Name</label><form:input type="text" id="name" path="account.name" maxlength="100"/>
        <label for="number">Number</label><form:input type="text" id="number" path="account.accountNumber" maxlength="100"/>
    </div>
    <div>
        <a href="#" class="btn btn-small btn-primary submit">Save changes</a>
    </div>
</form>
<script type="text/javascript">
    $('.submit').click(function () {
        if (!validAccountDetails()) return false;
        var formData = $('.account-create-form').serialize();
        $.ajax({
            type:'POST',
            url:'<%=request.getContextPath()%>/account/save',
            processData:true,
            async:false,
            timeout:3000,
            data:formData,
            success:function (data) {
                $(".close").click();
            },
            error:function (data) {
                $('.account-save-errors').html("<label class='label error'>Save Failed!!!</label>")
            }
        });
    });
    function validAccountDetails() {
        var errors = "";
        var errorMessageTemplate = "<label class='label label-important'>:message</label>"
        $(".account-save-errors").html("");
        if (isEmpty(jqVal("#name"))) {
            errors += errorMessageTemplate.replace(/:message/g, "Name cannot be empty")
        }
        if (!isEmpty(jqVal("#name")) && !canParseOnlyAlphabetsWithSpace(jqVal("#name"))) {
            errors += errorMessageTemplate.replace(/:message/g, "Name can only have alphabets with space")
        }
        if (errors) {
            $(".account-save-errors").html(errors + "<br/");
            return false;
        }
        return true;
    }
    $("input[type='text']").blur(function () {
        $(this).val(specialTrim($(this).val()));
    });
</script>





