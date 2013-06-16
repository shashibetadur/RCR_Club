<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
    <head>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/google-web-fonts.css" type="text/css"/>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap-selected.css" type="text/css"/>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/extra.css" type="text/css"/>
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
        <title>RCR Club Dash Board</title>
    </head>
    <body>
        <div style="margin:220 auto;width:35%;text-align:left">
            <table>
                <thead>
                    <th style="width: 65%"></th>
                    <th style="width: 35%"></th>
                </thead>
                <tbody>
                    <tr>
                        <td>
                            <form action="<%=request.getContextPath()%>/login/check" method="POST">
                                <div class="row">
                                    <div class="span5 offset4">
                                      <div class="well">
                                        <legend>Sign in to RCR Club</legend>
                                            <c:if test="${errorMessage != ''}">
                                                <div class="alert alert-error">
                                                        <a class="close" data-dismiss="alert" href="${redirectUrl}">x</a>Incorrect Username or Password!
                                                </div>
                                            </c:if>
                                            <label class="control-label" for="username">Username</label>
                                            <input class="span3" type="text" name="username">
                                            <label class="control-label" for="password">Password</label>
                                            <input class="span3" type="password" name="password">
                                            <legend></legend>
                                            <div>
                                                <input type="hidden" name="redirectUrl" value="${redirectUrl}"/>
                                                <button class="btn btn-primary" type="submit"><i class = 'icon-user'></i> &nbspLogin</button>
                                                <button type="reset" class="btn btn-warning"><i class = 'icon-refresh'></i> &nbsp Clear</button>
                                            </div>
                                      </div>
                                    </div>
                                </div>
                            </form>
                        </td>
                        <td>
                            <div>
                                <img src="<%=request.getContextPath()%>/img/login_icon.png" class="img-rounded">
                            </div>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </body>
</html>