$(document).ready(function() {
	$("#dropdown-puzzles").empty();
    $.get({
        url : 'getpuzzles.html',
        dataType : 'json',
        success : function(data) {
        	var a = data.filter(x => x.type == "crossword");
        	dropdownCrossword(data.filter(x => x.type == "crossword"));
        	dropdownWordSearch(data.filter(x => x.type == "wordsearch"));
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
	var panelgroup = $('<div class="panel-group" id="accordion' + this.id +'"></div>');
	var paneldefault = $('<div class="panel panel-default"></div>');
	var panelheading = $('<div class="panel-heading"></div>');
	panelheading.html(this.name + ' <button data-toggle="collapse" data-parent="#accordion' + this.id +'" href="#collapse' + this.id +'" id="show" onclick="javascript:expand(show' + this.id + ')" type="button" class="btn btn-default btn-xs pull-right"><i id="show' + this.id + '" class="fa fa-chevron-down"></i></button>')
	var panelbody = $('<div class="panel-body"><div class="row"><div align="center"><svg id="svgCW' + this.id + '" style="background-color:lightgrey" width="100" height="100"></svg></div></div><div id="collapse' + this.id + '" class="panel-collapse collapse"><div id="panel-info' + this.id + '" class="panel-body"></div></div></div>');
	var panelfooter = $('<div class="panel-footer"><button type="button" class="btn btn-default"><i class="fa fa-recycle fa-lg"></i></button></div>');
	
	paneldefault.append(panelheading);
	paneldefault.append(panelbody);
	paneldefault.append(panelfooter);
	panelgroup.append(paneldefault);
	
	$("#dropdown-crossword").append(panelgroup);
	
    var crossword=new Crossword(this.id, this.crossword.board, this.crossword.boardWords);
    crossword.draw();
}

function PanelWordSearch (id, name, wordsearch) {
	this.id = id;
	this.name = name;
	this.wordsearch = wordsearch;
}

PanelWordSearch.prototype.create = function () {
	var panelgroup = $('<div class="panel-group" id="accordionWS' + this.id +'"></div>');
	var paneldefault = $('<div class="panel panel-default"></div>');
	var panelheading = $('<div class="panel-heading"></div>');
	panelheading.html(this.name + ' <button data-toggle="collapse" data-parent="#accordionWS' + this.id +'" href="#collapseWS' + this.id +'" id="show" onclick="javascript:expand(showWS' + this.id + ')" type="button" class="btn btn-default btn-xs pull-right"><i id="showWS' + this.id + '" class="fa fa-chevron-down"></i></button>')
	var panelbody = $('<div class="panel-body"><div class="row"><div align="center"><svg id="svgWS' + this.id + '" style="background-color:lightgrey" width="100" height="100"></svg></div></div><div id="collapseWS' + this.id + '" class="panel-collapse collapse"><div id="panel-infoWS' + this.id + '" class="panel-body"></div></div></div>');
	var panelfooter = $('<div class="panel-footer"><button type="button" class="btn btn-default"><i class="fa fa-recycle fa-lg"></i></button></div>');
	
	paneldefault.append(panelheading);
	paneldefault.append(panelbody);
	paneldefault.append(panelfooter);
	panelgroup.append(paneldefault);
	
	$("#dropdown-wordsearch").append(panelgroup);
	
    var wordsearch=new WordSearch(this.id, this.wordsearch.board, this.wordsearch.boardWords);
    wordsearch.draw();
}