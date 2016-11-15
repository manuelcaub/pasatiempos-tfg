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
		formwords.append(createInputGroup(i + "h", hwords[i].word, hwords[i].definition));
	}
	
	formwords.append('<h4>Verticales</h4>');
	for(var i = 0; i < vwords.length; i++) {
		formwords.append(createInputGroup(i + "v", vwords[i].word, vwords[i].definition));
	}
	
	$("#panel-info").append(formwords);
}

function createInputGroup (id, word, definition) {
	var formgroup = $('<div class="form-group"></div>');
	var inputgroup = $('<div class="input-group"></div>');
	inputgroup.append('<span class="input-group-addon">' + word+ '</span>');
	var inputword = $('<input id="word-input' + id + '" class="form-control input-sm" type="text" word-value="' + word + '"></input>');
	if(definition != '') {
		inputword.attr("value", definition);
		inputword.prop("disabled", true);
		inputgroup.append(inputword);
	} else {
		inputgroup.append(inputword);
		inputgroup.append('<span class="input-group-btn"><button class="btn btn-secondary" type="button" id-definition="word-input' + id + '" word-value="' + word + '" onclick="saveDefinition(this);"><i class="fa fa-plus" aria-hidden="true"></i></button></span>');
	}
	
	formgroup.append(inputgroup);
	
	return formgroup;
}