<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="nk" uri="/WEB-INF/custom-tags.tld" %>
<div class="row well">
    <div class="span10 search-results-errors"></div>
    <c:choose>
        <c:when test="${fn:length(expensePayments) > 0}">
            <table class="nk-table table table-bordered table-striped">
                <thead>
                <th></th>
                <th>Expense Type</th>
                <th>Account</th>
                <th>Amount</th>
                <th>Date</th>
                <th>Notes</th>
                </thead>
                <tbody>
                <c:forEach var="expensePayment" items="${expensePayments}">
                    <tr>
                        <td><input type='radio' name='row' value='${expensePayment.transactionId}'/></td>
                        <td><label>${expensePayment.expenseType}</label></td>
                        <td><label>${expensePayment.accountName}</label></td>
                        <td><label>${expensePayment.amount}</label></td>
                        <td><label>${expensePayment.date}</label></td>
                        <td><label>${expensePayment.notes}</label></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <legend/>
            <div class="nk-form-section">
                <div class="span10">
                    <a href="<%=request.getContextPath()%>/account/expense/editForm/" class="btn btn-primary edit-expense-payment"><i class='icon-edit'></i> &nbsp Editr</a>
                    <a href="<%=request.getContextPath()%>/account/expense/viewForm" class="btn  btn-info view-expense-payment"><i class='icon-eye-open'></i> &nbsp View Details</a>
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
        $('.view-expense-payment').click(function (event) {

            handleClick(this);
        });
        $('.edit-expense-payment').click(function (event) {
            handleClick(this);
        });
        function handleClick(element) {
            event.preventDefault();
            var selectedMember = $('input:radio[name=row]:checked').val();
            if (selectedMember) document.location.href = $(element).attr('href') + "/" + selectedMember;
            else {
                var errors = "";
                var errorMessageTemplate = "<label class='label label-important'>:message</label>";
                errors += errorMessageTemplate.replace(/:message/g, "Please select entry!");
                $(".search-results-errors").html(errors + "<br/>");
            }
        }
    });
</script>



