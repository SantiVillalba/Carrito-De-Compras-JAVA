<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="clases.*"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="js/jquery-3.5.1.min.js"></script>
        <script type="text/javascript" src="js/MiPerfil.js"></script>
        <link href="css/style.css" rel="stylesheet" type="text/css">
        <title>Carrito de compras</title>
    </head>
    
    <%
        Usuario usu = Usuario_DB.listarUsuarioPorCodigo(request.getParameter("codigoU"));
    %>
    
    <body>
        <form action="Servlet_Usu" method="post" id="frmModificarUsuario">
            <table id="tablaModificarUsuario">
                <tr>
                    <th colspan="3">
                        <h1>Mi perfil</h1>
                    </th>
                </tr>
                <tr>
                    <td colspan="3">
                        <input type="hidden" name="txtCodigo" value="<%=usu.getCodigoUsuario() %>"> 
                    </td>
                </tr>
                <tr>
                    <td class="primeraColumna">
                        Nombre : 
                    </td>
                    <td>
                        <dd class="soloTexto"> <%=usu.getNombreUsuario() %> </dd>
                        <dd> <input type="text" name="txtNombre" id="txtNombre" class="campoOculto" value="<%=usu.getNombreUsuario() %>" size="30" maxlength="50"> </dd>
                        
                    </td>
                    <td></td>
                </tr>
                <tr>
                    <td class="primeraColumna">
                        Apellidos : 
                    </td>
                    <td>
                        <dd class="soloTexto"> <%=usu.getApellidosUsuario()%> </dd>
                        <dd> <input type="text" name="txtApellidos" id="txtApellido" class="campoOculto" value="<%=usu.getApellidosUsuario() %>" size="30" maxlength="50"> </dd>
                    </td>
                    <td></td>
                </tr>
                <tr>
                    <td class="primeraColumna">
                        Tipo de usuario :
                    </td>
                    <td>
                        <dd> <%=usu.getTipoUsuario() %> </dd>
                        
                    </td>
                    <td></td>
                </tr>
                <tr>
                    <td class="primeraColumna">
                        Email :
                    </td>
                    <td>
                        <dd> <%=usu.getEmailUsuario()%> </dd>
                    </td>
                    <td>
                        <dd> <a href="ModificarEmail.jsp?codigoU=<%=usu.getCodigoUsuario() %>" class="Enlace">Cambiar email</a> </dd>
                    </td>
                </tr>
                <tr>
                    <td class="primeraColumna">
                        Contraseña :
                    </td>
                    <td>
                        <dd> ********** </dd>
                    </td>
                    <td>
                        <dd> <a href="ModificarContraseña.jsp?codigoU=<%=usu.getCodigoUsuario() %>" class="Enlace">Cambiar contraseña</a> </dd>
                    </td>
                </tr>
                <tr>
                    
                </tr>
                <tr>
                    <td colspan="3" class="Botones">
                        <br>
                        <input type="button" name="btnGuardar" id="btnGuardar" class="campoOculto" value="Guardar">
                        <input type="button" name="btnModificar" id="btnModificar" class="button" value="Modificar">
                        <input type="button" name="btnCancelar" id="btnCancelar" class="button" value="Cancelar">
                    </td>
                </tr>
                <tr>
                    <td>
                        <br>
                    </td>
                </tr>
            </table>
                    <input type="hidden" name="accion" value="modificarUsuario">
        </form>
    </body>
</html>