
function dropdownCrossword() {
	$("#dropdown-puzzles").empty();
    $.get({
        url : 'getcrosswords.html',
        dataType : 'json',
        success : function(data) {

        }
    });
	
}

function dropdownWordsearch() {
	$("#dropdown-puzzles").empty();
	
}

function Dropdown(id, header, puzzle) {
	this.id = id;
	this.header = header;
	this.puzzle = puzzle;
}

Dropdown.prototype.draw = function () {
	
}