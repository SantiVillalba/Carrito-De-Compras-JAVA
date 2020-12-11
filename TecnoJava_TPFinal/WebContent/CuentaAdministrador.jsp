<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/style.css" rel="stylesheet" type="text/css">
        <title>Carrito de compras</title>
    </head>
    <body>
        <form id="frmAdministrador">
            <table id="tablaAdministrador">
                <tr>
                    <th colspan="5">
                        <h2> Menu administrador </h2>
                    </th>
                </tr>
                <tr>
                    <th>
                        Registrar
                    </th>
                    <th>
                        habilitados
                    </th>
                    <th>
                        inhabilitados
                    </th>
                    <th>
                        Ventas realizadas
                    </th>
                    <th>
                        Mi catalogo
                    </th>
                </tr>
                <tr>
                    <td>
                        <a href="RegistrarClaseProducto.jsp" class="link">Nueva clase producto</a>
                    </td>
                    <td>
                        <a href="MostrarCP.jsp" class="link">Clase producto</a>
                    </td>
                    <td>
                        <a href="MostrarCPEliminados.jsp" class="link">Clase producto</a>
                    </td>
                    <td>
                        <a href="MostrarVenta.jsp" class="link">Ir a ventas</a>
                    </td>
                    <td>
                        <a href="index.jsp" class="link">Ir a catalogo</a>
                    </td>
                </tr>
                <tr>
                    <td>
                        <a href="RegistrarMarcaProducto.jsp" class="link">Nueva marca producto</a>
                    </td>
                    <td>
                        <a href="MostrarMP.jsp" class="link">Marca producto</a>
                    </td>
                    <td>
                        <a href="MostrarMPEliminados.jsp" class="link">Marca producto</a>
                    </td>
                    <td>
                        
                    </td>
                    <td>
                        
                    </td>
                </tr>
                <tr>
                    <td>
                        <a href="RegistrarProducto.jsp" class="link">Nuevo producto</a>
                    </td>
                    <td>
                        <a href="MostrarProducto.jsp" class="link">Producto</a>
                    </td>
                    <td>
                        <a href="MostrarProductosEliminados.jsp" class="link">Producto</a>
                    </td>
                    <td>
                        
                    </td>
                    <td>
                        
                    </td>
                </tr>
                <tr>
                	<td>
                		
                	</td>
                	<td>
                		<a href="MostrarUsuario.jsp" class="link">Usuario</a>
                	</td>
                	<td>
                		<a href="MostrarUsuarioEliminado.jsp" class="link">Usuario</a>
                	</td>
                	<td>
                		
                	</td>
                	<td>
                		
                	</td>
                </tr>
            </table>
        </form>
        
    </body>
</html>