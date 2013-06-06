<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ taglib prefix="nk" uri="/WEB-INF/custom-tags.tld" %>
<div class="row well">
    <c:choose>
        <c:when test="${fn:length(memberBillList) > 0}">
            <table class="nk-table table table-bordered table-striped">
                <thead>
                <th></th>
                <th>Bill Id</th>
                <th>Amount</th>
                <th>Bill Date</th>
                <th>Bill Status</th>
                </thead>
                <tbody>
                <c:forEach var="order" items="${memberBillList}">
                    <tr>
                        <td><input type='radio' name='row' value='${order.id}'/></td>
                        <td><label>${order.id}</label></td>
                        <td><label>${order.totalAmount}</label></td>
                        <td><label><fmt:formatDate pattern="dd-MM-yyyy" value="${order.billDate}"/></label></td>
                        <td><label>${order.billStatus}</label></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div class="btn-group">
                    <a href="<%=request.getContextPath()%>/bill/billEdit/" class="btn  edit-order">Edit Bill</a>
                    <a href="<%=request.getContextPath()%>/bill/viewBill" class="btn  view-order">View Bill Details</a>
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
        $('.edit-order').click(function (event) {
            event.preventDefault();
            var selectedBill = $('input:radio[name=row]:checked').val();
            if (selectedBill) document.location.href = ($(this).attr('href') + "/" + selectedBill);
            else return false;
        });
        $('.view-order').click(function (event) {
            event.preventDefault();
            var selectedBill = $('input:radio[name=row]:checked').val();
            if (selectedBill) document.location.href = $(this).attr('href') + "/" + selectedBill;
            else return false;
        });
    });
</script>