<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="nk" uri="/WEB-INF/custom-tags.tld" %>
<ul class="nk-side-menu nav nav-tabs nav-pills nav-stacked">
    <li class="nk-side-menu-sep"><a class="btn" href="#">Member Context</a></li>
    <nk:security operations="membership-type-list">
        <li class="nk-side-menu-sep"><a href="<%=request.getContextPath()%>/member/type/list">Membership Types</a></li>
    </nk:security>
    <nk:security operations="member-create">
        <li class="nk-side-menu-sep"><a href="<%=request.getContextPath()%>/member/creationForm">Create Member</a></li>
    </nk:security>
    <nk:security operations="member-search">
        <li class="nk-side-menu-sep"><a href="<%=request.getContextPath()%>/member/searchForm">Members</a></li>
    </nk:security>
    <nk:security operations="member-visit-view">
        <li class="nk-side-menu-sep"><a href="<%=request.getContextPath()%>/member/visit">Members Visit</a></li>
    </nk:security>
</ul>