<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="clases.*" %>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/style.css" rel="stylesheet" type="text/css">
        <title>Carrito de compras</title>
    </head>
    
    <body>
        <form id="frmMostrarCabeceraCP">
            <table id="tablaMostrarCabeceraCP">
                <tr>
                    <th>
                        <h1> Clase productos habilitados </h1>
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
        
        <form id="frmMostrarCuerpoCP">
            <table id="tablaMostrarCuerpoCP">
                <tr>
                    <th class="Titulo">
                        CODIGO
                    </th>
                    <th class="Titulo">
                        NOMBRE
                    </th>
                    <th class="Titulo">
                        ESTADO
                    </th>
                    <th colspan="2" class="Titulo">
                        OPCION
                    </th>
                </tr>    
                
                <%
                    ArrayList<ClaseP> lista = ClaseP_DB.ObtenerCPHabilitados();
                    for(int i=0; i<lista.size(); i++){
                        ClaseP CP = lista.get(i);
                        %>
                        
                <tr>
                    <td>
                        <%=CP.getCodigoCP() %>
                    </td>
                    <td>
                        <%=CP.getNombreCP() %>
                    </td>
                    <td>
                        <%=CP.getEstadoCP() %>
                    </td>
                    <td class="Opcion">
                        <input type="button" name="btnModificar" value="Modificar" id="btnModificar" class="button" onclick="location.href='ModificarCP.jsp?codigoCP=<%=CP.getCodigoCP() %>&&nombreCP=<%=CP.getNombreCP() %>&&estadoCP=<%=CP.getEstadoCP() %>'">
                    </td>
                    <td class="Opcion">
                        <input type="button" name="btnEliminar" value="Eliminar" id="btnEliminar" class="button" onclick="location.href='Servlet_CP?codigoCP=<%=CP.getCodigoCP() %>&&accion=eliminar'">
                    </td>
                </tr>
                
                <%
                    }
                %>

            </table>
        </form>
    </body>
</html>