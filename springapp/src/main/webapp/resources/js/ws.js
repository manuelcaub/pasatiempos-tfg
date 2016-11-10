"use strict";

var url="ws://" + window.location.hostname + ":" + window.location.port + "/springapp/servidorWS";
var chat=null;

function conectarWebSocket() {
	if (chat!=null)
		return;
	chat=new WebSocket(url);

	chat.onopen = function() {
		document.getElementById("listening").innerHTML="Listening";
		document.getElementById("listening").setAttribute("style", "color:green");
	}
	
	chat.onerror = function() {
		document.getElementById("listening").innerHTML="No listening";
		showError("Error");
	}
	
	chat.onclose = function() {
		document.getElementById("listening").innerHTML="No listening";
		document.getElementById("listening").setAttribute("style", "color:red");
	}
	
	chat.onmessage = function(mensaje) {
		mensaje=JSON.parse(mensaje.data);
		var type=mensaje.type;
		if (type=="SESSION_ID") {
			this.sessionId=mensaje.sessionId;		
			document.getElementById("listening").innerHTML="Listening (sessionId: " + this.sessionId + ")";
		} else if (type=="CUADRO") {
			createCrossword(mensaje.texto);
		}
	}		
}