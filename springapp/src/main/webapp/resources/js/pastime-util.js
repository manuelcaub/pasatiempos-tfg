$(document).ready(function(){
    expand = function(showi){
      if($(showi).hasClass("fa-chevron-down")){
        $(showi).removeClass("fa-chevron-down");
        $(showi).addClass("fa-chevron-up");
      }else if($(showi).hasClass("fa-chevron-up")){
        $(showi).removeClass("fa-chevron-up");
        $(showi).addClass("fa-chevron-down");
      }
    };
});

function newCrossword() {
	if($("input#input-size").is(":valid") && $("input#input-blacks").is(":valid")) {
	    $.get({
	        url : 'newcrossword.html',
	        dataType : 'json',
	        data: { size: document.getElementById("input-size").value, blacks: document.getElementById("input-blacks").value },
	        success : function(data) {
	        	createCrossword(data);
	        	insertWords(data.boardWords);
	        }
	    });
	}
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
		return obj.vertical;
	});
	var hwords = words.filter(function(obj){
		return obj.horizontal;
	});
	
	formwords.append('<h4>Horizontales</h4>');
	for(var i = 0; i < hwords.length; i++) {
		var formgroup = $('<div class="form-group"></div>');
		var inputgroup = $('<div class="input-group"></div>');
		inputgroup.append('<span class="input-group-addon" id="basic-addon1">' + hwords[i].word + '</span>');
		var inputword = $('<input id="hword-input' + i + '" class="form-control input-sm" type="text" ></input>');
		if(typeof hwords[i].definition != 'undefined') {
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
		if(typeof vwords[i].definition != 'undefined') {
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

function newWordSearch() {
	if($("input#input-size").is(":valid") && $("input#input-words").is(":valid")) {
	    $.get({
	        url : 'newwordsearch.html',
	        dataType : 'json',
	        data: { size: document.getElementById("input-size").value, words: document.getElementById("input-words").value },
	        success : function(data) {
	        	createWordSearch(data);
	        }
	    });
	}
}