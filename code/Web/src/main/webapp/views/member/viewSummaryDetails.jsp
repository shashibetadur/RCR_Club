<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="nk-form-section">
    <div class="span10">
        <span class="nk-filed-label">
            <div class="btn-group">
                <a class="btn" href="<%=request.getContextPath()%>/member/membership/renew/${member.id}">Renew</a>
                <a class="btn" href="<%=request.getContextPath()%>/member/membership/payment/${member.id}">Make Payment</a>
            </div>
            <br/>
        </span>
    </div>
</div>
