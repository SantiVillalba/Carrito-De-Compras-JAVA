//comprueba que los campos de modificacion de  mi perfil no esten vacios.
//boton cancelar redirecciona al index.
$(document).ready(function(){
    $('.campoOculto').hide();
    
    $('#btnModificar').click(function(){
        botonModificar();
    });
    
    $('#btnGuardar').click(function(){
        validarDatos();
    });
    
    $('#btnCancelar').click(function(){
        $(location).attr('href','index.jsp');
    });
});

function botonModificar(){
    $('.campoOculto').show();
    $('.soloTexto').hide();
    $('#btnModificar').hide();
}

function validarDatos(){
    var nombre = $('#txtNombre').val();
    var apellido = $('#txtApellido').val();
    
    if(nombre == ""){
        alert("El campo nombre no puede estar vacio");
        $('#txtNombre').focus();
        botonModificar();
    }
    else if(apellido == ""){
        alert("El campo apellido no puede estar vacio");
        $('#txtApellido').focus();
        botonModificar();
    }
    else{
        $('#frmModificarUsuario').submit();
    }
}