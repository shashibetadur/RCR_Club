<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<ul class="nk-side-menu nav nav-tabs nav-pills nav-stacked">
    <li class="nk-side-menu-sep"><a href="<%=request.getContextPath()%>/member/creationForm">Create Member</a></li>
    <li class="nk-side-menu-sep"><a href="<%=request.getContextPath()%>/member/EditForm">Edit Member</a></li>
    <li class="nk-side-menu-sep"><a href="<%=request.getContextPath()%>/member/viewForm">View Customer</a></li>
</ul>