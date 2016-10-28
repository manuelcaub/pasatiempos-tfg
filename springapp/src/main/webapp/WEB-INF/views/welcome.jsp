<%@ include file="/WEB-INF/views/include.jsp" %>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
	<title><spring:message code="title"></spring:message></title>
    <%@ include file="/WEB-INF/views/includeFiles.jsp" %>	
	<link href="<c:url value="/resources/css/mcapp-main.css" />" rel="stylesheet">
	<script src="<c:url value="/resources/js/mcapp-main.js" />"></script>
	<script src="<c:url value="/resources/js/wordsearch.js"/>"></script>
</head>
<body>
	<%@ include file="/WEB-INF/views/header.jsp" %>
	<div align="center"><svg id="mySVG" style="background-color:lightgrey" width="100" height="100"></svg></div>
	<script>
        createWordSearch('${sopa}')
    </script>
</body>
</html>