<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<c:choose>
    <c:when test="${fn:length(membershipPayments) > 0}">
        <div class="nk-form-section">
            <div class="span10">
                <table class="nk-table table table-striped table-bordered">
                    <thead>
                    <th></th>
                    <th>Amount</th>
                    <th>Date</th>
                    <th>Account</th>
                    <th>Notes</th>
                    </thead>
                    <tbody>
                    <c:forEach items="${membershipPayments}" var="membershipPayment">
                        <tr>
                            <td><input type="radio" name="row-membership-payment" value="${membershipPayment.transactionId}"/></td>
                            <td><label>${membershipPayment.amount}</label></td>
                            <td><label><fmt:formatDate pattern="dd-MM-yyyy" value="${membershipPayment.date}"/></label></td>
                            <td><label>${membershipPayment.accountName}</label></td>
                            <td><label>${membershipPayment.notes}</label></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

        <div class="nk-form-section">
            <div class="span10">
                <span class="nk-filed-label">
                    <div class="btn-group">
                        <a class="btn edit-membership-payment"
                           href="<%=request.getContextPath()%>/member/membership/payment/editForm"><i class = 'icon-edit'></i> &nbsp Edit</a>
                        <a class="btn delete-membership-payment" href="#"><i class = 'icon-trash'></i> &nbsp Delete</a>
                    </div>
                    <br/>
                </span>
            </div>
        </div>
        <script type="text/javascript">
            $('.edit-membership-payment').click(function (event) {
                event.preventDefault();
                var selectedIndex = $('input:radio[name=row-membership-payment]:checked').val();
                if (selectedIndex) document.location.href = ($(this).attr('href') + "/" + selectedIndex);
                else return false;
            });
        </script>
    </c:when>
    <c:otherwise>
        <label>None</label>
    </c:otherwise>
</c:choose>


