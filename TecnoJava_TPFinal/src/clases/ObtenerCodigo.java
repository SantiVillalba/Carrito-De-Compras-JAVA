package clases;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import conexion.Conexion;

public class ObtenerCodigo {
	
	/**
	 * Ejecuta el procedimiento almacenado MOSTRAR_CODIGO_PRODUCTO y obtiene el codigo del siguiente
	 * producto que se va a registrar.
	 * @return Codigo del siguiente producto.
	 */
	public static String CodigoProducto(){
        
		String Codigo = "";
        
        Conexion con = new Conexion();
        Connection cn = con.getConnection();
        
        try{
            CallableStatement cs = cn.prepareCall("CALL MOSTRAR_CODIGO_PRODUCTO");
            ResultSet rs = cs.executeQuery();
            
            while(rs.next()){
            	Codigo = String.valueOf(rs.getInt("Codigo_P"));
            }
            
            cn.close();
            
        }catch(Exception e){
           System.out.println(e);
        }
        
        return Codigo;
    }
    /**
     * Ejecuta el procedimiento almacenado MOSTRAR_CODIGO_MARCA_PRODUCTO y obtiene el codigo de la
     * siguiente marca producto que se va a registrar.
     * @return Codigo de la siguiente marca producto.
     */
    public static String CodigoMarcaProducto(){
        
    	String Codigo = "";
        
        Conexion con = new Conexion();
        Connection cn = con.getConnection();
        
        try{
            CallableStatement cs = cn.prepareCall("CALL MOSTRAR_CODIGO_MARCA_PRODUCTO");
            ResultSet rs = cs.executeQuery();
            
            while(rs.next()){
                Codigo = rs.getString("Codigo_MP");
            }
            
            cn.close();
            
        }catch(Exception e){
           System.out.println(e);
        }
        
        return Codigo;
    }
    /**
     * Ejecuta el procedimiento almacenado MOSTRAR_CODIGO_CLASE_PRODUCTO y obtiene el codigo de la
     * siguiente clase producto que se va a registrar.
     * @return Codigo de la siguiente clase producto.
     */
    public static String CodigoClaseProducto(){
        
    	String Codigo = "";
        
        Conexion con = new Conexion();
        Connection cn = con.getConnection();
        
        try{
            CallableStatement cs = cn.prepareCall("CALL MOSTRAR_CODIGO_CLASE_PRODUCTO");
            ResultSet rs = cs.executeQuery();
            
            while(rs.next()){
                Codigo = rs.getString("Codigo_CP");
            }
            
            cn.close();
            
        }catch(Exception e){
           System.out.println(e);
        }
        
        return Codigo;
    }
    /**
     * Ejecuta el procedimiento almacenado MOSTRAR_CODIGO_VENTA y obtiene el codigo de la
     * siguiente venta que se va a registrar.
     * @return Codigo de la siguiente venta.
     */
    public static String CodigoVenta(){
        
    	String Codigo = "";
        
        Conexion con = new Conexion();
        Connection cn = con.getConnection();
        
        try{
            CallableStatement cs = cn.prepareCall("CALL MOSTRAR_CODIGO_VENTA");
            ResultSet rs = cs.executeQuery();
            
            while(rs.next()){
                Codigo = rs.getString("Codigo_V");
            }
            
            cn.close();
            
        }catch(Exception e){
           System.out.println(e);
        }
        
        return Codigo;
    }
	
}
