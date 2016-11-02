$(document).ready(function(){
    expand = function(showi){
      if($(showi).hasClass("fa-chevron-down")){
        $(showi).removeClass("fa-chevron-down");
        $(showi).addClass("fa-chevron-up");
      }else if($(showi).hasClass("fa-chevron-up")){
        $(showi).removeClass("fa-chevron-up");
        $(showi).addClass("fa-chevron-down");
      }
    };
});

function newCrossword() {
	if($("input#input-size").is(":valid") && $("input#input-blacks").is(":valid")) {
	    $.get({
	        url : 'newcrossword.html',
	        dataType : 'json',
	        data: { size: document.getElementById("input-size").value, blacks: document.getElementById("input-blacks").value },
	        success : function(data) {
	        	createCrossword(data);
	        }
	    });
	}
}