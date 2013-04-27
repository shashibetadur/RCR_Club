<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="nk" uri="/WEB-INF/custom-tags.tld" %>
<div class="nk-form-section">
    <div class="span10">
        <span class="nk-filed-label">
            <div class="btn-group">
                <nk:security operations="membership-renew">
                    <a class="btn" href="<%=request.getContextPath()%>/member/membership/renew/${member.id}">Renew</a>
                </nk:security>
                <a class="btn" href="<%=request.getContextPath()%>/member/membership/payment/createForm/${member.id}">Make Payment</a>
            </div>
            <br/>
        </span>
    </div>
</div>
