<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/datepicker.css" type="text/css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap-datepicker.js"></script>
<div class="order-search-form">
    <div class="row well">
        <legend>Search Purchase Order</legend>
        <form class="nk-search-form form-inline">
            <input type="text" class="order-id input-large" name="orderId" placeholder="Order Id" maxlength="15"/>
            <br/>
            <br/>
            <input type="text" class="from-date input-large" name="fromDate" placeholder="From Date" maxlength="100"/>
            <input type="text" class="to-date input-large" name="toDate" placeholder="To Date" maxlength="100"/>
            <br/>
            <br/>

            <div>
                <div class="order-status-selection">
                    <label>Order Status</label>
                    <br/>
                    <div class="list well">
                        <c:forEach var="orderState" items="${orderStates}">
                            <input class="order-status" type="checkbox" name="orderStatusList"
                                   value="${orderState.key}"><label>${orderState.value}</label><br>
                        </c:forEach>
                    </div>
                </div>
            </div>
            <div class="nk-form-section">
                <legend/>
            </div>
            <div class="nk-form-section">
                <div class="span10">
                    <a href="#" class="nk-search-button btn btn-primary"><i class = 'icon-search'></i> &nbsp Search Order</a>
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
            format:"dd-mm-yyyy"
        });
        $(".to-date").datepicker({
            format:"dd-mm-yyyy"
        });
        $('.nk-search-button').click(function () {
            var formData = $('form').serialize();
            $.ajax({
                type:'POST',
                url:'<%=request.getContextPath()%>/order/searchForm',
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