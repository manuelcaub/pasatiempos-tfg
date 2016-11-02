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
		formgroup.append('<label for="word-input' + i + '">' + hwords[i].word + '</label>');
		formgroup.append('<input id="word-input' + i + '" class="form-control input-sm" type="text"></input>');
		formwords.append(formgroup);
	}
	
	formwords.append('<h4>Verticales</h4>');
	for(var i = 0; i < vwords.length; i++) {
		var formgroup = $('<div class="form-group"></div>');
		formgroup.append('<label for="word-input' + i + '">' + vwords[i].word + '</label>');
		formgroup.append('<input id="word-input' + i + '" class="form-control input-sm" type="text"></input>');
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