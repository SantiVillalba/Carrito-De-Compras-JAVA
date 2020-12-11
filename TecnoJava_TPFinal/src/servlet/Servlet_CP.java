package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import clases.ClaseP;
import clases.ClaseP_DB;

/**
 * Servlet implementation class Servlet_CP
 */
@WebServlet("/Servlet_CP")
public class Servlet_CP extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet_CP() {
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
            if(accion.equals("insertar")){
                this.RegistrarClaseProducto(request, response);
            }
            if(accion.equals("actualizar")){
                this.ActualizarClaseProducto(request, response); 
            }
            if(accion.equals("eliminar")){
                this.EliminarClaseProducto(request, response); 
            }
            if(accion.equals("recuperar")){
                this.RecuperarClaseProducto(request, response); 
            }
            
        }
    	
    }
    /**
     * Envia el nombre de la clase producto que se quiere registrar al metodo insertarClaseProducto
     * de la clase ClaseP_DB.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void RegistrarClaseProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	ClaseP cp = new ClaseP();
        cp.setNombreCP(request.getParameter("txtNombre")); 
        
        boolean resp = ClaseP_DB.insertarClaseProducto(cp);
        if(resp){
            response.sendRedirect("mensaje.jsp?mens='Se ha registrado una clase de producto correctamente'");
        }else{
            response.sendRedirect("mensaje.jsp?mens='Error al registrar en la clase producto'"); 
        }
    	
    }
    /**
     * Envia el codigo de la clase producto y el nombre con el que se quiere modificar la clase
     * producto al metodo actualizarClaseProducto de la clase ClaseP_DB.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void ActualizarClaseProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	ClaseP cp = new ClaseP();
        cp.setCodigoCP(request.getParameter("txtCodigo"));
        cp.setNombreCP(request.getParameter("txtNombre")); 
        
        boolean resp = ClaseP_DB.actualizarClaseProducto(cp);
        if(resp){
            response.sendRedirect("mensaje.jsp?mens='Se ha modificado la clase producto "+cp.getCodigoCP()+"'");
        }else{
            response.sendRedirect("mensaje.jsp?mens='Error al actualizar la clase producto'");
        }
    	
    }
    /**
     * Envia el codigo de la clase producto que se quiere inhabilitar al metodo darBajaClaseProducto
     * de la clase ClaseP_DB.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void EliminarClaseProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	ClaseP cp = new ClaseP();
        cp.setCodigoCP(request.getParameter("codigoCP"));
        
        boolean resp = ClaseP_DB.darBajaClaseProducto(cp);
        if(resp){
            response.sendRedirect("mensaje.jsp?mens='Se ha eliminado la clase producto "+cp.getCodigoCP()+"'");
        }else{
            response.sendRedirect("mensaje.jsp?mens='Error al eliminar la clase producto'");
        }
    	
    }
    /**
     * Envia el codigo de la clase producto que se quiere habilitar al metodo RecuperarClaseProducto
     * de la clase ClaseP_DB.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void RecuperarClaseProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	ClaseP cp = new ClaseP();
        cp.setCodigoCP(request.getParameter("codigoCP"));
        
        boolean resp = ClaseP_DB.darAltaClaseProducto(cp);
        if(resp){
            response.sendRedirect("mensaje.jsp?mens='Se ha recuperado la clase producto "+cp.getCodigoCP()+"'");
        }else{
            response.sendRedirect("mensaje.jsp?mens='Error al recuperar la clase producto'");
        }
    	
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
