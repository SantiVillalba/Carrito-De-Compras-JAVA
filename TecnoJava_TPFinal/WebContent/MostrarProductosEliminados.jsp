<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList" %>
<%@page import="clases.*" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/style.css" rel="stylesheet" type="text/css">
        <title>Carrito de compras</title>
    </head>
    
    <body>
        <form id="frmMostrarCabeceraProdEliminados">
            <table id="tablaMostrarCabeceraProdEliminados">
                <tr>
                    <th>
                        <h1> Productos Inhabilitados </h1>
                    </th>
                </tr>
                <tr>
                    <td>
                        <a href="CuentaAdministrador.jsp" class="link"><h4> Pagina principal </h4></a>
                    </td>
                </tr>
            </table>
        </form>
        
        <hr>
        
        <form id="frmMostrarCuerpoProdEliminados">
            <table id="tablaMostrarCuerpoProdEliminados">
                <tr>
                    <th class="Titulo">
                        CODIGO
                    </th>
                    <th class="Titulo">
                        CLASE
                    </th>
                    <th class="Titulo">
                        MARCA
                    </th>
                    <th class="Titulo">
                        DESCRIPCION
                    </th>
                    <th class="Titulo">
                        PRECIO
                    </th>
                    <th class="Titulo">
                        ESTADO
                    </th>
                    <th class="Titulo">
                        OPCION
                    </th>
                </tr>
                
                <% 
                    ArrayList<Producto> lista = Producto_DB.obtenerProductosInhabilitados();
                    for(int i=0; i<lista.size(); i++){
                        Producto P = lista.get(i);
                %>
                    
                <tr>
                    <td>
                        <%=P.getCodigoP() %>
                    </td>
                    <td>
                        <%=P.getClaseProducto() %>
                    </td>
                    <td>
                        <%=P.getMarcaProducto() %>
                    </td>
                    <td>
                        <%=P.getDescripcion() %>
                    </td>
                    <td>
                        <%=P.getPrecioP() %>
                    </td>
                    <td>
                        <%=P.getEstadoP() %>
                    </td>
                    <td class="Opcion">
                        <input type="button" name="btnRecuperar" id="btnRecuperar" class="button" value="Recuperar" onclick="location.href='Servlet_Prod?codigoP=<%=P.getCodigoP() %>&&accion=recuperar'">
                    </td>
                </tr>
                
                <%
                    }
                %>
    
            </table>
        </form>
    </body>
</html>