<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ taglib prefix="nk" uri="/WEB-INF/custom-tags.tld" %>
<div class="row well">
    <div class="span10 search-edit-errors"></div>
    <c:choose>
        <c:when test="${fn:length(memberBillList) > 0}">
            <table class="nk-table table table-bordered table-striped">
                <thead>
                <th></th>
                <th>Bill Id</th>
                <th>Member</th>
                <th>Amount</th>
                <th>Bill Date</th>
                <th>Bill Status</th>
                </thead>
                <tbody>
                <c:forEach var="order" items="${memberBillList}">
                    <tr>
                        <td><input type='radio' name='row' value='${order.id}:${order.billStatus}'/></td>
                        <td><input type='hidden' name='state' value='${order.id}'/><label>${order.id}</label></td>
                        <td><label>${order.member.personalDetails.firstName} &nbsp ${order.member.personalDetails.lastName}</label></td>
                        <fmt:setLocale value="en_IN" scope="session"/>
                        <td><label><fmt:formatNumber value="${order.totalAmount}" type="currency"/></label></td>
                        <td><label><fmt:formatDate pattern="dd-MM-yyyy" value="${order.billDate}"/></label></td>
                        <td><label>${order.billStatus}</label></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            </br><legend></legend>
            <div class="nk-form-section">
                <div class="span10">
                    <a href="<%=request.getContextPath()%>/bill/billEdit/" class="btn btn-primary edit-bill"><i class = 'icon-edit'></i> &nbsp Edit Bill</a>
                    <a href="<%=request.getContextPath()%>/bill/viewBill" class="btn btn-info view-order"><i class = 'icon-eye-open'></i> &nbsp View Bill</a>
                </div>
            </div>
            <br/><br/>
        </c:when>
        <c:otherwise>
            <label>Search Results not found!!!</label>
        </c:otherwise>
    </c:choose>
</div>
<script type="text/javascript">
    $(function () {
        $('.edit-bill').click(function (event) {
            event.preventDefault();
            var selectedBillAndStatus = $('input:radio[name=row]:checked').val();
            var selectedBill = selectedBillAndStatus.split(":")[0];
            var selectedBillStatus = selectedBillAndStatus.split(":")[1];

                    var errors = "";
                    var errorMessageTemplate = "<label class='label label-important'>:message</label>"
                    $(".search-edit-errors").html("");
                    if(selectedBillStatus == "CLOSED" || selectedBillStatus == "PAID" ){
                        errors += errorMessageTemplate.replace(/:message/g, "Closed/Paid Bills Cannot be Edited");
                    }
                    if (errors) {
                        $(".search-edit-errors").html(errors + "<br/><br/>");
                        return false;
                    }

            if (selectedBill) document.location.href = ($(this).attr('href') + "/" + selectedBill);
            else return false;
        });
        $('.view-order').click(function (event) {
            event.preventDefault();
            var selectedBillAndStatus = $('input:radio[name=row]:checked').val();
            var selectedBill = selectedBillAndStatus.split(":")[0];
            if (selectedBill) document.location.href = $(this).attr('href') + "/" + selectedBill;
            else return false;
        });
    });
</script>