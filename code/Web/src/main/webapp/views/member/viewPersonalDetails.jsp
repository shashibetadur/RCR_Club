<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="nk" uri="/WEB-INF/custom-tags.tld" %>
<div class="nk-form-section">
    <div class="span10">
        <span class="nk-filed-label"><label>Name:</label></span>
        <span class="nk-filed"><label>${member.personalDetails.firstName} ${member.personalDetails.lastName}</label></span>
    </div>
</div>
<div class="nk-form-section">
    <div class="span10">
        <span class="nk-filed-label"><label>Phone:</label></span>
        <span class="nk-filed">
            <c:choose>
                <c:when test="${fn:length(member.personalDetails.phoneList) > 0}">
                    <c:forEach varStatus="index" var="phone" items="${member.personalDetails.phoneList}">
                        <label class="label" style="display: inline-block;">${phone.displayPhone}</label>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <label class="label" style="display: inline-block;">NA</label>
                </c:otherwise>
            </c:choose>
        </span>
    </div>
</div>
<div class="nk-form-section">
    <div class="span10">
        <span class="nk-filed-label"><label>Address:</label></span>
        <span class="nk-filed">
            <c:choose>
                <c:when test="${fn:length(member.personalDetails.addressList) > 0}">
                    <c:forEach varStatus="index" var="address" items="${member.personalDetails.addressList}">
                        <label class="label" style="display: inline-block;">${address.displayAddress}</label>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <label class="label" style="display: inline-block;">NA</label>
                </c:otherwise>
            </c:choose>
        </span>
    </div>
</div>
<div class="nk-form-section">
    <div class="span10">
        <span class="nk-filed-label">
            <nk:security operations="member-edit">
                <a href="<%=request.getContextPath()%>/member/editForm/${member.id}" class="btn"><i class = 'icon-edit'></i> &nbsp Edit</a><br/><br/>
            </nk:security>
        </span>
    </div>
</div>
