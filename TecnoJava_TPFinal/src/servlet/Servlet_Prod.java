package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import clases.DetalleVenta;
import clases.Producto;
import clases.Producto_DB;

/**
 * Servlet implementation class Servlet_Prod
 */
@WebServlet("/Servlet_Prod")
public class Servlet_Prod extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet_Prod() {
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
                this.RegistrarProducto(request, response); 
            }
            if(accion.equals("actualizar")){
                this.ActualizarProducto(request, response); 
            }
            if(accion.equals("eliminar")){
                this.DarBajaProducto(request, response); 
            }
            if(accion.equals("recuperar")){
                this.DarAltaProducto(request, response); 
            }
            if(accion.equals("anadirCarrito")){
                this.AnadirCarrito(request, response);
            }
        }
    	
    }
    /**
     * Envia los datos del producto nuevo que se quiere registrar al metodo insertarProducto de la 
     * clase Producto_DB para ser registrados.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void RegistrarProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	Producto p = new Producto();
    	
    	if(Double.parseDouble(request.getParameter("txtPrecio")) <= 0 || Integer.parseInt(request.getParameter("txtStock")) < 0) {
    		response.sendRedirect("mensaje.jsp?mens='Valores ingresados no permitidos.'");
    	}else {
    		p.setClaseProducto(request.getParameter("txtClase")); 
            p.setMarcaProducto(request.getParameter("txtMarca"));
            p.setDescripcion(request.getParameter("txtDescripcion"));
            p.setPrecioP(Double.parseDouble(request.getParameter("txtPrecio")));
            p.setStock(Integer.parseInt(request.getParameter("txtStock")));
            
            boolean resp = Producto_DB.insertarProducto(p);
            if(resp){
                response.sendRedirect("mensaje.jsp?mens='Se ha registrado un producto correctamente'");
            }else{
                response.sendRedirect("mensaje.jsp?mens='Error al registrar un producto'");
            }
    	}
    	
    }
    /**
     * Envia los datos nuevos del producto al metodo actualizarProducto de la clase Producto_DB 
     * para que modifique el producto.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void ActualizarProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	Producto p = new Producto();

        if(Double.parseDouble(request.getParameter("txtPrecioP")) <= 0 || Integer.parseInt(request.getParameter("txtStockP")) < 0 ) {
        	response.sendRedirect("mensaje.jsp?mens='Valores ingresados no permitidos.'");
        }else {
        	p.setCodigoP(Integer.parseInt(request.getParameter("txtCodigo"))); 
            p.setClaseProducto(request.getParameter("txtNombreCP")); 
            p.setMarcaProducto(request.getParameter("txtNombreMP"));
            p.setDescripcion(request.getParameter("txtDescripcionP"));
            p.setPrecioP(Double.parseDouble(request.getParameter("txtPrecioP")));
            p.setStock(Integer.parseInt(request.getParameter("txtStockP")));
        	boolean resp = Producto_DB.actualizarProducto(p);
        	if(resp){
                response.sendRedirect("mensaje.jsp?mens='Se ha modificado el producto "+p.getCodigoP()+"'"); 
            }else{
                response.sendRedirect("mensaje.jsp?mens='Error al modificar el producto'"); 
            }
        }
    	
    }
    /**
     * Envia el codigo del producto que se quiere inhabilitar al metodo eliminarProducto de la
     * clase Producto_DB para que lo inhabilite.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void DarBajaProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	Producto p = new Producto();
        p.setCodigoP(Integer.parseInt(request.getParameter("codigoP"))); 
        
        boolean resp = Producto_DB.eliminarProducto(p);
        if(resp){
            response.sendRedirect("mensaje.jsp?mens='Se ha eliminado el producto "+p.getCodigoP()+"'"); 
        }else{
            response.sendRedirect("mensaje.jsp?mens='Error al eliminar el producto'"); 
        }
    	
    }
    /**
     * Envia el codigo del producto que se quiere habilitar al metodo recuperarProducto de la clase
     * Producto_DB para que lo habilite.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void DarAltaProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	Producto p = new Producto();
        p.setCodigoP(Integer.parseInt(request.getParameter("codigoP"))); 
        
        boolean resp = Producto_DB.recuperarProducto(p);
        if(resp){
            response.sendRedirect("mensaje.jsp?mens='Se ha recuperado el producto "+p.getCodigoP()+"'"); 
        }else{
            response.sendRedirect("mensaje.jsp?mens='Error al recuperar el producto'"); 
        }
    	
    }
    /**
     * Añade al carrito de compras los productos y sus datos.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     * @throws NumberFormatException
     */
    protected void AnadirCarrito(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NumberFormatException {
    	
        HttpSession session = request.getSession();
        ArrayList<DetalleVenta> carritoCompra;
        
        if(session.getAttribute("carrito") == null){
            carritoCompra = new ArrayList<DetalleVenta>();
        }else{
            carritoCompra = (ArrayList<DetalleVenta>)session.getAttribute("carrito");
        }
        
        Producto p = Producto_DB.listarProductoPorCodigo(request.getParameter("txtCodigo")); 
        
        DetalleVenta dv = new DetalleVenta();
        dv.setCodigoProducto(Integer.parseInt(request.getParameter("txtCodigo"))); 
        dv.setNombreProducto(request.getParameter("txtNombreP")); 
        dv.setPrecio(Double.parseDouble(request.getParameter("txtPrecio")));
        dv.setCantidad(Integer.parseInt(request.getParameter("txtCantidad"))); 
        dv.setSubTotal(dv.getPrecio() * dv.getCantidad());
        
        int indice = -1;
        for(int i=0; i<carritoCompra.size(); i++){
            DetalleVenta det = carritoCompra.get(i);
            if(det.getCodigoProducto() == p.getCodigoP()){
                indice = i;
                break;
            }
        }
        if(indice == -1){
            dv.setNumero(String.valueOf(carritoCompra.size() + 1)); 
            carritoCompra.add(dv);
        }else{
            dv.setNumero(String.valueOf(indice + 1));
            carritoCompra.set(indice, dv);
        }
        
        session.setAttribute("carrito", carritoCompra); 
        request.getSession().setAttribute("parametroCliente", request.getParameter("txtCliente"));
        response.sendRedirect("RegistrarVenta.jsp");
        
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
