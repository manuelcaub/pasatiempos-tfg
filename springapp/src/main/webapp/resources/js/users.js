var selectedUser;

function listUsers() {
    $.get({
        url : 'getusers.html',
        dataType : 'json',
        success : function(data) {
        	var headers = ["Nombre", "Email", "Roles"];
        	var table = new Table(headers, data);
        	var htmlTable = table.create()[0].outerHTML;
        	$("#body").append(htmlTable);
        	$("#body").append('<div align="center"><button type="button" class="btn btn-success" onclick="javascript:setCollaborator()">Colaborador</button> <button type="button" class="btn btn-danger" onclick="javascript:removeUser()">Eliminar</button></div>')
        	$("#table tbody tr").click(function(){
     		   $(this).addClass('selected').siblings().removeClass('selected'); 
     		   selectedUser = $(this).find("td").eq(1).html();
        	});
        }
    });
}

function setCollaborator() {
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
    $.ajax({type : "POST",
		dataType : 'text',
    	url: 'setcollaborator.html',
    	beforeSend: function (xhr) {
    	    xhr.setRequestHeader(header, token);
    	},
    	data : selectedUser,
    	contentType : "application/json",
        success: function(data) {
        	if(data == "success") {
        		var roles = $("#table tbody tr.selected").find("td").eq(2);
        		roles.append((roles.is(':empty') ? "" : ",") + "ROLE_COLLABORATOR");
        	}
        },
        error: function(e) {
			console.log("ERROR: ", e);
		}});
}

function removeUser() {
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
    $.ajax({type : "POST",
		dataType : 'text',
    	url: 'removeuser.html',
    	beforeSend: function (xhr) {
    	    xhr.setRequestHeader(header, token);
    	},
    	data : selectedUser,
    	contentType : "application/json",
        success: function(data) {
        	$("#table tbody tr.selected").remove()
        },
        error: function(e) {
			console.log("ERROR: ", e);
		}});
}


$(document).ready(function() {
	listUsers();
});
