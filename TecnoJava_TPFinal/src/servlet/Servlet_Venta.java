package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
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
import clases.Venta;
import clases.Venta_DB;
import conexion.Conexion;

/**
 * Servlet implementation class Servlet_Venta
 */
@WebServlet("/Servlet_Venta")
public class Servlet_Venta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet_Venta() {
        super();
        
    }
    /**
     * Maneja las solicitudes que se realizan al doPost y al doGet.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     * @throws SQLException 
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
    	
    	response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String accion = request.getParameter("accion");
            
            if(accion.equals("RegistrarVenta")){
                this.RegistrarVenta(request, response); 
            }
            if(accion.equals("MostrarDetalle")){
                this.MostrarDetalleVenta(request, response); 
            }
            if(accion.equals("EliminarVenta")){
                this.EliminarVenta(request, response); 
            }
            if(accion.equals("quitar")){
                this.QuitarProducto(request, response); 
            }
        }
    	
    }
    /**
     * Registra la venta que se realizo por el cliente, envia los datos de la compra al metodo 
     * insertarVenta de la clase Venta_DB.
     * Actualiza el stock cuando se realiza la venta correctamente.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     * @throws SQLException 
     */
    private void RegistrarVenta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
    	
        Conexion con = new Conexion();
        Connection cn = con.getConnection();
        
    	HttpSession session = request.getSession(false);
        ArrayList lista = (ArrayList)session.getAttribute("carrito");
        ArrayList<DetalleVenta> listap = (ArrayList<DetalleVenta>)session.getAttribute("carrito");
        
        try {
        	if(lista.isEmpty() || listap.equals(null)) {
            	response.sendRedirect("mensaje2.jsp?mens='No hay productos agregados en su carrito'");
            }else {
            	
            	Venta v = new Venta();
                v.setCliente(request.getParameter("txtCliente")); 
                v.setTotal(Double.parseDouble(request.getParameter("txtTotal")));
                
                for (int k = 0; k < listap.size(); k++) {
        			Producto prodP = new Producto();
        			Producto_DB prodDB = new Producto_DB();
        			int idProd = listap.get(k).getCodigoProducto();
        			int cantProd = listap.get(k).getCantidad();
        			prodP = prodDB.Buscar(idProd);
        			
        			if(prodP.getStock() < cantProd) {
        				response.sendRedirect("mensaje2.jsp?mens='Lo sentimos no hay stock suficiente'");
        			}else {
        				
        				boolean resp = Venta_DB.insertarVenta(v);
        		        
        		        if(resp){
        		        	//Actualiza el stock del producto.
        		        	for (int i = 0; i < listap.size(); i++) {

        		    			Producto pr = new Producto();
        		    			int cantidad = listap.get(i).getCantidad();
        		    			int id = listap.get(i).getCodigoProducto();
        		    			Producto_DB pdb = new Producto_DB();
        		    			pr = pdb.Buscar(id);
        		    			int stockActual = pr.getStock() - cantidad;
        		    			pdb.actualizarStock(stockActual, id);

        		    		}
        		        	
        		            // Registra los detalle de la venta que se realiza
        		            String CodigoVenta = request.getParameter("txtCodigoV");
        		            String NombreProducto[] = request.getParameterValues("nombreProd");
        		            String PrecioProducto[] = request.getParameterValues("precioProd");
        		            String CantidadProducto[] = request.getParameterValues("cantidadProd");
        		            String SubTotalProducto[] = request.getParameterValues("subTotalProd");
        		            
        		            if("null".equals(CodigoVenta)){
        		                CodigoVenta = "V0001";
        		            }

        		            for(int i=0; i<NombreProducto.length;i++){
        		                try{
        		                    CallableStatement cs = cn.prepareCall("CALL REGISTRAR_DETALLE_VENTA (?,?,?,?,?)");
        		                    cs.setString(1, CodigoVenta);
        		                    cs.setString(2, NombreProducto[i]);
        		                    cs.setString(3, PrecioProducto[i]);
        		                    cs.setString(4, CantidadProducto[i]);
        		                    cs.setString(5, SubTotalProducto[i]);
        		                    int j = cs.executeUpdate();
        		                    if(j==1){
        		                        response.sendRedirect("mensaje2.jsp?mens='Se ha registrado su compra correctamente'"); 
        		                        lista.clear();
        		                    }else{
        		                        response.sendRedirect("mensaje2.jsp?mens='Error al registrar su compra'");
        		                    }
        		                }catch(Exception e){System.out.println(e);}
        		            }
        		        }else{
        		            response.sendRedirect("mensaje2.jsp?mens='Error al registrar su compra'");
        		        }
        			}
                
                cn.close();
                }
            }
		} catch (Exception e) {
			response.sendRedirect("mensaje2.jsp?mens='No hay productos agregados en su carrito'");
			System.out.println("EXCEPCION DEL SERVLET VENTA: "+ e);
		}
        
        
        
    }

    /**
     * Muestra los detalle de la venta que se realizo.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void MostrarDetalleVenta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	PrintWriter out = response.getWriter();

        request.getSession().setAttribute("CodigoVenta", request.getParameter("codigoV"));
        request.getSession().setAttribute("Cliente", request.getParameter("cliente"));
        request.getSession().setAttribute("Importe", request.getParameter("importe"));
        request.getSession().setAttribute("FechaVenta", request.getParameter("FechaV")); 
        request.getSession().setAttribute("CodigoCliente", request.getParameter("codigoCli")); 

        response.sendRedirect("MostrarDetalleVenta.jsp");
    	out.close();
    }
    /**
     * Quita el producto de la lista del carrito de compras que no se quiere comprar.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void QuitarProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(false); 
        
        int numeroVenta = Integer.parseInt(request.getParameter("numero"));
        ArrayList lista = (ArrayList)session.getAttribute("carrito");
        lista.remove(numeroVenta - 1);
        
        response.sendRedirect("RegistrarVenta.jsp");
        
    	out.close();
    }
    /**
     * Envia el codigo de la venta que se quiere borrar al metodo eliminarVenta de la clase
     * Venta_DB.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void EliminarVenta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	PrintWriter out = response.getWriter();

        Venta v = new Venta();
        v.setCodigoVenta(request.getParameter("codigoV"));
        boolean resp = Venta_DB.eliminarVenta(v);
        
        if(resp){
            response.sendRedirect("mensaje.jsp?mens='Se ha eliminado la venta correctamente "+ v.getCodigoVenta() +"'"); 
        }else{
            response.sendRedirect("mensaje.jsp?mens='Error al eliminar la venta " +v.getCodigoVenta()+ "'"); 
        }
    	out.close();
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			processRequest(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			processRequest(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
