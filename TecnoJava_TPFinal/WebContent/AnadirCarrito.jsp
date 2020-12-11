<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*" %>
<%@page import="clases.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="js/jquery-3.5.1.min.js"></script>
        <script type="text/javascript" src="js/RegistrarCliente.js"></script>
        <link href="css/style.css" rel="stylesheet" type="text/css">
        <title>Carrito de compras</title>
    </head>
    <%
        String cliente = request.getParameter("cliente");
        Producto p = Producto_DB.listarProductoPorCodigo(request.getParameter("codigoP"));
    %>
    
    <body>
        <form name="frm" action="Servlet_Prod" method="post" id="frmAnadirCarrito">
            <input type="hidden" name="txtCliente" value="<%=cliente %>">
            <table id="tablaAnadirCarrito">
                <tr>
                    <th colspan="2">
                        <h1>Añadir a carrito</h1>
                    </th>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="hidden" name="txtCodigo" value="<%=p.getCodigoP() %>" size="20" maxlength="30">
                    </td>
                </tr>
                <tr>
                    <td class="primeraColumna">
                        Nombre Articulo :
                    </td>
                    <td>
                        <input type="text" name="txtNombreP" class="campoNoEditable" value="<%=p.getClaseProducto() %> <%=p.getMarcaProducto() %> - <%=p.getDescripcion() %>" 
                               size="60" readonly="readonly">
                    </td>
                </tr>
                <tr>
                    <td class="primeraColumna">
                        Precio Articulo :
                    </td>
                    <td>
                        <input type="text" name="txtPrecio" class="campoNoEditable" value="<%=p.getPrecioP() %>" size="20" maxlength="30" readonly="readonly">
                    </td>
                </tr>
                <tr>
                    <td class="primeraColumna">
                        Cantidad :
                    </td>
                    <td>
                        <input type="number" name="txtCantidad" value="1" min="1">
                    </td>
                </tr>
                <tr>
                    <td colspan="2" class="Botones">
                        <br>
                        <input type="submit" name="btnGuardar" id="btnGuardar" class="button" value="Add al carrito">
                        <input type="button" name="btnCancelar" id="btnCancelar" class="button" value="Cancelar">
                    </td>
                </tr>
                <tr>
                    <td>
                        <br>
                    </td>
                </tr>
            </table>
           <input type="hidden" name="accion" value="anadirCarrito">
        </form>
    </body>
</html>