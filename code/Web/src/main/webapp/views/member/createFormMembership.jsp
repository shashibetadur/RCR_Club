<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/datepicker.css" type="text/css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap-datepicker.js"></script>

<div class="row well">
    <form method="POST" action="<%=request.getContextPath()%>/member/membership/renew">
        <legend>Membership Renewal Form</legend>
        <form:hidden path="membershipDetail.id"/>
        <form:hidden path="membershipDetail.memberId"/>
        <div class="nk-form-section">
            <div class="span10">
                <span class="nk-filed-label"><label for="membership-type">Membership Type</label></span>
                <span class="nk-filed">
                    <form:select type="text" id="membership-type" path="membershipDetail.membershipType.id">
                        <form:option value="-1" label="--Please Select--"/>
                        <form:options items="${membershipTypes}" itemValue="id" itemLabel="name"/>
                    </form:select>
                </span>
            </div>
        </div>
        <br/>

        <div class="nk-form-section">
            <div class="span10">
                <span class="nk-filed-label"><label for="start-date">Start Date</label></span>
                <span class="nk-filed"><form:input type="text" readonly="true" id="start-date" path="membershipDetail.startDate"
                                                   maxlength="100"/></span>
            </div>
        </div>
        <br/>

        <div class="nk-form-section">
            <div class="span10">
                <span class="nk-filed-label"><label for="end-date">End Date</label></span>
                <span class="nk-filed"><form:input type="text" readonly="true" id="end-date" path="membershipDetail.endDate"
                                                   maxlength="100"/></span>
            </div>
        </div>
        <br/>

        <div class="nk-form-section">
            <div class="span10">
                <span class="nk-filed-label"><label for="enrollment-fees">Enrollment Fees</label></span>
                <span class="nk-filed"><form:input type="text" readonly="true" id="enrollment-fees"
                                                   path="membershipDetail.enrollmentFees" maxlength="100"/></span>
            </div>
        </div>
        <br/>

        <div class="nk-form-section">
            <div class="span10">
                <span class="nk-filed-label"><label for="fees">Fees</label></span>
                <span class="nk-filed"><form:input type="text" readonly="true" id="fees" path="membershipDetail.fees"
                                                   maxlength="100"/></span>
            </div>
        </div>
        <br/>

        <div class="span8 nk-form-section">
            <button type="submit" class="btn btn-primary">Save changes</button>
            <button type="reset" class="btn">Cancel</button>
        </div>
    </form>
</div>
<script type="text/javascript">
    $("#membership-type").change(function () {
        var selectedType = $(this).val();
        if (selectedType > 0) {
            $.getJSON("<%=request.getContextPath()%>/member/type/" + selectedType, {}, function (data) {
                var membershipType = eval(data);
                $('#enrollment-fees').val(membershipType.enrollmentFees);
                $('#fees').val(membershipType.fees);
                var startDateTokens = $("#start-date").val().split("-");
                var startDate = new Date(startDateTokens[2],startDateTokens[1]-1,startDateTokens[0]);
                startDate.setDate(startDate.getDate()+membershipType.validity);
                $("#end-date").val(startDate.getDate()+"-"+(startDate.getMonth()+1)+"-"+startDate.getYear());
            });
        }
    });

</script>



