<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="clases.*" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="js/jquery-3.5.1.min.js"></script>
        <script type="text/javascript" src="js/RegistrarCP.js"></script>
        <link href="css/style.css" rel="stylesheet" type="text/css">
        <title>Carrito de compras</title>
    </head>
    
    <%
        String Codigo_CP = ObtenerCodigo.CodigoClaseProducto();
    %>
    
    <body onload="cargar()">
        <form name="frm" action="Servlet_CP" method="post" id="frmRegistrarCP">
            <table id="tablaRegistrarCP">
                <tr>
                    <td colspan="2">
                        <h1>Registrar clase producto</h1>
                    </td>
                </tr>
                <tr>
                    <td class="primeraColumna">
                        Codigo :
                    </td>
                    <td>
                        <dd> <input type="text" name="txtCodigo" value="<%=Codigo_CP %>" readonly="readonly">  </dd>
                    </td>
                </tr>
                <tr>
                    <td class="primeraColumna">
                        Clase producto :
                    </td>
                    <td>
                        <dd> <input type="text" name="txtNombre" id="txtNombre" class="textBox"> </dd>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" class="Botones">
                        <br>
                        <input type="button" name="btnRegistrar" id="btnRegistrar" class="button" value="Registrar">
                        <input type="button" name="btnCancelar" id="btnCancelar" class="button" value="Cancelar"> 
                    </td>
                </tr>
                <tr>
                    <td>
                        <br>
                    </td>
                </tr>
            </table>
                    <input type="hidden" name="accion" value="insertar">
        </form>
    </body>
</html>