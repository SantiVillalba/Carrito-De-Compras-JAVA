<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="clases.*" %>
<%@ page import="java.util.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link type="text/css" rel="stylesheet" href="css/style.css" />
<title>Carrito de compras</title>
</head>
<body>
<form>
	<table>
		<tr>
			<th>
				<h1>Usuarios eliminados</h1>
			</th>
		</tr>
		<tr>
			<td>
				<a href="CuentaAdministrador.jsp" class="link">Pagina principal</a>
			</td>
		</tr>
	</table>
</form>
<form>
	<table>
		<tr>
			<th class="Titulo">
				CODIGO
			</th>
			<th class="Titulo">
				NOMBRE
			</th>
			<th class="Titulo">
				APELLIDO
			</th>
			<th class="Titulo">
				CORREO
			</th>
			<th class="Titulo">
				USUARIO
			</th>
			<th class="Titulo">
				TIPO
			</th>
			<th class="Titulo">
				ESTADO
			</th>
			<th class="Titulo">
				OPCION
			</th>
		</tr>
		<%
			ArrayList<Usuario> lista = Usuario_DB.MostrarUsuarioInhabilitado();
			for(int i = 0; i<lista.size();i++){
				Usuario usu = lista.get(i);
		%>
		<tr>
			<td>
				<%=usu.getCodigoUsuario() %>
			</td>
			<td>
				<%=usu.getNombreUsuario() %>
			</td>
			<td>
				<%=usu.getApellidosUsuario() %>
			</td>
			<td>
				<%=usu.getEmailUsuario()%>
			</td>
			<td>
				<%=usu.getIdUsuario()%>
			</td>
			<td>
				<%=usu.getTipoUsuario()%>
			</td>
			<td>
				<%=usu.getEstadoUsuario()%>
			</td>
			<td class="Opcion">
				<input type="button" name="btnRecuperar" id="btnRecuperar" class="button" value="Recuperar" onclick="location.href='Servlet_Usu?codigoU=<%=usu.getCodigoUsuario() %>&&accion=recuperar'">
			</td>
		</tr>
		<%
			}
		%>
	</table>
</form>
</body>
</html>