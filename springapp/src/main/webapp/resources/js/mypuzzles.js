var mycrosswords;
var mywordsearchs;

$(document).ready(function() {
	$("#dropdown-puzzles").empty();
    $.get({
        url : 'getpuzzles.html',
        dataType : 'json',
        success : function(data) {
        	mycrosswords = data.filter(x => x.type == "crossword");
        	mywordsearchs = data.filter(x => x.type == "wordsearch");
        	dropdownCrossword(mycrosswords);
        	dropdownWordSearch(mywordsearchs);
        }
    });
});

function dropdownCrossword(crosswords) {
	$("#dropdown-crossword").empty();
	for(var i = 0; i < crosswords.length; i++) {
		var panel = new PanelCrossword(i, "crossword", crosswords[i]);
		panel.create();
	}
}

function dropdownWordSearch(wordsearchs) {
	$("#dropdown-wordsearch").empty();
	for(var i = 0; i < wordsearchs.length; i++) {
		var panel = new PanelWordSearch(i, "wordsearch", wordsearchs[i]);
		panel.create();
	}
}

function PanelCrossword (id, name, crossword) {
	this.id = id;
	this.name = name;
	this.crossword = crossword;
}

PanelCrossword.prototype.create = function () {
	var panelgroup = $('<div class="panel-group" idcw="' + this.id +'" id="accordion' + this.id +'"></div>');
	var paneldefault = $('<div class="panel panel-default"></div>');
	var panelheading = $('<div class="panel-heading clearfix"></div>');
	panelheading.html(' <button data-toggle="collapse" data-parent="#accordion' + this.id +'" href="#collapse' + this.id +'" id="show" onclick="javascript:expand(show' + this.id + ')" type="button" class="btn btn-default btn-xs pull-right"><i id="show' + this.id + '" class="fa fa-chevron-down"></i></button>')
	var panelbody = $('<div class="panel-body"><div class="row"><div align="center"><svg id="svgCW' + this.id + '" width="100" height="100"></svg></div></div><div id="collapse' + this.id + '" class="panel-collapse collapse"><div id="panel-info' + this.id + '" class="panel-body"></div></div></div>');
	var panelfooter = $('<div class="panel-footer"><button type="button" class="btn btn-default" onclick="removeCrossword(' + this.id +');"><i class="fa fa-recycle fa-lg"></i> Eliminar</button> <button type="button" class="btn btn-default" onclick="saveSvg(svgCW' + this.id + ');"><i class="fa fa-download fa-lg"></i> Descargar</button></div>');
	
	paneldefault.append(panelheading);
	paneldefault.append(panelbody);
	paneldefault.append(panelfooter);
	panelgroup.append(paneldefault);
	
	$("#dropdown-crossword").append(panelgroup);
	
    var crossword=new Crossword(this.id, this.crossword.board, this.crossword.boardWords);
    crossword.draw();
    var panelinfo = $("#panel-info" + this.id);
	for (var i = 0; i < this.crossword.boardWords.length; i++) {
		panelinfo.append(createSimpleInputGroup( i + "CW", this.crossword.boardWords[i].word, this.crossword.boardWords[i].definition));
	}    
    
}

function PanelWordSearch (id, name, wordsearch) {
	this.id = id;
	this.name = name;
	this.wordsearch = wordsearch;
}

PanelWordSearch.prototype.create = function () {
	var panelgroup = $('<div class="panel-group" idws="' + this.id +'" id="accordionWS' + this.id +'"></div>');
	var paneldefault = $('<div class="panel panel-default"></div>');
	var panelheading = $('<div class="panel-heading clearfix"></div>');
	panelheading.html(' <button data-toggle="collapse" data-parent="#accordionWS' + this.id +'" href="#collapseWS' + this.id +'" id="show" onclick="javascript:expand(showWS' + this.id + ')" type="button" class="btn btn-default btn-xs pull-right"><i id="showWS' + this.id + '" class="fa fa-chevron-down"></i></button>')
	var panelbody = $('<div class="panel-body"><div class="row"><div align="center"><svg id="svgWS' + this.id + '" width="100" height="100"></svg></div></div><div id="collapseWS' + this.id + '" class="panel-collapse collapse"><div id="panel-infoWS' + this.id + '" class="panel-body"></div></div></div>');
	var panelfooter = $('<div class="panel-footer"><button type="button" class="btn btn-default" onclick="removeWordSearch(' + this.id +');"><i class="fa fa-recycle fa-lg"></i> Eliminar</button> <button type="button" class="btn btn-default" onclick="saveSvg(svgWS' + this.id + ');"><i class="fa fa-download fa-lg"></i> Descargar</button></div>');
	
	paneldefault.append(panelheading);
	paneldefault.append(panelbody);
	paneldefault.append(panelfooter);
	panelgroup.append(paneldefault);
	
	$("#dropdown-wordsearch").append(panelgroup);
	
    var wordsearch=new WordSearch(this.id, this.wordsearch.board, this.wordsearch.boardWords, this.wordsearch.quoteDto);
    wordsearch.draw();
    
	if(wordsearch.quote != null) {
		$("#svgWS" + this.id).after('<p class="col-md-12" align="center"> "' + wordsearch.quote.quote + '" (' + wordsearch.quote.author + ')</p>');
	}
    
    var panelinfo = $("#panel-infoWS" + this.id);
    
	for (var i = 0; i < this.wordsearch.words.length; i++) {
		panelinfo.append('<p class="col-md-2">' + this.wordsearch.words[i].word + '</p>');
	}
}

function createSimpleInputGroup (id, word, definition) {
	var formgroup = $('<div class="form-group col-md-6"></div>');
	var inputgroup = $('<div class="input-group"></div>');
	inputgroup.append('<span class="input-group-addon">' + word+ '</span>');
	var inputword = $('<input id="word-input' + id + '" class="form-control input-sm" type="text" word-value="' + word + '"></input>');
	inputword.attr("value", definition);
	inputword.prop("disabled", true);
	inputgroup.append(inputword);
	formgroup.append(inputgroup);
	
	return formgroup;
}

function removeCrossword(id) {
	removePuzzle(id, "crossword");
}

function removeWordSearch(id) {
	removePuzzle(id, "wordsearch");
}

function removePuzzle(id, type) {
	var puzzle;
	if(type == "crossword") {
		puzzle = mycrosswords[id];
	} else if (type == "wordsearch") {
		puzzle = mywordsearchs[id];
	}
	var json = {};
	json["puzzle"] = JSON.stringify(puzzle);
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
    $.ajax({type : "POST",
		dataType : 'text',
    	url: 'removepuzzle.html',
    	beforeSend: function (xhr) {
    	    xhr.setRequestHeader(header, token);
    	},
    	data : JSON.stringify(json),
    	contentType : "application/json",
        success: function(data) {
        	if(type == "crossword") {
        		$("#accordion" + id).remove();
        	} else if (type == "wordsearch") {
        		$("#accordionWS" + id).remove();
        	}
        },
        error: function(e) {
			console.log("ERROR: ", e);
		}});
}