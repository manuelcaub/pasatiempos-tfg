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
    $.ajax({
    	type : "GET",
		contentType : "application/json",
        url : 'newcrossword.html',
        dataType : 'json',
        success : function(data) {
        	createCrossword(data);
            $('#mySvg').html(data);
        },
		error : function(e) {
			console.log("ERROR: ", e);
		}
    });
}