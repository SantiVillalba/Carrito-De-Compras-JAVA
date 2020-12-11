//comprueba que los campos de registrar cliente no esten vacios.
$(document).ready(function (){
    limpiarCampos();
    
    $('#btnRegistrar').click(function (){
        validarDatos();
    });
    
    $('#btnCancelar').click(function (){
        history.back();
    });
    
});

function limpiarCampos(){
    $('.textBox').val("");
}

function validarDatos(){
    var nombre = $('#txtNombre').val();
    var apellido = $('#txtApellido').val();
    var email = $('#txtEmail').val();
    var email2 = $('#txtConfirEmail').val();
    var usuario = $('#txtUsuario').val();
    var pass1 = $('#txtClave').val();
    var pass2 = $('#txtConfirClave').val();
    
    if(nombre == ""){
        alert("El campo nombre no puede estar vacio");
        $('#txtNombre').focus();
    }
    else if(apellido == ""){
        alert("El campo apellido no puede estar vacio");
        $('#txtApellido').focus();
    }
    else if(email == ""){
        alert("El campo email no puede estar vacio");
        $('#txtEmail').focus();
    }
    else if(email2 == ""){
        alert("El campo confirmar email no puede estar vacio");
        $('#txtConfirEmail').focus();
    }
    else if(usuario == ""){
        alert("El campo usuario no puede estar vacio");
        $('#txtUsuario').focus();
    }
    else if(pass1 == ""){
        alert("El campo clave no puede estar vacio");
        $('#txtClave').focus();
    }
    else if(pass2 == ""){
        alert("El campo confirmar clave no puede estar vacio");
        $('#txtConfirClave').focus();
    }
    else{
        if(email != email2){
            alert("Los correos electronicos no son iguales");
        }
        else if(pass1 != pass2){
            alert("Las contraseñas no son iguales");
        }
        else{
            $('#frmRegistrarUsuario').submit();
        }
        
    }
}