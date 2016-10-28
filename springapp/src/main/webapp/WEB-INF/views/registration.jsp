<%@ include file="/WEB-INF/views/include.jsp" %>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
	<title><spring:message code="register.title"></spring:message></title>
	<%@ include file="/WEB-INF/views/includeFiles.jsp" %>
	<link href="<c:url value="/resources/css/mcapp-main.css" />" rel="stylesheet">
	<script src="<c:url value="/resources/js/mcapp-main.js" />"></script>
	<script src="<c:url value="/resources/js/registration.js" />"></script>
</head>
<body>
	<%@ include file="/WEB-INF/views/header.jsp" %>    
   	<div class="container">
		<div class="row main">
			<div class="main-login main-center">
				<form:form class="form-horizontal col-md-4 col-md-offset-4"  modelAttribute="usuarioRegistro" action="/springapp/registration.html">
					<div class="form-group">
						<label for="nombre" class="control-label"><spring:message code="register.firstName"></spring:message></label>

							<div class="input-group">
								<span class="input-group-addon"><i class="fa fa-user" aria-hidden="true"></i></span>
								<form:input type="text" class="form-control" name="nombre" id="nombre" path="nombre" placeholder="${register.firstNamePH}" required="true"/>
							</div>

					</div>

					<div class="form-group">
						<label for="email" class="cols-sm-2 control-label"><spring:message code="register.email"></spring:message></label>
						<div >
							<div class="input-group">
								<span class="input-group-addon"><i class="fa fa-envelope" aria-hidden="true"></i></span>
								<form:input type="email" class="form-control" name="email" id="email" path="email"  placeholder="${register.emailPH}" required="true"/>
							</div>
						</div>
					</div>

					<div class="form-group">
						<label for="password" class="cols-sm-2 control-label"><spring:message code="register.password"></spring:message></label>
						<div class="cols-sm-10">
							<div class="input-group">
								<span class="input-group-addon"><i class="fa fa-lock" aria-hidden="true"></i></span>
								<form:input title="8 caracteres, incluyendo mayúscula y minúscula" type="password" class="form-control" name="password" id="password" path="password"  placeholder="${register.passwordPH}" onchange="validatePassword()" pattern="^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\s).{8}$" required="true"/>
							</div>
						</div>
					</div>

					<div class="form-group">
						<label for="passwordConfirm" class="cols-sm-2 control-label"><spring:message code="register.confirmPass"></spring:message></label>
						<div class="cols-sm-10">
							<div class="input-group">
								<span class="input-group-addon"><i class="fa fa-lock" aria-hidden="true"></i></span>
								<form:input type="password" class="form-control" name="passwordConfirm" id="passwordConfirm" path="passwordConfirm" placeholder="${register.confirmPassPH}" onkeyup="validatePassword()" required="true"/>
							</div>
						</div>
					</div>

					<div class="form-group ">
						<button type="submit" class="btn btn-primary btn-lg btn-block login-button"><spring:message code="register.submit"></spring:message></button>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>