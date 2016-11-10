<%@ include file="/WEB-INF/views/include.jsp" %>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
	<title><spring:message code="title"></spring:message></title>
    <%@ include file="/WEB-INF/views/includeFiles.jsp" %>	
	<script src="<c:url value="/resources/js/mypuzzles.js"/>"></script>
	
</head>
<body>
	<%@ include file="/WEB-INF/views/header.jsp" %>
	<div class="container">
	  <div class="dropdown">
	    <button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown">Tipo de pasatiempo
	    <span class="caret"></span></button>
	    <ul class="dropdown-menu">
	      <li><a href="javascript:dropdownCrossword()">Crucigrama</a></li>
	      <li><a href="#">Sopa de letras</a></li>
	    </ul>
	  </div>
	  <div id="dropdown-puzzles"></div>
	</div>	
</body>
</html>