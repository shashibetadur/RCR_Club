<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<c:choose>
    <c:when test="${fn:length(membershipDetails.membershipDetailList) > 0}">
        <div class="nk-form-section">
            <div class="span10">
                <table class="nk-table table table-striped table-bordered">
                    <thead>
                    <th></th>
                    <th>Type</th>
                    <th>Start Date</th>
                    <th>End Date</th>
                    <th>Enrollment Fees</th>
                    <th>Fees</th>
                    </thead>
                    <tbody>
                    <c:forEach items="${membershipDetails.membershipDetailList}" var="membershipDetail">
                        <tr>
                            <td><input type="radio" name="row-membership-detail" value="${membershipDetail.id}"/></td>
                            <td><label>${membershipDetail.membershipType.name}</label></td>
                            <fmt:setLocale value="en_IN" scope="session"/>
                            <td><label><fmt:formatDate pattern="dd-MM-yyyy" value="${membershipDetail.startDate}"/></label></td>
                            <td><label><fmt:formatDate pattern="dd-MM-yyyy" value="${membershipDetail.endDate}"/></label></td>
                            <td><label><fmt:formatNumber value="${membershipDetail.enrollmentFees}" type="currency"/></label></td>
                            <td><label><fmt:formatNumber value="${membershipDetail.fees}" type="currency"/></label></td>
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
                        <a class="btn edit-membership"
                           href="<%=request.getContextPath()%>/member/membership/edit"><i class = 'icon-edit'></i> &nbsp Edit</a>
                        <a class="btn delete-membership" href="#"><i class = 'icon-trash'></i> &nbsp Delete</a>
                    </div>
                    <br/>
                </span>
            </div>
        </div>
        <script type="text/javascript">
            $('.edit-membership').click(function (event) {
                event.preventDefault();
                var selectedIndex = $('input:radio[name=row-membership-detail]:checked').val();
                if (selectedIndex) document.location.href = ($(this).attr('href') + "/" + selectedIndex);
                else return false;
            });
        </script>
    </c:when>
    <c:otherwise>
        <label>None</label>
    </c:otherwise>
</c:choose>


