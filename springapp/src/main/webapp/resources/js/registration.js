
function validatePassword(){
	var password = document.getElementById("password");
	var confirm_password = document.getElementById("passwordConfirm");
	if(password.value != confirm_password.value) {
		confirm_password.setCustomValidity("Las contraseñas no coinciden");
	} else {
		confirm_password.setCustomValidity("");
	}
}