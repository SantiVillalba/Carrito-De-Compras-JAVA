package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import conexion.Conexion;
import clases.Usuario;
import clases.Usuario_DB;

/**
 * Servlet implementation class Servlet_Usu
 */
@WebServlet("/Servlet_Usu")
public class Servlet_Usu extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet_Usu() {
        super();
        
    }
    /**
     * Maneja las solicitudes que se realizan al doPost y al doGet.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String accion = request.getParameter("accion");
            
            if(accion.equals("login")){
                Login(request, response); 
            }
            if(accion.equals("registrar")){
                RegistrarUsuario(request, response); 
            }
            if(accion.equals("modificarUsuario")){
                ModificarUsuario(request, response); 
            }
            if(accion.equals("modificarClave")){
                ModificarClaveUsuario(request, response); 
            }
            if(accion.equals("modificarEmail")){
                ModificarEmailUsuario(request, response); 
            }
            if(accion.equals("eliminar")){
                DarBajaUsuario(request, response); 
            }
            if(accion.equals("recuperar")){
                DarAltaUsuario(request, response); 
            }
            if(accion.equals("logout")){
                Logout(request, response); 
            }
        }
    	
    }
    /**
     * Envia el usuario y clave al metodo VerificarUsuario de la clase Usuario_DB para que verifique
     * si los datos ingresados por el usuario existen en la base de datos y permitir el Login
     * del usuario.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void Login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
        Conexion con = new Conexion();
        Connection cn = con.getConnection();
            
        String usuario = request.getParameter("txtUsuario");
        String clave = request.getParameter("txtClave");
            
        try{
            Usuario usu = Usuario_DB.VerificarUsuario(usuario);
            
            if(usuario.equals(usu.getIdUsuario())){
                if(clave.equals(usu.getClaveUsuario())){
                    if("HAB".equals(usu.getEstadoUsuario())){
                        request.getSession().setAttribute("parametroCodigo", usu.getCodigoUsuario());
                        response.sendRedirect("index.jsp");
                    }else{
                        response.sendRedirect("Respuesta.jsp?mens='Usted ha sido inhabilitado del sistema'");
                    }
                }else{
                    response.sendRedirect("Respuesta.jsp?mens='Su clave es incorrecto'"); 
                }
            }else{
                response.sendRedirect("Respuesta.jsp?mens='Su id de usuario es incorrecto'"); 
            }
            cn.close();
        }catch(Exception e){
        	System.out.println("ERROR: "+ e);
        }
    	
    }
    /**
     * Envia los datos del usuario al metodo RegistrarUsuario de la clase Usuario_DB para que 
     * registre al usuario nuevo.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void RegistrarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String Nombre = request.getParameter("txtNombres");
        String Apellidos = request.getParameter("txtApellidos");
        String Email = request.getParameter("txtEmail");
        String Usuario = request.getParameter("txtUsuario");
        String Clave = request.getParameter("txtClave");
        
        Usuario usu = new Usuario();
        usu.setNombreUsuario(Nombre);
        usu.setApellidosUsuario(Apellidos);
        usu.setEmailUsuario(Email);
        usu.setIdUsuario(Usuario);
        usu.setClaveUsuario(Clave);
         
        boolean resp = Usuario_DB.RegistrarUsuario(usu);
        if(resp){
            response.sendRedirect("Respuesta.jsp?mens='Se ha registrado correctamente'");
        }else{
            response.sendRedirect("Respuesta.jsp?mens='Error: Estamos solucionando un problema'"); 
        }
    	
    }
    /**
     *  Modifica los datos nombre y apellido del usuario, envia los datos nuevos al metodo 
     *  ModificarUsuario de la clase Usuario_DB para que la modifique.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void ModificarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	Usuario usu = new Usuario();
        usu.setCodigoUsuario(request.getParameter("txtCodigo"));
        usu.setNombreUsuario(request.getParameter("txtNombre"));
        usu.setApellidosUsuario(request.getParameter("txtApellidos"));
        
        boolean resp = Usuario_DB.ModificarUsuario(usu);
        if(resp){
            response.sendRedirect("mensaje2.jsp?mens='Sus datos se han modificado correctamente'");
        }else{
            response.sendRedirect("mensaje2.jsp?mens='Error al modificar uno de sus datos'");
        }
    	
    }
    /**
     * Modifica la clave del usuario, envia los datos nuevos al metodo ModificarClaveUsuario de la
     * clase Usuario_DB para que la modifique.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void ModificarClaveUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String pass1 = request.getParameter("txtCaracter");
        String pass2 = request.getParameter("txtClaveActual");
        
        Usuario usu = new Usuario();
        usu.setCodigoUsuario(request.getParameter("txtCodigo"));
        usu.setClaveUsuario(request.getParameter("txtClave")); 
        
        if(pass1.equals(pass2)){
            boolean resp = Usuario_DB.ModificarClaveUsuario(usu);
            if(resp){
                response.sendRedirect("mensaje2.jsp?mens='Su password ha sido cambiado correctamente'");
            }else{
                response.sendRedirect("mensaje2.jsp?mens='Error al cambiar su contraseña'");
            }
        }else{
            response.sendRedirect("mensaje2.jsp?mens='Error el password actual no es correcta'");
        }
    	
    }
    /**
     * Modifica el email del usuario, envia los datos nuevos al metodo ModificarEmailUsuario de la
     * clase Usuario_DB para que lo modifique.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void ModificarEmailUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String pass1 = request.getParameter("txtCaracter");
        String pass2 = request.getParameter("txtPass");
        
        Usuario usu = new Usuario();
        usu.setCodigoUsuario(request.getParameter("txtCodigo"));
        usu.setEmailUsuario(request.getParameter("txtEmail")); 
        
        if(pass1.equals(pass2)){
            boolean resp = Usuario_DB.ModificarEmailUsuario(usu);
            if(resp){
                response.sendRedirect("mensaje2.jsp?mens='Su correo ha sido cambiado correctamente'");
            }else{
                response.sendRedirect("mensaje2.jsp?mens='Error al cambiar su correo electronico'");
            }
        }else{
            response.sendRedirect("mensaje2.jsp?mens='Error el password no es correcta'");
        }
    	
    }
    /**
     * Envia el codigo del usuario que se quiere inhabilitar al metodo DarDeBajaUsuario de la clase
     * Usuario_DB para que lo inhabilite.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void DarBajaUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	Usuario usu = new Usuario();
        usu.setCodigoUsuario(request.getParameter("codigoU"));
        
        boolean resp = Usuario_DB.DarDeBajaUsuario(usu);
        if(resp){
            response.sendRedirect("mensaje.jsp?mens='El usuario "+usu.getCodigoUsuario()+" ha sido inhabilitado correctamente'");
        }else{
            response.sendRedirect("mensaje.jsp?mens='Error al eliminar el usuario'");
        }
    	
    }
    /**
     * Envia el codigo del usuario que se quiere habilitar al metodo DarDeAltaUsuario de la clase
     * Usuario_DB para que lo habilite nuevamente.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void DarAltaUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	Usuario usu = new Usuario();
        usu.setCodigoUsuario(request.getParameter("codigoU"));
        
        boolean resp = Usuario_DB.DarDeAltaUsuario(usu);
        if(resp){
            response.sendRedirect("mensaje.jsp?mens='El usuario "+usu.getCodigoUsuario()+" acaba de ser habilitado correctamente'");
        }else{
            response.sendRedirect("mensaje.jsp?mens='Error al recuperar el usuario'");
        }
    	
    }
    /**
     * Cierra la session del usuario.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void Logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect("index.jsp");
    	
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		processRequest(request, response);
	}

}
