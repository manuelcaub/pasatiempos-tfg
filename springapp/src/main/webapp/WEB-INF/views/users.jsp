<%@ include file="/WEB-INF/views/include.jsp" %>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
	<title><spring:message code="title"></spring:message></title>
    <%@ include file="/WEB-INF/views/includeFiles.jsp" %>
    <script src="<c:url value="/resources/js/users.js"/>"></script>
</head>
<body>
	<%@ include file="/WEB-INF/views/header.jsp" %>
	<div class="container">
		<div id="body" style="background-color:white"></div>
	</div>
	<%@ include file="/WEB-INF/views/footer.jsp" %>
</body>
</html>