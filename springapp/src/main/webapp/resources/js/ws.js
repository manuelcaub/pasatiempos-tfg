"use strict";

var url="ws://" + window.location.hostname + ":" + window.location.port + "/springapp/servidorWS";
var chat=null;

function conectarWebSocket() {
	if (chat!=null)
		return;
	chat=new WebSocket(url);

	chat.onopen = function() {
		document.getElementById("circle-ws").setAttribute("style", "color:green");
	}
	
	chat.onerror = function() {
		showError("Error");
	}
	
	chat.onclose = function() {
		document.getElementById("circle-ws").setAttribute("style", "color:red");
	}
	
	chat.onmessage = function(message) {
		message=JSON.parse(message.data);
		var type=message.type;
		if (type=="SESSION_ID") {
			this.sessionId=message.sessionId;		
		} else if (type=="CROSSWORD") {
			createCrossword(message.puzzle);
		} else if (type=="WORDSEARCH") {
			createWordSearch(message.puzzle);
		}
	}		
}