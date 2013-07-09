<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/datepicker.css" type="text/css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap-datepicker.js"></script>
<div class="order-search-form">
    <div class="row well">
        <legend>Search Payments</legend>
        <form class="nk-search-form form-inline">
            <div class="search-criterion-errors"></div>
            <input type='hidden' class='memberId' value="${memberDetails.personalDetails.id}"/>
            <div class="nk-form-section">
                    <span class="nk-filed-label"><label>Name :</label></span>
                    <span class="nk-filed"><label>${memberDetails.personalDetails.firstName} &nbsp ${memberDetails.personalDetails.lastName}</label></span>
            </div>
            <br/>
            <div class="nk-form-section">
                <input type="text" class="from-date input-large" name="fromDate" placeholder="From Date" maxlength="100"/>
                <input type="text" class="to-date input-large" name="toDate" placeholder="To Date" maxlength="100"/>
            </div>
            <br/>
            <legend></legend>
            <div class="nk-form-section">
                <div class="span10">
                    <a href="#" id="fetch_payments" class="nk-search-button btn btn-primary"><i class = 'icon-search'></i> &nbsp Search Payments</a>
                </div>
            </div>
        </form>
    </div>
    <div class="sales_payments">
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        $(".from-date").datepicker({
            format:"dd-mm-yyyy"
        });
        $(".to-date").datepicker({
            format:"dd-mm-yyyy"
        });

        $('#fetch_payments').bind("click",function(){
            var paymentsInRangeUrl = "<%=request.getContextPath()%>/bill/payment/searchPaymentOfMember/?fromDate="+$('.from-date').val()+"&toDate="+$('.to-date').val()+"&memberId="+$('.memberId').val();
            $.ajax({
                url : paymentsInRangeUrl,
                processData : true,
                success : function(data) {
                    $(".sales_payments").html(data);
                }
            })
        });
    });

    $("input[type='text']").blur(function () {
        $(this).val(specialTrim($(this).val()));
    });

    function validateSearchCriterion(){
        var fields = $("input[name='billStatusList']").serializeArray();
        var errors = "";
        var errorMessageTemplate = "<label class='label label-important'>:message</label>"
        $(".search-criterion-errors").html("");
        if (isEmpty(jqVal(".bill-id")) && isEmpty(jqVal(".member-id")) &&  isEmpty(jqVal(".phone")) && isEmpty(jqVal(".first-name"))
            && isEmpty(jqVal(".last-name")) && fields.length == 0 && isEmpty(jqVal(".from-date")) &&  isEmpty(jqVal(".to-date"))) {
            errors += errorMessageTemplate.replace(/:message/g, "Please enter search criteria")
        }
        if (errors) {
            $(".search-criterion-errors").html(errors + "<br/>");
            return false;
        }
        return true;
    }
</script>



