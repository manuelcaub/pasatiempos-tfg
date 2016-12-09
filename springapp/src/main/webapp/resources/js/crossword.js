        var widthCW = 30;  // Ancho de cada casilla
        var heightCW = 30;    // Alto de cada casilla
        var border = 2;  // Grosor del borde de cada casilla
        var fontSize = widthCW/2;
        var namespace="http://www.w3.org/2000/svg";

        function Crossword(id, board, words) {
        	this.id = id;
            this.rows=board.length;
            this.cols=board[0].length;
            this.board=board;
            this.words=words;
        }

        Crossword.prototype.draw = function() {
            var svg=document.getElementById("svgCW" + this.id);
            svg.setAttribute("xmlns", namespace);
            svg.setAttribute("width", this.rows * widthCW);
            svg.setAttribute("height", this.cols * heightCW);

            for (var i=0; i<this.rows; i++) {
                for (var j=0; j<this.cols; j++) {
                    var box=new Box(i, j, this.board[i][j]);
                    svg.appendChild(box.group);
                }
            }
            
            var x=[];
            for (var i=0; i<this.words.length; i++){
                var centerX=this.words[i].col*widthCW+widthCW/2;
                var centerY=this.words[i].row*heightCW+heightCW/2;
                var id = this.words[i].row +""+this.words[i].col;
                if($.inArray(id, x) == -1){
                	x.push(id);
                	var group = svg.getElementById(id);
                    var box=document.createElementNS(namespace, "text");
                    box.setAttribute("contentEditable", "true");
                    box.setAttribute("fill", "black");
                    box.setAttribute("font-size", widthCW*0.002);
                    box.innerHTML=x.length;
                    box.setAttribute("x", centerX - 0.45*widthCW);
                    box.setAttribute("y", centerY - 0.3*heightCW);
                    group.appendChild(box);
                }
            }
        }

        function Box(row, col, letter) {
            this.group=document.createElementNS(namespace, "g");
            this.group.id = row+""+col
            var rect=document.createElementNS(namespace, "rect");
            rect.setAttribute("x", col*widthCW);
            rect.setAttribute("y", row*heightCW);
            rect.setAttribute("width", widthCW);
            rect.setAttribute("height", heightCW);
            rect.setAttribute("stroke", "black");
            rect.setAttribute("stroke-width", border);
            rect.setAttribute("fill", "white");
            var centerX=col*widthCW+widthCW/2;
            var centerY=row*heightCW+heightCW/2;

            var box=document.createElementNS(namespace, "text");
            box.setAttribute("contentEditable", "true");
            box.setAttribute("fill", "black");
            box.setAttribute("font-size", widthCW*0.75);
            box.innerHTML=letter;
             box.setAttribute("x", centerX - 0.25*widthCW);
            box.setAttribute("y", centerY + 0.25*heightCW);

            this.group.appendChild(rect);
            if (letter!="-")
                this.group.appendChild(box);
            else
                rect.setAttribute("fill", "black");
        }

        function createCrossword(jsonObject) {
        	var obj;
        	if(typeof jsonObject == "string") {
        		obj = JSON.parse(jsonObject);
        	} else {
        		obj = jsonObject;
        	}
        	
            var crossword=new Crossword("", obj.board, obj.boardWords);
            crossword.draw();
        }