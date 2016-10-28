<%@ include file="/WEB-INF/views/include.jsp" %>

<html lang="es">
<head>
	<link href="<c:url value="/resources/css/mcapp-main.css" />" rel="stylesheet">
    <script src="<c:url value="/resources/js/mcapp-main.js" />"></script>
    <%@ include file="/WEB-INF/views/includeFiles.jsp" %>
    <title>Inicie sesión con su cuenta</title>
</head>

<body>
	<%@ include file="/WEB-INF/views/header.jsp" %> 
	<div class="container">
	    <form:form method="POST" modelAttribute="usuarioLogin" action="/springapp/j_spring_security_check.action" class="form-signin">
	        <h2 class="form-heading">Log in</h2>
	
	        <div class="form-group">
	        	<spring:bind path="email">
	            	<form:input name="email" path="email" type="text" class="form-control" placeholder="Email" autofocus="true"/>
	            </spring:bind>
	            <spring:bind path="password">
	            	<form:input name="password" path="password" type="password" class="form-control" placeholder="Password"/>
				</spring:bind>
	            <button class="btn btn-lg btn-primary btn-block" type="submit">Log In</button>
	            <h4 class="text-center"><a href="/registration">Create an account</a></h4>
	        </div>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	    </form:form>
	
	</div>

</body>
</html>