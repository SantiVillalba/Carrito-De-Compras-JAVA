<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="js/jquery-3.5.1.min.js"></script>
        <script type="text/javascript" src="js/ModificarCP.js"></script>
        <link href="css/style.css" rel="stylesheet" type="text/css">
        <title>Carrito de compras</title>
    </head>
    
    <%
        
        String codigo = request.getParameter("codigoCP");
        String nombre = request.getParameter("nombreCP");
    %>
    
    <body onload="cargar()">
        <form action="Servlet_CP" method="post" name="frm" id="frmModificarCP">
            <table id="tablaModificarCP">
                <tr>
                    <th colspan="2">
                        <h1> Modificar CP </h1>
                    </th>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="hidden" name="txtCodigo" value="<%=codigo %>">
                    </td>
                </tr>
                <tr>
                    <td class="primeraColumna">
                        Codigo :
                    </td>
                    <td>
                        <dd> <%=codigo %> </dd> 
                    </td>
                </tr>
                <tr>
                    <td class="primeraColumna">
                        Clase producto :
                    </td>
                    <td>
                        <dd> <input type="text" name="txtNombre" id="txtNombre" value="<%=nombre %>"> </dd>
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
                    <input type="hidden" name="accion" value="actualizar">
        </form>
    </body>
</html>