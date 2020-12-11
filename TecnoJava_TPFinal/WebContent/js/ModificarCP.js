//comprueba que los campos de modificacion de clase producto no esten vacios.
//el botón cancelar redirecciona al jsp mostrar clase producto.
$(document).ready(function(){
    $('#btnCancelar').click(function(){
        $(location).attr('href',"MostrarCP.jsp");
    });
    
    $('#btnGuardar').click(function(){
        validarDatos();
    });
});

function validarDatos(){
    var nombre = $('#txtNombre').val();
    
    if(nombre == ""){
        alert("El campo clase producto no puede estar vacio");
        $('#txtNombre').focus();
    }
    else{
        $('#frmModificarCP').submit();
    }
}