<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="row well">
    <form id="member-creation" method="POST" action="<%=request.getContextPath()%>/member/save">
        <legend>New Member Form</legend>
        <form:hidden path="member.id"/>
        <form:hidden path="member.personalDetails.id"/>
        <div class="span10 member-creation-errors"></div>
        <div class="nk-form-section">
            <div class="span5">
                <span class="nk-filed-label"><label for="firstname">First Name</label></span>
                <span class="nk-filed"><form:input type="text" id="firstname" path="member.personalDetails.firstName" maxlength="100"/></span>
            </div>
            <div class="span5">
                <span class="nk-filed-label"><label for="lastname">Last Name</label></span>
                <span class="nk-filed"><form:input type="text" id="lastname" path="member.personalDetails.lastName" maxlength="100"/></span>
            </div>
        </div>
        <br/>

        <div class="nk-form-section">
            <div class="span5">
                <span class="nk-filed-label"><label for="designation">Designation</label></span>
                <span class="nk-filed"><form:input type="text" id="designation" path="member.personalDetails.designation" maxlength="100"/></span>
            </div>
            <div class="span5">
                <span class="nk-filed-label"><label for="status">Status</label></span>
                <span class="nk-filed"><form:input type="text" id="status" path="member.status" maxlength="100"/></span>
            </div>
        </div>
        <br/>
        <div class="nk-phone-list nk-form-section span4">
            <h4>Phone List</h4>

            <div class="phone-list-location">
                <table class="nk-table table table-striped table-bordered">
                    <thead>
                    <th></th>
                    <th>Type</th>
                    <th>Number</th>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>

            <div class="phone-buttons btn-group">
                <a class="btn add"><i class = 'icon-plus'></i> &nbsp Add</a>
                <a class="btn edit"><i class = 'icon-edit'></i> &nbsp Edit</a>
                <a class="btn delete"><i class = 'icon-trash'></i> &nbsp Delete</a>
            </div>
        </div>
        <div class="nk-address-list nk-form-section span10">
            <h4>Address List</h4>

            <div class="address-list-location">
                <table class="nk-table table table-striped table-bordered">
                    <thead>
                    <th></th>
                    <th>Address Line One</th>
                    <th>Address Line Two</th>
                    <th>Locality</th>
                    <th>City</th>
                    <th>Pin Code</th>
                    <th>State</th>
                    <th>Landmark</th>
                    <th>Address Type</th>
                    <th>Notes</th>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>

            <div class="address-buttons btn-group">
                <a class="btn add"><i class = 'icon-plus'></i> &nbsp Add</a>
                <a class="btn edit"><i class = 'icon-edit'></i> &nbsp Edit</a>
                <a class="btn delete"><i class = 'icon-trash'></i> &nbsp Delete</a>
            </div>
        </div>
        <br/>
        <div class="span8 nk-form-section">
            <button type="submit" class="btn btn-primary"><i class = 'icon-ok'></i> &nbsp Save Changes</button>
            <button type="reset" class="btn btn-warning"><i class = 'icon-refresh'></i> &nbsp Clear</button>
        </div>
    </form>
</div>
<jsp:include page="../phone.jsp"/>
<jsp:include page="../address.jsp"/>

<script type="text/javascript">
    $("#member-creation").submit(function() {
        var errors = "";
        var errorMessageTemplate = "<label class='label label-important'>:message</label>"
        $(".member-creation-errors").html("");
        if (isEmpty(jqVal("#firstname"))) {
            errors += errorMessageTemplate.replace(/:message/g,"First name cannot be empty")
        }
        if (!isEmpty(jqVal("#firstname")) && !canParseOnlyAlphabetsWithSpace(jqVal("#firstname"))) {
            errors += errorMessageTemplate.replace(/:message/g,"First name can have only have alphabets and space ")
        }
        if (isEmpty(jqVal("#lastname"))) {
            errors += errorMessageTemplate.replace(/:message/g,"Last name cannot be empty")
        }
        if (!isEmpty(jqVal("#lastname")) && !canParseOnlyAlphabetsWithSpace(jqVal("#lastname"))) {
            errors += errorMessageTemplate.replace(/:message/g,"Last name can have only have alphabets and space ")
        }
        if(errors){
            $(".member-creation-errors").html(errors+"<br/><br/>");
            return false;
        }
        return true;
    });
    $("input[type='text']").blur(function () {
        $(this).val(specialTrim($(this).val()));
    });
</script>


