<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="nk" uri="/WEB-INF/custom-tags.tld" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<div class="nk-form-section">
    <div class="span10">
        <span class="nk-filed-label"><label>Current Membership:</label></span>
        <span class="nk-filed"><label>${memberSummary.membershipType}</label></span>
    </div>
</div>
<div class="nk-form-section">
    <div class="span10">
        <span class="nk-filed-label"><label>Membership Status:</label></span>
        <span class="nk-filed"><label>${memberSummary.status}</label></span>
    </div>
</div>
<div class="nk-form-section">
    <div class="span10">
        <span class="nk-filed-label"><label>Membership Expiry Date:</label></span>
        <span class="nk-filed"><label><fmt:formatDate pattern="dd-MM-yyyy" value="${memberSummary.expiryDate}"/></label></span>
    </div>
</div>
<div class="nk-form-section">
    <div class="span10">
        <span class="nk-filed-label"><label>Membership Pending Amount:</label></span>
        <fmt:setLocale value="en_IN" scope="session"/>
        <span class="nk-filed"><label><fmt:formatNumber value="${memberSummary.pendingAmount}" type="currency"/></label></span>
    </div>
</div>
<div class="nk-form-section">
    <div class="span10">
        <span class="nk-filed-label">
            <div class="btn-group">
                <nk:security operations="membership-renew">
                    <a class="btn" href="<%=request.getContextPath()%>/member/membership/renew/${member.id}"><i class = 'icon-repeat'></i> &nbsp Renew</a>
                </nk:security>
                <a class="btn" href="<%=request.getContextPath()%>/member/membership/payment/createForm/${member.id}"><i class = 'icon-book'></i> &nbsp Make
                    Payment</a>
            </div>
            <br/>
        </span>
    </div>
</div>
