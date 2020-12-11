<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="clases.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="js/jquery-3.5.1.min.js"></script>
        <script type="text/javascript" src="js/CambiarEmail.js"></script>
        <link href="css/style.css" rel="stylesheet" type="text/css">
        <title>Carrito de compras</title>
    </head>
    
    <%
        Usuario usu = Usuario_DB.listarUsuarioPorCodigo(request.getParameter("codigoU"));
    %>
    
    <body onload="cargar()">
        <form action="Servlet_Usu" method="post" id="frmModificarEmail">
            <table id="tablaModificarEmail">
                <tr>
                    <th colspan="2">
                        <h1>Cambiar Email</h1>
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
                        Correo actual : 
                    </td>
                    <td>
                        <dd> <%=usu.getEmailUsuario() %> </dd>
                    </td>
                </tr>
                <tr>
                    <td class="primeraColumna">
                        Ingrese nuevo Email : 
                    </td>
                    <td>
                        <dd> <input type="text" name="txtEmail" id="txtEmail" class="textBox" size="30" maxlength="50"> </dd>
                    </td>
                </tr>
                <tr>
                    <td class="primeraColumna">
                        Confirmar su Email :
                    </td>
                    <td>
                        <dd> <input type="text" name="txtConfirmarEmail" id="txtConfirEmail" class="textBox" size="30" maxlength="50"> </dd>
                    </td>
                </tr>
                <tr>
                    <td class="primeraColumna">
                        Ingrese su contraseña :
                    </td>
                    <td>
                        <dd> <input type="password" name="txtPass" id="txtPass" class="textBox" size="30" maxlength="50"> </dd>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" class="Botones">
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
                    <input type="hidden" name="accion" value="modificarEmail">
        </form>
    </body>
</html>