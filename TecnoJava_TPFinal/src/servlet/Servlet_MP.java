package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import clases.MarcaP;
import clases.MarcaP_DB;

/**
 * Servlet implementation class Servlet_MP
 */
@WebServlet("/Servlet_MP")
public class Servlet_MP extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet_MP() {
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
                this.RegistrarMarcaProducto(request, response);
            }
            if(accion.equals("actualizar")){
                this.ActualizarMarcaProducto(request, response);
            }
            if(accion.equals("eliminar")){
                this.darBajaMarcaProducto(request, response);
            }
            if(accion.equals("recuperar")){
                this.darAltarMarcaProducto(request, response);
            }
        }
    	
    }
    /**
     * Envia el nombre de la marca producto que se quiere registrar al metodo insertarMarcaProducto
     * de la clase MarcaP_DB.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void RegistrarMarcaProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	MarcaP mp = new MarcaP();
        mp.setNombreMP(request.getParameter("txtNombre"));
        
        boolean resp = MarcaP_DB.insertarMarcaProducto(mp);
        if(resp){
            response.sendRedirect("mensaje.jsp?mens='Se ha registrado una marca de producto correctamente'"); 
        }else{
            response.sendRedirect("mensaje.jsp?mens='Error al registrar la marca producto'"); 
        }
    	
    }
    /**
     * Envia el codigo de la marca producto y el nombre al metodo actualizarMarcaProducto de la
     * clase MarcaP_DB para modificar la marca producto.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void ActualizarMarcaProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	MarcaP mp = new MarcaP();
        mp.setCodigoMP(request.getParameter("txtCodigo"));
        mp.setNombreMP(request.getParameter("txtNombre")); 
        
        boolean resp = MarcaP_DB.actualizarMarcaProducto(mp);
        if(resp){
            response.sendRedirect("mensaje.jsp?mens='Se ha modificado la marca producto "+mp.getCodigoMP()+"'"); 
        }else{
            response.sendRedirect("mensaje.jsp?mens='Error al modificar la marca producto'"); 
        }
    	
    }
    /**
     * Envia el codigo de la marca producto que se quiere inhabilitar al metodo eliminarMarcaProducto
     * de la clase MarcaP_DB.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void darBajaMarcaProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	MarcaP mp = new MarcaP();
        mp.setCodigoMP(request.getParameter("codigoMP"));
        
        boolean resp = MarcaP_DB.eliminarMarcaProducto(mp);
        if(resp){
            response.sendRedirect("mensaje.jsp?mens='Se ha eliminado la marca producto "+mp.getCodigoMP()+"'"); 
        }else{
            response.sendRedirect("mensaje.jsp?mens='Error al eliminar la marca producto'"); 
        }
    	
    }
    /**
     * Envia el codigo de la marca producto que se quiere habilitar al metodo recuperarMarcaProducto
     * de la clase MarcaP_DB.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void darAltarMarcaProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	MarcaP mp = new MarcaP();
        mp.setCodigoMP(request.getParameter("codigoMP"));
        
        boolean resp = MarcaP_DB.recuperarMarcaProducto(mp);
        if(resp){
            response.sendRedirect("mensaje.jsp?mens='Se ha recuperado la marca producto "+mp.getCodigoMP()+"'"); 
        }else{
            response.sendRedirect("mensaje.jsp?mens='Error al recuperar la marca producto'"); 
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
