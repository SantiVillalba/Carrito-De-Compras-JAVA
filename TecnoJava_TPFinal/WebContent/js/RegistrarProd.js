//comprueba que los campos de registrar producto no esten vacios.
$(document).ready(function(){
    $('.textBox').val("");
    
    $('#btnCancelar').click(function(){
        history.back();
    });
    
    $('#btnRegistrar').click(function(){
        validarDatos();
    });
});

function validarDatos(){
    var clase = $('#txtClase').val();
    var marca = $('#txtMarca').val();
    var descripcion = $('#txtDescripcion').val();
    var precio = $('#txtPrecio').val();
    
    if(clase == ""){
        alert("El campo clase producto no puede estar vacio");
        $('#txtClase').focus();
    }
    else if(marca == ""){
        alert("El campo marca producto no puede estar vacio");
        $('#txtMarca').focus();
    }
    else if(descripcion == ""){
        alert("El campo descripcion del producto no puede estar vacio");
        $('#txtDescripcion').focus();
    }
    else if(precio == ""){
        alert("El campo precio del producto no puede estar vacio");
        $('#txtPrecio').focus();
    }
    else{
        $('#frmRegistrarProd').submit();
    }
}