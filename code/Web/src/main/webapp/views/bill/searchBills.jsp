<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/datepicker.css" type="text/css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap-datepicker.js"></script>
<div class="order-search-form">
    <div class="row well">
        <form class="nk-search-form form-inline">
            <input type="text" class="order-id input-large" name="billId" placeholder="Bill Id" maxlength="15"/>
            <br/>
            <br/>
            <input type="text" class="phone input-large" name="phone" placeholder="Customer Phone Number"
                   maxlength="15"/>
            <label class="condition">OR</label>
            <input type="text" class="first-name input-large" name="firstName" placeholder="Customer First Name"
                   maxlength="100"/>
            <input type="text" class="last-name input-large" name="lastName" placeholder="Customer Last Name"
                   maxlength="100"/>
            <br/>
            <br/>
            <input type="text" class="from-date input-large" name="fromDate" placeholder="From Date"
                   maxlength="100"/>
            <input type="text" class="to-date input-large" name="toDate" placeholder="To Date" maxlength="100"/>
            <br/>
            <br/>
            <a href="#" class="nk-search-button btn btn-small btn-primary">Search</a>
        </form>
    </div>
    <div class="search-results">

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
        $('.nk-search-button').click(function () {
            var formData = $('form').serialize();
            $.ajax({
                type:'POST',
                url:'<%=request.getContextPath()%>/bill/searchBill',
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
</script>



