  <header class="mcapp-header">
  <nav role="navigation" class="navbar navbar-inverse navbar-fixed-top">
      <div class="navbar-header">
          <a href="/springapp/welcome.html" class="navbar-brand mcapp-navbar-brand">Pasatiempos</a>
          <button id='btnHamburger' type="button" onclick='javascript:hamburger()' class="navbar-toggle pull-left" data-toggle="collapse" data-target=".navbar-collapse">
               <span class="icon-bar mcbar mcbar-sup"></span>
               <span class="icon-bar mcbar mcbar-med"></span>
               <span class="icon-bar mcbar mcbar-inf"></span>
             </button>
      </div>
	  <div id="navbarCollapse" class="collapse navbar-collapse">
		  <security:authorize access="isAnonymous()">
	          <ul class="nav navbar-nav navbar-right">
	              <li><a href="/springapp/registration.html"><i class="fa fa-user-plus fa-lg" aria-hidden="true"></i> Registrarme</a></li>
	              <li><a href="javascript:showLogin()"><i class="fa fa-user fa-lg" aria-hidden="true"></i> Iniciar sesión</a></li>
	          </ul>
		  </security:authorize>
		  <security:authorize access="isAuthenticated()">
		      <ul class="nav navbar-nav navbar-left">
              	<li><a href="#"> Crucigrama</a></li>
              	<li><a href="#"> Sopa de letras</a></li>
          	  </ul>
	          <ul class="nav navbar-nav navbar-right">
	              <li><p class="navbar-text"><i class="fa fa-user fa-lg" aria-hidden="true"></i> Hola <security:authentication property="principal.username" /></p></li>
	              <li><a href="javascript:formSubmit()"><i class="fa fa-sign-out fa-lg" aria-hidden="true"></i> Cerrar sesión</a></li>
	          </ul>
		  </security:authorize>
		  <form:form id="logoutForm" method="POST" modelAttribute="usuarioLogout" commandName="usuarioLogout" action="/springapp/j_spring_security_logout.action" class="form-signin">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	      </form:form>
      </div>
  </nav>
  </header>
  <security:authorize access="isAnonymous()">
	  <div class="modal fade" id="modalLogin" role="dialog">
	    <div class="modal-dialog">
	      <div class="modal-content">
	        <div class="modal-header" style="padding:5px;">
	          <button type="button" class="close" data-dismiss="modal">&times;</button>
	        </div>
	        <div class="modal-body" style="padding:40px 50px;">
	          <form:form method="POST" modelAttribute="usuarioLogin" action="/springapp/j_spring_security_check.action">
	            <div class="form-group">
	              <label for="email"><span class="fa fa-user"></span> Usuario</label>
	              <spring:bind path="email">
	                <form:input type="text" class="form-control" id="email" path="email" name="email" placeholder="Introduzca su email"/>
	              </spring:bind>
	            </div>
	            <div class="form-group">
	              <label for="pass"><span class="fa fa-lock"></span> Contraseña</label>
	              <spring:bind path="password">
	                <form:input type="password" class="form-control" id="pass" name="password" path="password" placeholder="Introduzca su contraseña"/>
	              </spring:bind>
	            </div>
	            <div class="checkbox">
	              <label><input type="checkbox" value="" checked>Recordarme</label>
	            </div>
	            <button type="submit" class="btn btn-success btn-block">Login</button>
	            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	          </form:form>
	        </div>
	        <div class="modal-footer">
	          <p>¿No estás registrado? <a href="/springapp/registration.html">Registrarme</a></p>
	        </div>
	      </div>
	      
	    </div>
	  </div>
  </security:authorize>