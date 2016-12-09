function saveWord() {
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	var json = {};
	json["word"]= $("#wordbox").val().toLowerCase();
	json["definition"] = $("#definitionbox").val();
    $.ajax({type : "POST",
		dataType : 'text',
    	url: 'saveword.html',
    	beforeSend: function (xhr) {
    	    xhr.setRequestHeader(header, token);
    	},
    	data : JSON.stringify(json),
    	contentType : "application/json",
        success: function(data) {
        	$("#modalWord").modal("show");
        },
        error: function(e) {
        	$("#modalWordError").modal("show");
			console.log("ERROR: ", e);
		}});
}

function saveQuote() {
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	var json = {};
	json["quote"]= $("#quotebox").val();
	json["author"] = $("#authorbox").val();
    $.ajax({type : "POST",
		dataType : 'text',
    	url: 'savequote.html',
    	beforeSend: function (xhr) {
    	    xhr.setRequestHeader(header, token);
    	},
    	data : JSON.stringify(json),
    	contentType : "application/json",
        success: function(data) {
        	$("#modalQuote").modal("show");
        },
        error: function(e) {
        	$("#modalQuoteError").modal("show");
			console.log("ERROR: ", e);
		}});
}