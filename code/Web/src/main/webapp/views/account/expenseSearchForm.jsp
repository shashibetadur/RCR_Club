<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/datepicker.css" type="text/css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap-datepicker.js"></script>
<div class="order-search-form">
    <div class="row well">
        <legend>Search Expense Details</legend>
        <form class="nk-search-form form-inline">
            <div class="search-criterion-errors"></div>
            <input type="text" class="expense-type input-large" name="expenseType" placeholder="Expense Type"
                   maxlength="25"/>
            <br/>
            <br/>
            <input type="text" class="from-date input-large" name="startDate" placeholder="From Date" maxlength="100"/>
            <input type="text" class="to-date input-large" name="endDate" placeholder="To Date" maxlength="100"/>
            <br/>
            <br/>

            <div class="nk-form-section">
                <legend/>
            </div>
            <div class="nk-form-section">
                <div class="span10">
                    <a href="#" class="nk-search-button btn btn-primary"><i class='icon-search'></i> &nbsp Search Expense</a>
                </div>
            </div>
        </form>
    </div>
    <div class="search-results">

    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        $(".from-date").datepicker({
            format:"dd-mm-yyyy",
            autoclose:true
        });
        $(".to-date").datepicker({
            format:"dd-mm-yyyy",
            autoclose:true
        });
        $('.nk-search-button').click(function () {
            if (!validateSearchCriterion()) return false;
            var formData = $('form').serialize();
            $.ajax({
                type:'POST',
                url:'<%=request.getContextPath()%>/account/expense/searchForm',
                processData:true,
                async:false,
                timeout:3000,
                data:formData,
                success:function (data) {
                    $(".search-results").html(data);
                },
                error:function (data) {
                    $(".search-results").html("error fetching search results");
                }
            });
        });
    });

    $("input[type='text']").blur(function () {
        $(this).val(specialTrim($(this).val()));
    });

    function validateSearchCriterion() {
        var errors = "";
        var errorMessageTemplate = "<label class='label label-important'>:message</label>"
        $(".search-criterion-errors").html("");
        if (isEmpty(jqVal(".order-id")) && isEmpty(jqVal(".from-date")) && isEmpty(jqVal(".to-date")) && isEmpty(jqVal(".expense-type"))) {
            errors += errorMessageTemplate.replace(/:message/g, "Please enter search criteria")
        }
        if (errors) {
            $(".search-criterion-errors").html(errors + "<br/>");
            return false;
        }
        return true;
    }
</script>