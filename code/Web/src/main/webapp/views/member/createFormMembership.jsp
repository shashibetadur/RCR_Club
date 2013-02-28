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
            <div class="span5">
                <span class="nk-filed-label"><label for="start-date">Start Date</label></span>
                <span class="nk-filed"><form:input type="text" id="start-date" path="membershipDetail.startDate" maxlength="100"/></span>
            </div>
            <div class="span5">
                <span class="nk-filed-label"><label for="end-date">End Date</label></span>
                <span class="nk-filed"><form:input type="text" id="end-date" path="membershipDetail.endDate" maxlength="100"/></span>
            </div>
        </div>
        <br/>

        <div class="nk-form-section">
            <div class="span5">
                <span class="nk-filed-label"><label for="enrollment-fees">Enrollment Fees</label></span>
                <span class="nk-filed"><form:input type="text" id="enrollment-fees" path="membershipDetail.enrollmentFees" maxlength="100"/></span>
            </div>
            <div class="span5">
                <span class="nk-filed-label"><label for="fees">Fees</label></span>
                <span class="nk-filed"><form:input type="text" id="fees" path="membershipDetail.fees" maxlength="100"/></span>
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
    $("#start-date").datepicker({
        format:"dd-mm-yyyy"
    });
    $("#end-date").datepicker({
        format:"dd-mm-yyyy"
    });

</script>



