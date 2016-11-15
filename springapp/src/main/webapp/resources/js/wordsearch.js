var width = 20;  // Ancho de cada casilla
var height = 20;   // Alto de cada casilla
var fontSize = width/2;
var namespace="http://www.w3.org/2000/svg";

function createWordSearch(jsonObject) {
	var obj;
	if(typeof jsonObject == "string") {
		obj = JSON.parse(jsonObject);
	} else {
		obj = jsonObject;
	}
	
    var wordsearch=new WordSearch("", obj.board, obj.words);
    wordsearch.draw();
	if(typeof obj.quote != "undefined") {
		$("#mySVG").after('<br><p>"' + obj.quote.quote + '", ' + obj.quote.author + '</p>');
	}
}

function WordSearch(id, board, words) {
	this.id = id;
    this.rows=board.length;
    this.cols=board[0].length;
    this.board=board;
    this.words = words;
}

WordSearch.prototype.draw = function() {
    var svg=document.getElementById("svgWS"  + this.id);
    svg.setAttribute("width", this.rows * width);
    svg.setAttribute("height", this.cols * height);

    for (var i=0; i<this.rows; i++) {
        for (var j=0; j<this.cols; j++) {
            var box=new WSBox(i, j, this.board[i][j]);
            svg.appendChild(box.group);
        }
    }
}

function WSBox(row, col, letter) {
    this.group=document.createElementNS(namespace, "g");

    var rect=document.createElementNS(namespace, "rect");
    rect.setAttribute("x", col*width);
    rect.setAttribute("y", row*height);
    rect.setAttribute("width", width);
    rect.setAttribute("height", height);
    rect.setAttribute("fill", "white");
    var centerX=col*width+width/2;
    var centerY=row*height+height/2;

    var box=document.createElementNS(namespace, "text");
    box.setAttribute("contentEditable", "true");
    box.setAttribute("fill", "black");
    box.setAttribute("text-anchor", "middle");
    box.setAttribute("font-size", width*0.75);
    box.innerHTML=letter;
    box.setAttribute("x", centerX - 0.25*width);
    box.setAttribute("y", centerY + 0.25*height);

    this.group.appendChild(rect);
    this.group.appendChild(box);
}