<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
    <head>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/google-web-fonts.css" type="text/css"/>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap-selected.css" type="text/css"/>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/extra.css" type="text/css"/>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/logon.css" type="text/css"/>
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
        <title>RCR Club Dash Board</title>
    </head>
    <body>
			<div id="wrapper">

				<form name="login-form" class="login-form" action="<%=request.getContextPath()%>/login/check" method="post">
				
					<div class="header">
					<h1>Sign in to RCR Club</h1>
					<span>Please enter your user id and password! Or contact the Admin!!</span>
					</div>
					<c:if test="${errorMessage != ''}">
						<div class="alert alert-error">
								<a class="close" data-dismiss="alert" href="${redirectUrl}">x</a>Incorrect Username or Password!
						</div>
					</c:if>	
					<div class="content">
					<input name="username" type="text" class="input username" placeholder="Username" />
					<div class="user-icon"></div>
					<input name="password" type="password" class="input password" placeholder="Password" />
					<div class="pass-icon"></div>		
					</div>

					<div class="footer">
                        <input type="hidden" name="redirectUrl" value="${redirectUrl}"/>
                        <button type="submit" name="submit" class="btn btn-large btn-primary"><i class = 'icon-user'></i> &nbsp Login</button>
                        <button type="reset" name="reset" class="btn btn-large btn-warning"><i class = 'icon-refresh'></i> &nbsp Clear</button>
					</div>
				
				</form>

			</div>
			<div class="gradient"></div>
    </body>
</html>