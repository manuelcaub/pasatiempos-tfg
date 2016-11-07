
var ancho = 20;  // Ancho de cada casilla
var alto = 20;   // Alto de cada casilla
var grosor = 2;  // Grosor del borde de cada casilla
var fontSize = ancho/2;
var namespace="http://www.w3.org/2000/svg";

function createWordSearch(jsonObject) {
	var obj;
	if(typeof jsonObject == "string") {
		obj = JSON.parse(jsonObject);
	} else {
		obj = jsonObject;
	}
	
    var tablero=new Tablero(obj.board);
    tablero.draw();
	if(typeof obj.quote != "undefined") {
		$("#mySVG").after('<br><p>"' + obj.quote.valor + '", ' + obj.quote.autor + '</p>');
	}
}

function Tablero(texto) {
    this.filas=texto.length;
    this.columnas=texto[0].length;
    this.texto=texto;
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
}

function Casilla(fila, columna, letra) {
    this.grupo=document.createElementNS(namespace, "g");

    var rect=document.createElementNS(namespace, "rect");
    rect.setAttribute("x", columna*ancho);
    rect.setAttribute("y", fila*alto);
    rect.setAttribute("width", ancho);
    rect.setAttribute("height", alto);
    rect.setAttribute("fill", "white");
    var centroX=columna*ancho+ancho/2;
    var centroY=fila*alto+alto/2;

    var caja=document.createElementNS(namespace, "text");
    caja.setAttribute("contentEditable", "true");
    caja.setAttribute("fill", "black");
    caja.setAttribute("text-anchor", "middle");
    caja.setAttribute("font-size", ancho*0.75);
    caja.innerHTML=letra;
    caja.setAttribute("x", centroX - 0.25*ancho);
    caja.setAttribute("y", centroY + 0.25*alto);

    this.grupo.appendChild(rect);
    this.grupo.appendChild(caja);
}