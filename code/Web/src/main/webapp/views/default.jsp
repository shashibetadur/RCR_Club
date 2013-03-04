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
                            <li class="divider-vertical"><a href="<%=request.getContextPath()%>/home">Home</a></li>
                            <li class="divider-vertical"><a href="<%=request.getContextPath()%>/member/creationForm">Members</a></li>
                            <li class="divider-vertical"><a href="<%=request.getContextPath()%>/bar/material/createForm">Bar</a></li>
                            <li class="divider-vertical"><a href="<%=request.getContextPath()%>/order">Accounts</a></li>
                            <li class="divider-vertical"><a href="<%=request.getContextPath()%>/bill">Assets</a></li>
                        </ul>
                    </div>
                    <div class="nk-nav-right">
                        <label class="loggedin-username">Logged In As <%=((User) session.getAttribute("user")).getUsername()%>
                        </label>
                        <a class="logout" href="#">Settings</a>
                        <a href="<%=request.getContextPath()%>/logout">Logout</a>
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
                    <li class="nk-side-menu-sep"><a href="#">Create Bill</a></li>
                    <li><a href="#">Update Inventory</a></li>
                </ul>
            </div>
            <div class="nk-middle span10">
                <tiles:insertAttribute name="main-content"/>
            </div>
            <%--<div class="nk-right span2"></div>--%>
        </div>
    </div>
    <div class="nk-footer well">
        <p>this is footer</p>
    </div>
</div>

</body>
</html>

