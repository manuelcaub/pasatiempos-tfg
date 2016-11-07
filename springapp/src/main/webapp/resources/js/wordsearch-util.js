function newWordSearch() {
	if($("input#input-size").is(":valid") && $("input#input-words").is(":valid")) {
	    $.get({
	        url : 'newwordsearch.html',
	        dataType : 'json',
	        data: { size: document.getElementById("input-size").value, words: document.getElementById("input-words").value },
	        success : function(data) {
	        	createWordSearch(data);
	        	insertWordsUnderWordSearch(data.words, data.quote);
	        }
	    });
	}
}

function insertWordsUnderWordSearch(words) {
	var panelInfo = $("#panel-info");
	panelInfo.empty();
	
	for (var i = 0; i < words.length; i++) {
		panelInfo.append('<p class="col-md-2">' + (i + 1) + '. ' + words[i] + '</p>');
	}
}