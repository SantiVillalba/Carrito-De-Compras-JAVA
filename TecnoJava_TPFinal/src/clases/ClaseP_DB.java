package clases;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import clases.ClaseP;
import conexion.Conexion;

public class ClaseP_DB {
	
	/**
	 * Ejecuta  el procedimiento almacenado MOSTRAR_CLASE_PRODUCTO_HABILITADOS para llenar el 
	 * arraylist lista con las clase producto que se encuentren habilitadas.
	 * @return Un ArrayList con las clase producto habilitadas.
	 */
	public static ArrayList<ClaseP> ObtenerCPHabilitados(){
        
		ArrayList<ClaseP> lista = new ArrayList<ClaseP>();
        
        Conexion con = new Conexion();
        Connection cn=con.getConnection();
        
        try{
            CallableStatement cs = cn.prepareCall("CALL MOSTRAR_CLASE_PRODUCTO_HABILITADOS");
            ResultSet rs = cs.executeQuery();
            
            while(rs.next()){
                ClaseP cp = new ClaseP(rs.getString("Codigo_CP"), rs.getString("Nombre_CP"), rs.getString("Estado_CP"));
                lista.add(cp);
            }
            
            cn.close();
            
        }catch(Exception e){ 
            System.out.println(e);
        }
        
        return lista;
    }
    /**
     * Ejecuta el procedimiento almacenado MOSTRAR_CLASE_PRODUCTO_INHABILITADOS y llena el 
     * ArrayList lista con las clase producto que se encuentran inhabilitados. 
     * @return Un ArrayList con las clase producto inhabilitadas.
     */
    public static ArrayList<ClaseP> obtenerCPInhabilitados(){
        
    	ArrayList<ClaseP> lista = new ArrayList<ClaseP>();
        
        Conexion con = new Conexion();
        Connection cn=con.getConnection();
        
        try{
            CallableStatement cs = cn.prepareCall("CALL MOSTRAR_CLASE_PRODUCTO_INHABILITADOS");
            ResultSet rs = cs.executeQuery();
            
            while(rs.next()){
                ClaseP CP = new ClaseP(rs.getString("Codigo_CP"), rs.getString("Nombre_CP"), rs.getString("Estado_CP"));
                lista.add(CP);
            }
            
            cn.close();
            
        }catch(Exception e){
            System.out.println(e);
        }
        
        return lista;
    } 
    /**
     * Ejecuta el procedimiento almacenado REGISTRAR_CLASE_PRODUCTO y registra la clase producto
     * que recibe desde el metodo RegistrarClaseProducto del Servlet_CP.
     * @param cp
     * @return true si el registro se realizo correctamente.
     */
    public static boolean insertarClaseProducto(ClaseP cp){
        
    	boolean resp = false;
        
        Conexion con = new Conexion();
        Connection cn=con.getConnection();
        
        try{
            CallableStatement cs = cn.prepareCall("CALL REGISTRAR_CLASE_PRODUCTO (?)");
            cs.setString(1, cp.getNombreCP());
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
     * Ejecuta el procedimiento almacenado MODIFICAR_CLASE_PRODUCTO y modifica la clase producto
     * que recibe desde el metodo ActualizarClaseProducto del Servlet_CP.
     * @param cp
     * @return true si la modificación se realizo correctamente.
     */
    public static boolean actualizarClaseProducto(ClaseP cp){
        
    	boolean resp = false;
        
        Conexion con = new Conexion();
        Connection cn=con.getConnection();
        
        try{
            CallableStatement cs = cn.prepareCall("CALL MODIFICAR_CLASE_PRODUCTO (?,?)");
            cs.setString(1, cp.getCodigoCP());
            cs.setString(2, cp.getNombreCP()); 
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
     * Ejecuta el procedimiento almacenado ELIMINAR_CLASE_PRODUCTO y inhabilita la clase producto
     * que recibe desde el metodo EliminarClaseProducto del Servlet_CP.
     * @param cp
     * @return true si se inhabilito correctamente.
     */
    public static boolean darBajaClaseProducto(ClaseP cp){
        
    	boolean resp = false;
        
        Conexion con = new Conexion();
        Connection cn=con.getConnection();
        
        try{
            CallableStatement cs = cn.prepareCall("CALL ELIMINAR_CLASE_PRODUCTO (?)");
            cs.setString(1, cp.getCodigoCP()); 
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
     * Ejecuta el procedimiento almacenado RECUPERAR_CLASE_PRODUCTO y y habilita la clase producto
     * que recibe desde el metodo RecuperarClaseProducto del Servlet_CP.
     * @param cp
     * @return true si se dio de alta correctamente.
     */
    public static boolean darAltaClaseProducto(ClaseP cp){
        
    	boolean resp = false;
        
        Conexion con = new Conexion();
        Connection cn=con.getConnection();
        
        try{
            CallableStatement cs = cn.prepareCall("CALL RECUPERAR_CLASE_PRODUCTO (?)");
            cs.setString(1, cp.getCodigoCP()); 
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
	
}
