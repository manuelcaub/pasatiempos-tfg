        var ancho = 30;  // Ancho de cada casilla
        var alto = 30;    // Alto de cada casilla
        var grosor = 2;  // Grosor del borde de cada casilla
        var fontSize = ancho/2;
        var namespace="http://www.w3.org/2000/svg";

        function Tablero(texto, palabras) {
            this.filas=texto.length;
            this.columnas=texto[0].length;
            this.texto=texto;
            this.palabras=palabras;
        }

        Tablero.prototype.draw = function() {
            var lienzo=document.getElementById("mySVG");
            lienzo.setAttribute("width", this.filas * ancho);
            lienzo.setAttribute("height", this.columnas * alto);

            for (var i=0; i<this.filas; i++) {
                for (var j=0; j<this.columnas; j++) {
                    var casilla=new Casilla(i, j, this.texto[i][j]);
                    lienzo.appendChild(casilla.grupo);
                }
            }
            
            var x=[];
            for (var i=0; i<this.palabras.length; i++){
                var centroX=this.palabras[i].col*ancho+ancho/2;
                var centroY=this.palabras[i].row*alto+alto/2;
                var id = this.palabras[i].row +""+this.palabras[i].col;
                if($.inArray(id, x) == -1){
                	x.push(id);
                	var grupo = lienzo.getElementById(id);
                    var caja=document.createElementNS(namespace, "text");
                    caja.setAttribute("contentEditable", "true");
                    caja.setAttribute("fill", "black");
                    caja.setAttribute("font-size", ancho*0.002);
                    caja.innerHTML=x.length;
                    caja.setAttribute("x", centroX - 0.45*ancho);
                    caja.setAttribute("y", centroY - 0.3*alto);
                    grupo.appendChild(caja);
                }
            }
        }

        function Casilla(fila, columna, letra) {
            this.grupo=document.createElementNS(namespace, "g");
            this.grupo.id = fila+""+columna
            var rect=document.createElementNS(namespace, "rect");
            rect.setAttribute("x", columna*ancho);
            rect.setAttribute("y", fila*alto);
            rect.setAttribute("width", ancho);
            rect.setAttribute("height", alto);
            rect.setAttribute("stroke", "black");
            rect.setAttribute("stroke-width", grosor);
            rect.setAttribute("fill", "white");
            var centroX=columna*ancho+ancho/2;
            var centroY=fila*alto+alto/2;

            var caja=document.createElementNS(namespace, "text");
            caja.setAttribute("contentEditable", "true");
            caja.setAttribute("fill", "black");
            caja.setAttribute("font-size", ancho*0.75);
            caja.innerHTML=letra;
             caja.setAttribute("x", centroX - 0.25*ancho);
            caja.setAttribute("y", centroY + 0.25*alto);

            this.grupo.appendChild(rect);
            if (letra!="-")
                this.grupo.appendChild(caja);
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
        	
            var tablero=new Tablero(obj.board, obj.boardWords);
            tablero.draw();
        }