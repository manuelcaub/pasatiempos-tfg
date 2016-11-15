var mycrossword;

function newCrossword() {
	if($("input#input-size").is(":valid") && $("input#input-blacks").is(":valid")) {
	    $.get({
	        url : 'newcrossword.html',
	        dataType : 'json',
	        data: { size: document.getElementById("input-size").value, blacks: document.getElementById("input-blacks").value, sessionId: chat.sessionId },
	        success : function(data) {
	        	mycrossword = data;
	        	var a = JSON.stringify(data);
	        	createCrossword(data);
	        	insertWords(data.boardWords);
	        }
	    });
	}
}

function saveCrossword() {
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
    $.ajax({type : "POST",
		dataType : 'text',
    	url: 'savecrossword.html',
    	beforeSend: function (xhr) {
    	    xhr.setRequestHeader(header, token);
    	},
    	data : JSON.stringify(mycrossword),
    	contentType : "application/json",
        success: function(data) {

        },
        error: function(e) {
			console.log("ERROR: ", e);
		}});
}

function saveDefinition(obj) {
	var json = {};
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	json["word"] = obj.getAttribute("word-value");
	json["definition"] = $('input#' + obj.getAttribute("id-definition")).val();
    $.ajax({type : "POST",
		dataType : 'text',
    	url: 'savedefinition.html',
    	beforeSend: function (xhr) {
    	    xhr.setRequestHeader(header, token);
    	},
    	data : JSON.stringify(json),
    	contentType : "application/json",
         success: function(data) {
    			obj.remove();
    			$('input#' + obj.getAttribute("id-definition")).prop("disabled", true);
        },
        error: function(e) {
			console.log("ERROR: ", e);
		}});
}

function insertWords(words) {
	$("#panel-info").empty();
	var formwords = $("<form></form>");
	var vwords = words.filter(function(obj){
		return obj.direction == "s";
	});
	var hwords = words.filter(function(obj){
		return obj.direction == "e";
	});
	
	formwords.append('<h4>Horizontales</h4>');
	for(var i = 0; i < hwords.length; i++) {
		var formgroup = $('<div class="form-group"></div>');
		var inputgroup = $('<div class="input-group"></div>');
		inputgroup.append('<span class="input-group-addon" id="basic-addon1">' + hwords[i].word + '</span>');
		var inputword = $('<input id="hword-input' + i + '" class="form-control input-sm" type="text" ></input>');
		if(hwords[i].definition != '') {
			inputword.attr("value", hwords[i].definition);
			inputword.prop("disabled", true);
			inputgroup.append(inputword);
		} else {
			inputgroup.append(inputword);
			inputgroup.append('<span class="input-group-btn"><button class="btn btn-secondary" type="button" id-definition="hword-input' + i + '" word-value="' + hwords[i].word + '" onclick="saveDefinition(this);"><i class="fa fa-plus" aria-hidden="true"></i></button></span>');
		}

		formgroup.append(inputgroup);
		formwords.append(formgroup);
	}
	
	formwords.append('<h4>Verticales</h4>');
	for(var i = 0; i < vwords.length; i++) {
		var formgroup = $('<div class="form-group"></div>');
		var inputgroup = $('<div class="input-group"></div>');
		inputgroup.append('<span class="input-group-addon">' + vwords[i].word + '</span>');
		var inputword = $('<input id="vword-input' + i + '" class="form-control input-sm" type="text" word-value="' + vwords[i].word + '"></input>');
		if(vwords[i].definition != '') {
			inputword.attr("value", vwords[i].definition);
			inputword.prop("disabled", true);
			inputgroup.append(inputword);
		} else {
			inputgroup.append(inputword);
			inputgroup.append('<span class="input-group-btn"><button class="btn btn-secondary" type="button"><i class="fa fa-plus" aria-hidden="true"></i></button></span>');
		}
		
		formgroup.append(inputgroup);
		formwords.append(formgroup);
	}
	
	$("#panel-info").append(formwords);    		
	
}