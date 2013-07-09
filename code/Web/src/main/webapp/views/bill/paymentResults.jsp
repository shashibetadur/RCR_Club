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
        <c:when test="${fn:length(memberBillPayments) > 0}">
            <table class="nk-table table table-bordered table-striped">
                <thead>
                <th>Payment Id</th>
                <th>Account Id</th>
                <th>Amount</th>
                <th>Payment Date</th>
                <th>Bills</th>
                </thead>
                <tbody>
                <c:forEach var="payment" items="${memberBillPayments}">
                    <tr>
                        <td><label>${payment.transactionId}</label></td>
                        <td><label>${payment.accountId}</label></td>
                        <fmt:setLocale value="en_IN" scope="session"/>
                        <td><label><fmt:formatNumber value="${payment.amount}" type="currency"/></label></td>
                        <td><label><fmt:formatDate pattern="dd-MM-yyyy" value="${payment.date}"/></label></td>
                        <td>
                            <a target="_blank" href="<%=request.getContextPath()%>/bill/payment/fetchBillsForPayment/${payment.transactionId}"><i class = 'icon-book'></i></a>
                            <a name="${payment.transactionId}"></a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            </br><legend></legend>
            <br/><br/>
        </c:when>
        <c:otherwise>
            <label>Search Results not found!!!</label>
        </c:otherwise>
    </c:choose>
    <div class="search-bills">

    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
    });
</script>