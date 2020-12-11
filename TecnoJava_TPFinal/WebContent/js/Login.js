//comprueba que los campos del login no esten vacios.
//boton cancelar vuelve a la página anterior.
$(document).ready(function (){
    limpiarCamposLogin();
    
    $('#btnIngresar').click(function (){
        validarLogin();
    });
    $('#btnCancelar').click(function() {
    	history.back();
    	
	});
});

function validarLogin(){
    var Usuario = $('#txtUsuario').val();
    var Password = $('#txtClave').val();
    
    if(Usuario == ""){
        alert("El campo Usuario no puede estar en blanco");
        $('#txtUsuario').focus();
    }
    else if(Password == ""){
        alert("El campo Contraseña no puede estar en blanco");
        $('#txtClave').focus();
    }
    else{
        $('#frmLogin').submit();
    }
}

function limpiarCamposLogin(){
    $('.textBox').val("");
}