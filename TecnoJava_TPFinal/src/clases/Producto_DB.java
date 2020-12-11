package clases;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import clases.Producto;
import conexion.Conexion;


public class Producto_DB {
	
	int r;
	/**
	 * Ejecuta la consulta query y busca el producto que tenga el id que se le pasa por parametro.
	 * @param id
	 * @return El producto que tenga mismo id que se le pasa.
	 */
	public Producto Buscar(int id) {
		
		Producto p = new Producto();
		String query = "select * from producto where Codigo_P="+ id;
		
		try {
			
			Conexion con = new Conexion();
			Connection cn = con.getConnection();
			
			java.sql.PreparedStatement ps = cn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				
				p.setCodigoP(rs.getInt(1));
				p.setClaseProducto(rs.getString(2));
				p.setMarcaProducto(rs.getString(3));
				p.setDescripcion(rs.getString(4));
				p.setPrecioP(rs.getDouble(5));
				p.setStock(rs.getInt(6));
				p.setEstadoP(rs.getString(7));
				
			}
			
			cn.close();
			
		} catch (Exception e) {
			System.out.println("ERROR:"+ e);
		}
		
		return p;
	}
	/**
	 * Ejecuta la consulta query y actualiza el stock del producto.
	 * @param stock
	 * @param id
	 * @return true si se actualizo el stock.
	 */
	public int actualizarStock(int stock, int id) {
		
		String query = "update producto set Stock_P=? where Codigo_P=?";
		
		try {
			
			Conexion con = new Conexion();
			Connection cn = con.getConnection();
			
			java.sql.PreparedStatement ps = cn.prepareStatement(query);
			
			ps.setInt(1, stock);
			ps.setInt(2, id);
			ps.executeUpdate();
			
			cn.close();
			
		} catch (Exception e) {
			System.out.println("ERROR:"+ e);
		}
		
		return r;
	}
	
	// Traer todo los productos
	/**
	 * Ejecuta el procedimiento almacenado MOSTRAR_PRODUCTOS_HABILITADOS y llena el ArrayList lista
	 * con los productos que se encuentren habilitados.
	 * @return ArraList con los productos habilitados.
	 */
    public static ArrayList<Producto> obtenerProductosHabilitados(){
        
    	ArrayList<Producto> lista = new ArrayList<Producto>();
        
    	Conexion con = new Conexion();
		Connection cn = con.getConnection();
		
        try{
        	
        	CallableStatement cs = cn.prepareCall("CALL MOSTRAR_PRODUCTOS_HABILITADOS");
        	ResultSet rs = cs.executeQuery();
            
            while(rs.next()){
            	
                Producto p = new Producto(rs.getInt("Codigo_P"), rs.getString("Clase_P"), rs.getString("Marca_P"), rs.getString("Descripcion_P"), rs.getDouble("Precio_P"), rs.getInt("Stock_P"), rs.getString("Estado_P"));
                lista.add(p);
            }
            
            cn.close();
            
            }catch(Exception e){
                System.out.println(e);
            }
            
        return lista;
    }
    /**
     * Ejecuta el procedimiento almacenado MOSTRAR_PRODUCTOS_ELIMINADOS y llena el ArrayList lista
	 * con los productos que se encuentren inhabilitados.
	 * @return ArraList con los productos inhabilitados.
     */
    public static ArrayList<Producto> obtenerProductosInhabilitados(){
        
    	ArrayList<Producto> lista = new ArrayList<Producto>();
    	
        Conexion con = new Conexion();
		Connection cn = con.getConnection();
            
        try{
        	
            CallableStatement cs = cn.prepareCall("CALL MOSTRAR_PRODUCTOS_ELIMINADOS");
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
            	
                Producto p = new Producto(rs.getInt("Codigo_P"), rs.getString("Clase_P") , rs.getString("Marca_P"), rs.getString("Descripcion_P"), rs.getDouble("Precio_P"), rs.getInt("Stock_P"), rs.getString("Estado_P"));
                lista.add(p);
            }
            
            cn.close();
            
        }catch(Exception e){
            System.out.println(e);
        }
            
        return lista;
    }
    /**
     * Ejecuta el procedimiento almacenado REGISTRAR_PRODUCTO, registra el producto que recibe 
     * desde el metodo RegistrarProducto de el Servlet_Prod.
     * @param p
     * @return true si se registro correctamente
     */
    public static boolean insertarProducto(Producto p){
        
    	boolean resp = false;
        
        Conexion con = new Conexion();
		Connection cn = con.getConnection();
        
        try{
            CallableStatement cs = cn.prepareCall("CALL REGISTRAR_PRODUCTO (?,?,?,?,?)");
            cs.setString(1, p.getClaseProducto());
            cs.setString(2, p.getMarcaProducto());
            cs.setString(3, p.getDescripcion());
            cs.setDouble(4, p.getPrecioP());
            cs.setInt(5, p.getStock());
            int i = cs.executeUpdate();
            
            if(i==1)
                resp = true;
            else
                resp = false;
            
            cn.close();
            
        }catch(Exception e){
        	System.out.println(e);
        }
        
        return resp;
    }
    /**
     * Ejecuta el procedimiento almacenado MODIFICAR_PRODUCTO, modifica el producto que recibe desde
     * el metodo ActualizarProducto de el Servlet_Prod.
     * @param p
     * @return true si se actualizo correctamente.
     */
    public static boolean actualizarProducto(Producto p){
        
    	boolean resp = false;
        
        Conexion con = new Conexion();
		Connection cn = con.getConnection();
        
        try{
            CallableStatement cs = cn.prepareCall("CALL MODIFICAR_PRODUCTO (?,?,?,?,?,?)");
            cs.setInt(1, p.getCodigoP());
            cs.setString(2, p.getClaseProducto());
            cs.setString(3, p.getMarcaProducto());
            cs.setString(4, p.getDescripcion());
            cs.setDouble(5, p.getPrecioP());
            cs.setInt(6, p.getStock());
            cs.executeUpdate();
            
            int i = cs.executeUpdate();
            
            if(i==1)
                resp = true;
            else
                resp = false;
            
            cn.close();
            
        }catch(Exception e){
        	System.out.println(e);
        }
        
        return resp;
    }
    /**
     * Ejecuta el procedimiento almacenado ELIMINAR_PRODUCTO, y inhabilita el producto que recibe 
     * desde el metodo DarBajaProducto de el Servlet_Prod.
     * @param p
     * @return true si se inhabilito correctamente el producto.
     */
    public static boolean eliminarProducto(Producto p){
        
    	boolean resp = false;
        
        Conexion con = new Conexion();
		Connection cn = con.getConnection();
        
        try{
            CallableStatement cs = cn.prepareCall("CALL ELIMINAR_PRODUCTO (?)");
            cs.setInt(1, p.getCodigoP());

            int i = cs.executeUpdate();
            
            if(i==1)
                resp = true;
            else
                resp = false;
            
            cn.close();
            
        }catch(Exception e){
        	System.out.println(e);
        }
        
        return resp;
    }
    /**
     * Ejecuta el procedimiento almacenado RECUPERAR_PRODUCTO, habilita el producto que recibe desde 
     * el metodo DarAltaProducto del Servlet_Prod.
     * @param p
     * @return true si se habilito correctamente el producto.
     */
    public static boolean recuperarProducto(Producto p){
        
    	boolean resp = false;
        
        Conexion con = new Conexion();
		Connection cn = con.getConnection();
        
        try{
            CallableStatement cs = cn.prepareCall("CALL RECUPERAR_PRODUCTO (?)");
            cs.setInt(1, p.getCodigoP());
            
            int i = cs.executeUpdate();
            
            if(i==1)
                resp = true;
            else
                resp = false;
            
            cn.close();
            
        }catch(Exception e){
        	System.out.println(e);
        }
        
        return resp;
    }
    /**
     * Ejecuta el procedimiento almacenado BUSCAR_PRODUCTO_CODIGO, busca el producto por su codigo.
     * @param Codigo
     * @return el producto que se busca por codigo.
     */
    public static Producto listarProductoPorCodigo(String Codigo){
        
    	Producto p = new Producto();
    	
    	Conexion con = new Conexion();
		Connection cn = con.getConnection();
        
        try{
            CallableStatement cs = cn.prepareCall("CALL BUSCAR_PRODUCTO_CODIGO (?)");
            cs.setString(1, Codigo);
            ResultSet rs = cs.executeQuery();
            
            while(rs.next()){
            	
                p.setCodigoP(rs.getInt("Codigo_P"));
                p.setClaseProducto(rs.getString("Nombre_CP"));
                p.setMarcaProducto(rs.getString("Nombre_MP"));
                p.setDescripcion(rs.getString("Descripcion_P"));
                p.setPrecioP(rs.getDouble("Precio_P"));
                p.setStock(rs.getInt("Stock_P"));
                
            }
            
            cn.close();
            
        }catch(Exception e){
        	System.out.println(e);
        }
        
        return p;
    }
	
}
