<%@ include file="/WEB-INF/views/include.jsp" %>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
	<title><spring:message code="title"></spring:message></title>
    <%@ include file="/WEB-INF/views/includeFiles.jsp" %>	
    <script src="<c:url value="/resources/js/data.js" />"></script>
</head>
<body>
	<%@ include file="/WEB-INF/views/header.jsp" %>
	<div class="container">
		 <ul class="nav nav-tabs nav-justified">
		   <li class="active"><a data-toggle="tab" href="#tabQuotes">Frases</a></li>
		   <li><a data-toggle="tab" href="#tabWords">Palabras</a></li>
		 </ul>
		
		 <div class="tab-content">
		   <div id="tabQuotes" class="tab-pane fade in active">
				
				  		<div class="panel-group" id="accordion">
				    		<div class="panel panel-default">
					      		<div class="panel-heading text-center"> Insertar frase</div>
					      		<div class="panel-body">
					      			
					          			<div>
								            <form>
								              <div class="form-group">
								                <label for="input-size">Frase</label>
								                <input id="quotebox" class="form-control input-sm" id="input-size" type="text" required>
								              </div>
								              <div class="form-group">
								                <label for="input-blacks">Autor</label>
								                <input id="authorbox" class="form-control input-sm" id="input-blacks" type="text" required>
								              </div>
								            </form>
					          			</div>
					    		</div>
					      		<div class="panel-footer clearfix" align="center"><button type="button" onclick="javascript:saveQuote()" class="btn btn-default"><i class="fa fa-cloud-upload"></i> Guardar</button></div>
				    		</div>
				  	
				  </div>
		   </div>
		   <div id="tabWords" class="tab-pane fade">
		     
		  		<div class="panel-group" id="accordion">
		    		<div class="panel panel-default">
			      		<div class="panel-heading text-center"> Insertar palabra</div>
			      		<div class="panel-body">
			      			
			          			<div>
						            <form>
						              <div class="form-group">
						                <label for="input-size">Palabra</label>
						                <input id="wordbox" class="form-control input-sm" id="input-size" type="text" required="">
						              </div>
						              <div class="form-group">
						                <label for="input-blacks">Definición</label>
						                <input id="definitionbox" class="form-control input-sm" id="input-blacks" type="text" required="">
						              </div>
						            </form>
			          			</div>
			    			</div>
			      		<div class="panel-footer clearfix" align="center"><button type="button" onclick="javascript:saveWord()" class="btn btn-default"><i class="fa fa-cloud-upload"></i> Guardar</button></div>
		    		</div>
  			</div>
		   </div>
		 </div>	
		 
		  <div class="modal fade" id="modalWord" role="dialog">
		    <div class="modal-dialog modal-sm">
		      <div class="modal-content">
		        <div class="modal-body bg-green">
		          <button type="button" class="close" data-dismiss="modal">&times;</button>
		          <p>Palabra insertada con éxito.</p>
		        </div>
		      </div>
		    </div>
		  </div>  
		  
		  <div class="modal fade" id="modalQuote" role="dialog">
		    <div class="modal-dialog modal-sm">
		      <div class="modal-content">
		        <div class="modal-body bg-green">
		          <button type="button" class="close" data-dismiss="modal">&times;</button>
		          <p>Frase insertada con éxito.</p>
		        </div>
		      </div>
		    </div>
		  </div>
		  
		  <div class="modal fade" id="modalQuoteError" role="dialog">
		    <div class="modal-dialog modal-sm">
		      <div class="modal-content">
		        <div class="modal-body bg-red">
		          <button type="button" class="close" data-dismiss="modal">&times;</button>
		          <p>Error al guardar la frase.</p>
		        </div>
		      </div>
		    </div>
		  </div> 	

		  <div class="modal fade" id="modalWordError" role="dialog">
		    <div class="modal-dialog modal-sm">
		      <div class="modal-content">
		        <div class="modal-body bg-red">
		          <button type="button" class="close" data-dismiss="modal">&times;</button>
		          <p>Error al guardar la palabra</p>
		        </div>
		      </div>
		    </div>
		  </div>  		  	  
	</div>
	<%@ include file="/WEB-INF/views/footer.jsp" %>	
</body>
</html>