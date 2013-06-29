<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<form class=" membership-type-create-form">
    <form:hidden path="memberType.id"/>
    <div class="membership-type-save-errors"></div>
    <div>
        <label for="name">Name</label><form:input type="text" id="name" path="memberType.name" maxlength="100"/>
        <label for="enrollment-fees">Enrollment Fees</label><form:input type="text" id="enrollment-fees" path="memberType.enrollmentFees" maxlength="100"/>
        <label for="validity">Validity In Month(s)</label><form:input type="text" id="validity" path="memberType.validity" maxlength="100"/>
        <label for="fees">Fees</label><form:input type="text" id="fees" path="memberType.fees" maxlength="100"/>
    </div>
    <legend/>
    <div>
        <a href="#" class="btn btn-primary submit"><i class = 'icon-ok'></i> &nbsp Save Changes</a>
        <button type="reset" class="btn btn-warning"><i class = 'icon-refresh'></i> &nbsp Clear</button>
    </div>
</form>
<script type="text/javascript">
    $('.submit').click(function () {
        if (!validMembershipTypeDetails()) return false;
        var formData = $('.membership-type-create-form').serialize();
        $.ajax({
            type:'POST',
            url:'<%=request.getContextPath()%>/member/type/save',
            processData:true,
            async:false,
            timeout:3000,
            data:formData,
            success:function (data) {
                $(".close").click();
            },
            error:function (data) {
                $('.membership-type-save-errors').html("<label class='label error'>Save Failed!!!</label>")
            }
        });
    });
    function validMembershipTypeDetails() {
        var errors = "";
        var errorMessageTemplate = "<label class='label label-important'>:message</label>"
        $(".membership-type-save-errors").html("");
        if (isEmpty(jqVal("#name"))) {
            errors += errorMessageTemplate.replace(/:message/g, "Name cannot be empty")
        }
        if (!isEmpty(jqVal("#name")) && !canParseOnlyAlphabets(jqVal("#name"))) {
            errors += errorMessageTemplate.replace(/:message/g, "Name can only have alphabets")
        }
        if (isEmpty(jqVal("#validity"))) {
            errors += errorMessageTemplate.replace(/:message/g, "Validity cannot be empty")
        }
        if (!isEmpty(jqVal("#validity")) && !canParseNumber(jqVal("#validity"))) {
            errors += errorMessageTemplate.replace(/:message/g, "Validity can only have digits")
        }
        if (isEmpty(jqVal("#enrollment-fees"))) {
            errors += errorMessageTemplate.replace(/:message/g, "Enrollment fees cannot be empty")
        }
        if (!isEmpty(jqVal("#enrollment-fees")) && !canParseDecimal(jqVal("#enrollment-fees"))) {
            errors += errorMessageTemplate.replace(/:message/g, "Enrollment fees can only have digits with decimal")
        }
        if (isEmpty(jqVal("#fees"))) {
            errors += errorMessageTemplate.replace(/:message/g, "Fees cannot be empty")
        }
        if (!isEmpty(jqVal("#fees")) && !canParseDecimal(jqVal("#fees"))) {
            errors += errorMessageTemplate.replace(/:message/g, "Fees can only have digits with decimal")
        }
        if (errors) {
            $(".membership-type-save-errors").html(errors + "<br/");
            return false;
        }
        return true;
    }
    $("input[type='text']").blur(function () {
        $(this).val(specialTrim($(this).val()));
    });
</script>





