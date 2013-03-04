<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="nk" uri="/WEB-INF/custom-tags.tld" %>
<div class="row well">
    <c:choose>
        <c:when test="${fn:length(memberList) > 0}">
            <table class="nk-table table table-bordered table-striped">
                <thead>
                <th></th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Phone List</th>
                <th>Address List</th>
                </thead>
                <tbody>
                <c:forEach var="member" items="${memberList}">
                    <tr>
                        <td><input type='radio' name='row' value='${member.id}'/></td>
                        <td><label>${member.personalDetails.firstName}</label></td>
                        <td><label>${member.personalDetails.lastName}</label></td>
                        <td>
                            <c:choose>
                                <c:when test="${fn:length(member.personalDetails.phoneList) > 0}">
                                    <c:forEach varStatus="index" var="phone"
                                               items="${member.personalDetails.phoneList}">
                                        <span class="label">${phone.displayPhone}</span>
                                        <c:if test="${!index.last}">
                                            <br/><br/>
                                        </c:if>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <span class="label">NA</span>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${fn:length(member.personalDetails.addressList) > 0}">
                                    <c:forEach varStatus="index" var="address"
                                               items="${member.personalDetails.addressList}">
                                        <span class="label">${address.displayAddress}</span>
                                        <c:if test="${!index.last}">
                                            <br/><br/>
                                        </c:if>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <span class="label">NA</span>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div class="btn-group">
                <nk:security operations="member-view">
                    <a href="<%=request.getContextPath()%>/member/viewForm" class="btn  view-member">View Member Details</a>
                </nk:security>
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
        $('.view-member').click(function (event) {
            event.preventDefault();
            var selectedMember = $('input:radio[name=row]:checked').val();
            if (selectedMember) document.location.href = $(this).attr('href') + "/" + selectedMember;
            else return false;
        });
    });
</script>



