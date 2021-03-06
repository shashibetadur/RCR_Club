<%@ page import="com.rcr.domain.User" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="nk" uri="/WEB-INF/custom-tags.tld" %>
<html>
<head>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/google-web-fonts.css" type="text/css"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap-selected.css" type="text/css"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/extra.css" type="text/css"/>

    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.8.2.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/ap-common.js"></script>
    <title>RCR Raichur</title>
</head>
<body>
<div id="nk-page">
    <div class="nk-header">
        <br/><br/>
    </div>
    <div class="nk-navigation">
        <div class="navbar  navbar-fixed-top">
            <div class="navbar-inner">
                <div class="container-fluid">
                    <a class="brand" href="#">Manage My Club</a>

                    <div>
                        <!--p class="navbar-text pull-right">
                        Logged in as <a href="#" class="navbar-link">Username</a>
                        </p-->
                        <ul class="nav nav-tabs nav-pills">
                            <li class="divider-vertical"><a href="<%=request.getContextPath()%>/home"><i class = 'icon-home'></i>&nbsp Home</a></li>
                            <li class="divider-vertical"><a href="<%=request.getContextPath()%>/member/creationForm"><i class = 'icon-user'></i>&nbsp Members</a></li>
                            <li class="divider-vertical"><a href="<%=request.getContextPath()%>/order/searchOrder"><i class = 'icon-arrow-down'></i>&nbsp Procurement</a></li>
                            <li class="divider-vertical"><a href="<%=request.getContextPath()%>/bill/searchBill"><i class = 'icon-arrow-up'></i>&nbsp Sales</a></li>
                            <li class="divider-vertical"><a href="<%=request.getContextPath()%>/inventory/currentStock"><i class = 'icon-glass'></i>&nbsp Bar</a></li>
                            <li class="divider-vertical"><a href="<%=request.getContextPath()%>/account/list"><i class = 'icon-briefcase'></i>&nbsp Accounts</a></li>
                            <li class="divider-vertical"><a href="<%=request.getContextPath()%>/account/list"><i class = 'icon-list'></i>&nbsp Reports & Charts</a></li>
                            <li class="divider-vertical"><a href="<%=request.getContextPath()%>/bill"><i class = 'icon-th-large'></i>&nbsp Assets</a></li>
                        </ul>
                    </div>
                    <div class="nk-nav-right">
                        <label class="loggedin-username">Logged In As <%=((User) session.getAttribute("user")).getUsername()%>
                        </label>
                        <a class="logout" href="#"><i class = 'icon-wrench'></i>&nbsp Settings</a>
                        <a href="<%=request.getContextPath()%>/logout"><i class = 'icon-off'></i>&nbsp Logout</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <br/>

    <div class="nk-body container-fluid">
        <div class="row-fluid">
            <div class="nk-left span2">
                <tiles:insertAttribute name="context-menu"/>
                <ul class="nk-side-menu nav nav-pills nav-tabs nav-stacked">
                    <li class="nk-side-menu-sep"><a class="btn" href="#">Quick Links</a></li>
                    <nk:security operations="member-search">
                        <li class="nk-side-menu-sep"><a href="<%=request.getContextPath()%>/member/searchForm">Member Dashboard</a></li>
                    </nk:security>
                    <li class="nk-side-menu-sep"><a href="<%=request.getContextPath()%>/member/searchForm">Create Bill</a></li>
                    <li><a href="#">Current Stock</a></li>
                </ul>
            </div>
            <div class="nk-middle span10">
                <tiles:insertAttribute name="main-content"/>
            </div>
            <%--<div class="nk-right span2"></div>--%>
        </div>
    </div>
    <div class="nk-footer well">
            <hr>
            <div class="row-fluid">
                <div class="span12">
                    <div class="span8">
                        <a href="#">Terms of Service</a>
                        <a href="#">Privacy</a>
                        <a href="#">Security</a>
                    </div>
                    <div class="span4">
                        <p class="muted pull-right">© 2013 Company Name. All rights reserved</p>
                    </div>
                </div>
            </div>
    </div>
</div>

</body>
</html>

