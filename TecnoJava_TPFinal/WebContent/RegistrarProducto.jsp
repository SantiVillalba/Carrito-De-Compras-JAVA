<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@page import="clases.*" %>
<%@page import="java.util.*" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="js/jquery-3.5.1.min.js"></script>
        <script type="text/javascript" src="js/RegistrarProd.js"></script>
        <link href="css/style.css" rel="stylesheet" type="text/css">
        <title>Carrito de compras</title>
    </head>
    
    <%
        String Codigo_P = ObtenerCodigo.CodigoProducto();
    	
    %>
    
    <body onload="cargar()">
        <form name="frm" action="Servlet_Prod" method="post" id="frmRegistrarProd">
            <table id="tablaRegistrarProd">
                <tr>
                    <th colspan="2">
                        <h1> Registrar producto  </h1>
                    </th>
                </tr>
                <tr>
                    <td class="primeraColumna">
                        Codigo :
                    </td>
                    <td>
                        <dd> <input type="text" name="txtCodigo" value="<%=Codigo_P %>" readonly="readonly"> </dd>
                    </td>
                </tr>
                
                <tr>
                	<td>
                		Clase producto :
                	</td>
                	<td><dd>
                		<select name="txtClase" id="txtClase">
                		<option>Escoge una opcion</option>
                		<%
                    		ArrayList<ClaseP> listac = ClaseP_DB.ObtenerCPHabilitados();
                			for (ClaseP lista : listac) {
                		%>
                			<option><%=lista.getNombreCP() %></option>
                			
                		<%
                    		}
                		%>
                		</select>
                		</dd>
                	</td>
                </tr>
                
                <tr>
                	<td>
                		Marca producto :
                	</td>
                	<td><dd>
                		<select name="txtMarca" id="txtMarca">
                		<option>Escoge una opcion</option>
                		<%
                    		ArrayList<MarcaP> listam = MarcaP_DB.obtenerMPHabilitados();
                			for (MarcaP listaMarca : listam) {
                		%>
                			<option><%=listaMarca.getNombreMP() %></option>
                			
                		<%
                    		}
                		%>
                		</select>
                		</dd>
                	</td>
                </tr>
                <tr>
                    <td class="primeraColumna">
                        Descripcion :
                    </td>
                    <td>
                        <dd> <input type="text" name="txtDescripcion" id="txtDescripcion" class="textBox"> </dd>
                    </td>
                </tr>
                <tr>
                    <td class="primeraColumna">
                        Precio :
                    </td>
                    <td>
                        <dd> <input type="text" name="txtPrecio" id="txtPrecio" class="textBox"> </dd>
                    </td>
                </tr>
                <tr>
                    <td class="primeraColumna">
                        Stock :
                    </td>
                    <td>
                        <dd> <input type="text" name="txtStock" id="txtStock" class="textBox"> </dd>
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