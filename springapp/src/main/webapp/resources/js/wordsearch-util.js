var mywordsearch;

function newWordSearch() {
	if($("input#input-size").is(":valid") && $("input#input-words").is(":valid")) {
	    $.get({
	        url : 'newwordsearch.html',
	        dataType : 'json',
	        data: { size: document.getElementById("input-size").value, words: document.getElementById("input-words").value, sessionId: chat.sessionId },
	        success : function(data) {
	        	mywordsearch = data;
	        	createWordSearch(data);
	        	insertWordsUnderWordSearch(data.words, data.quoteDto);
	        }
	    });
	}
}

function saveWordSearch() {
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
    $.ajax({type : "POST",
		dataType : 'text',
    	url: 'savewordsearch.html',
    	beforeSend: function (xhr) {
    	    xhr.setRequestHeader(header, token);
    	},
    	data : JSON.stringify(mywordsearch),
    	contentType : "application/json",
        success: function(data) {
        	
        },
        error: function(e) {
			console.log("ERROR: ", e);
		}});
}

function insertWordsUnderWordSearch(words, quote) {
	var panelInfo = $("#panel-info");
	panelInfo.empty();
	
	for (var i = 0; i < words.length; i++) {
		panelInfo.append('<p class="col-md-2">' + (i + 1) + '. ' + words[i].word + '</p>');
	}
}