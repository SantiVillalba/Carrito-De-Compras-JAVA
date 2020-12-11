<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="clases.*" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="js/jquery-3.5.1.min.js"></script>
        <script type="text/javascript" src="js/CambiarPass.js"></script>
        <link href="css/style.css" rel="stylesheet" type="text/css">
        <title>Carrito de compras</title>
    </head>
    
    <%
        Usuario usu = Usuario_DB.listarUsuarioPorCodigo(request.getParameter("codigoU"));
    %>

    
    <body onload="cargar()">
        
        <form action="Servlet_Usu" method="post" id="frmModificarClave">
            <table id="tablaModificarClave">
                <tr>
                    <th colspan="2">
                        <h1>Cambiar contraseña</h1>
                    </th>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="hidden" name="txtCodigo" value="<%=usu.getCodigoUsuario() %>">
                        <input type="hidden" name="txtCaracter" value="<%=usu.getClaveUsuario() %>">
                    </td>
                </tr>
                <tr>
                    <td class="primeraColumna">
                        Ingrese su contraseña actual : 
                    </td>
                    <td>
                        <dd> <input type="password" name="txtClaveActual" id="txtClaveActual" class="textBox" size="20" maxlength="20"> </dd>
                    </td>
                </tr>
                <tr>
                    <td class="primeraColumna">
                        Ingrese nueva contraseña : 
                    </td>
                    <td>
                        <dd> <input type="password" name="txtClave" id="txtClave" class="textBox" size="20" maxlength="20"> </dd>
                    </td>
                </tr>
                <tr>
                    <td class="primeraColumna">
                        Confirmar su contraseña :
                    </td>
                    <td>
                        <dd> <input type="password" name="txtConfirmarClave" id="txtConfirClave" class="textBox" size="20" maxlength="20"> </dd>
                    </td>
                </tr>
                <tr>
                    <td class="Botones">
                        <br>
                        <input type="button" name="btnGuardar" id="btnGuardar" class="button" value="Guardar">
                        <input type="button" name="btnCancelar" id="btnCancelar" class="button" value="Cancelar">
                    </td>
                </tr>
                <tr>
                    <td>
                        <br>
                    </td>
                </tr>
            </table>
                    <input type="hidden" name="accion" value="modificarClave">
        </form>
    </body>
</html>