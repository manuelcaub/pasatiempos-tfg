<%@ include file="/WEB-INF/views/include.jsp" %>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
	<title><spring:message code="title"></spring:message></title>
    <%@ include file="/WEB-INF/views/includeFiles.jsp" %>	
	<script src="<c:url value="/resources/js/mypuzzles.js"/>"></script>
	<script src="<c:url value="/resources/js/pastime-util.js"/>"></script>
	<script src="<c:url value="/resources/js/crossword.js"/>"></script>
	<script src="<c:url value="/resources/js/wordsearch.js"/>"></script>
</head>
<body>
	<%@ include file="/WEB-INF/views/header.jsp" %>
	<div class="container">
		 <ul class="nav nav-tabs nav-justified">
		   <li class="active"><a data-toggle="tab" href="#tabCrossword">Crucigramas</a></li>
		   <li><a data-toggle="tab" href="#tabWordSearch">Sopas de letras</a></li>
		 </ul>
		
		 <div class="tab-content">
		   <div id="tabCrossword" class="tab-pane fade in active">
		     <div id="dropdown-crossword"></div>
		   </div>
		   <div id="tabWordSearch" class="tab-pane fade">
		     <div id="dropdown-wordsearch"></div>
		   </div>
		 </div>	  
	</div>
	<%@ include file="/WEB-INF/views/footer.jsp" %>
</body>
</html>