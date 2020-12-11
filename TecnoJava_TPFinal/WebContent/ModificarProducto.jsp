<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*" %>
<%@page import="clases.*" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="js/jquery-3.5.1.min.js"></script>
        <script type="text/javascript" src="js/ModificarProd.js"></script>
        <link href="css/style.css" rel="stylesheet" type="text/css">
        <title>Carrito de compras</title>
    </head>
    
    <%
        Producto p = Producto_DB.listarProductoPorCodigo(request.getParameter("codigoP"));
    %>
    
    <body onload="cargar()">
        <form name="frm" action="Servlet_Prod" method="post" id="frmModificarProd">
            <table id="tablaModificarProd">
                <tr>
                    <th colspan="2">
                        <h1>Modificar producto</h1>
                    </th>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="hidden" name="txtCodigo" value="<%=p.getCodigoP() %>">
                    </td>
                </tr>
                <tr>
                    <td class="primeraColumna">
                        Codigo producto :
                    </td>
                    <td>
                        <dd> <%=p.getCodigoP() %> </dd>
                    </td>
                </tr>
                <tr>
                    <td class="primeraColumna">
                        Nombre CP :
                    </td>
                    <td>
                        <dd> <input type="text" name="txtNombreCP" id="txtNombreCP" value="<%=p.getClaseProducto() %>" size="30" maxlength="30"> </dd>
                    </td>
                </tr>
                <tr>
                    <td class="primeraColumna">
                        Nombre MP :
                    </td>
                    <td>
                        <dd> <input type="text" name="txtNombreMP" id="txtNombreMP" value="<%=p.getMarcaProducto() %>" size="30" maxlength="30"> </dd>
                    </td>
                </tr>
                <tr>
                    <td class="primeraColumna">
                        Descripcion producto :
                    </td>
                    <td>
                        <dd> <input type="text" name="txtDescripcionP" id="txtDescripcionP" value="<%=p.getDescripcion() %>" size="30" maxlength="30"> </dd>
                    </td>
                </tr>
                <tr>
                    <td class="primeraColumna">
                        Precio producto :
                    </td>
                    <td>
                        <dd> <input type="text" name="txtPrecioP" id="txtPrecioP" value="<%=p.getPrecioP() %>" size="30" maxlength="30"> </dd>
                    </td>
                </tr>
                <tr>
                    <td class="primeraColumna">
                        Stock Producto :
                    </td>
                    <td>
                        <dd> <input type="number" name="txtStockP" id="txtStockP" value="<%=p.getStock() %>" min="1"> </dd>
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