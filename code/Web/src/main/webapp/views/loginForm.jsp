<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/google-web-fonts.css" type="text/css"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap-selected.css" type="text/css"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/extra.css" type="text/css"/>
    <title>AlbergoPadrone</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/login/check" method="POST" class="form-horizontal well login">
    <c:if test="${errorMessage != ''}">
        <label class="label label-important">${errorMessage}</label>
    </c:if>
    <div class="control-group">
        <label class="control-label" for="username">Username</label>

        <div class="controls">
            <input id="username" type="text" name="username"><br/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label" for="password">Password</label>

        <div class="controls">
            <input id="password" type="password" name="password"><br/>
        </div>
    </div>
    <div class="control-group">
        <div class="controls">
            <input type="hidden" name="redirectUrl" value="${redirectUrl}"/>
            <input type="reset" value="Clear"/> <input type="submit" value="Submit"/>
        </div>
    </div>
</form>
</body>
</html>