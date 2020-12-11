//comprueba que los campos de modificar producto no esten vacios y envia el form modificar producto.
$(document).ready(function(){
    
    $('#btnCancelar').click(function(){
        history.back();
    });
    
    $('#btnGuardar').click(function(){
        validarDatos();
    });
    
});

function validarDatos(){
    var nombreCP = $('#txtNombreCP').val();
    var nombreMP = $('#txtNombreMP').val();
    var descripcionP = $('#txtDescripcionP').val();
    var precioP = $('#txtPrecioP').val();
    var stockP = $('#txtStockP').val();
    
    if(nombreCP == ""){
        alert("El campo clase producto no puede estar vacio");
        $('#txtNombreCP').focus();
    }
    else if(nombreMP == ""){
        alert("El campo marca producto no puede estar vacio");
        $('#txtNombreMP').focus();
    }
    else if(descripcionP == ""){
        alert("El campo descripcion producto no puede estar vacio");
        $('#txtDescripcionP').focus();
    }
    else if(precioP == ""){
        alert("El campo precio producto no puede estar vacio");
        $('#txtPrecioP').focus();
    }else if(stockP == ""){
        alert("El campo precio producto no puede estar vacio");
        $('#txtStockP').focus();
    }
    else{
        $('#frmModificarProd').submit();
    }
}