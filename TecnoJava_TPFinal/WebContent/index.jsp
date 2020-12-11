<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList" %>
<%@page import="clases.*"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="js/jquery-3.5.1.min.js"></script>
        <script type="text/javascript" src="js/index.js"></script>
        <link href="css/style.css" rel="stylesheet" type="text/css">
        <title>Carrito de compras</title>
    </head>
    
    <body onload="cargar()">
        
        <%
            String codigo = (String)session.getAttribute("parametroCodigo");
            Usuario usu = Usuario_DB.listarUsuarioPorCodigo(codigo);
            String cliente = usu.getNombreUsuario() + ", " + usu.getApellidosUsuario();
        %>
        
        <form action="Servlet_Usu" method="post" id="frmCabecera">
            <input type="hidden" value="<%=usu.getTipoUsuario() %>" name="txtTipo" id="txtTipo">
            <table id="tablaCabecera">
                <tr>
                    <td>
                        <a href="RegistrarVenta.jsp" class="link" id="lnkCompra">Mi compra</a>
                    </td>
                    <td>
                        <a href="MiPerfil.jsp?codigoU=<%=usu.getCodigoUsuario() %>" class="link" id="lnkPerfil">Mi cuenta</a>  
                    </td>
                    <td>
                        <a href="CuentaAdministrador.jsp" class="link" id="lnkAdm">Administrar</a>
                    </td>
                    <td>
                        <a href="Login.jsp" class="link" id="lnkLogin">Login</a>
                    </td>
                    <td>
                        <a href="Servlet_Usu?accion=logout" class="link" id="lnkLogout">Salir</a>
                    </td>
                </tr>
            </table>
            <input type="hidden" name="accion" value="logout">
        </form>
        <hr>

        <form id="frmCatalogo">
            <br><br><br>
            <table id="tablaCatalogo">
                <tbody>
                <%
                    ArrayList<Producto> lista = Producto_DB.obtenerProductosHabilitados();
                    int salto = 0;
                    for(int i=0; i<lista.size(); i++){
                        Producto p = lista.get(i);
                %>
                        
                            <th>
                                <%=p.getClaseProducto()%> <%=p.getMarcaProducto()%> <br>
                                <%=p.getDescripcion()%><br><br><%out.print("Stock disponible: " + p.getStock());%><br><br>
                                $ <%=p.getPrecioP() %><br>
                            <input type="button" class="BotonADD" name="btnADD" value="Add al Carrito" onclick="location.href='AnadirCarrito.jsp?codigoP=<%=p.getCodigoP()%>&&cliente=<%=cliente %>'">
                            <input type="button" class="BotonModificar" name="btnModificar" value="Modificar" onclick="location.href='ModificarProducto.jsp?codigoP=<%=p.getCodigoP() %>'"><br><br><br>
                            </th>
                <%
                        salto++;
                        if(salto == 3){
                            %>
                            
                            <tr>
                                
                            <%
                            salto = 0;
                        } 
                    }
                %>
                </tbody>
                
            </table>
        </form>  
    </body>
</html>