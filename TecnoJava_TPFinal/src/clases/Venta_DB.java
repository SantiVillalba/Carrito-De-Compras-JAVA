package clases;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import clases.Venta;
import conexion.Conexion;

public class Venta_DB {
	/**
	 * Ejecuta el procedimiento almacenado REGISTRAR_VENTA, registra la venta que se realiza
	 * por el usuario con los datos que recibe del metodo RegistrarVenta del Servlet_Venta.
	 * @param v
	 * @return true si la venta se registro correctamente.
	 */
	public static boolean insertarVenta(Venta v){
        
		boolean resp = false;
        
        Conexion con = new Conexion();
        Connection cn = con.getConnection();
        
        try{
            CallableStatement cs = cn.prepareCall("CALL REGISTRAR_VENTA (?,?)");
            cs.setString(1, v.getCliente());
            cs.setDouble(2, v.getTotal());
            int i = cs.executeUpdate();
            
            if(i == 1){
                resp = true;
            }else{
                resp = false;
            }
            
            cn.close();
            
        }catch(Exception e){
        	System.out.println(e);
        }
        
        return resp;
    }
    /**
     * Ejecuta el procedimiento almacenado MOSTRAR_VENTA, agrega a el ArrayList lista las ventas
     * que se realizaron
     * @return ArrayList con las ventas realizadas.
     */
    public static ArrayList<Venta> ObtenerVentas(){
        
    	ArrayList<Venta> lista = new ArrayList<Venta>();
        
        Conexion con = new Conexion();
        Connection cn = con.getConnection();
        
        try{
            CallableStatement cs = cn.prepareCall("CALL MOSTRAR_VENTA");
            ResultSet rs = cs.executeQuery();
            
            while(rs.next()){
            	
                Venta v = new Venta();
                v.setCodigoVenta(rs.getString("Codigo_V"));
                v.setCodigoCliente(rs.getString("Codigo_U")); 
                v.setCliente(rs.getString("CLIENTE"));
                v.setTotal(rs.getDouble("Total"));
                v.setFecha(rs.getString("Fecha")); 
                lista.add(v);
                
            } 
            
            cn.close();
            
        }catch(Exception e){
        	
            System.out.println(e);
        }
        
        return lista;
    }
    /**
     * Ejecuta el procedimiento almacenado ELIMINAR_VENTA, y borra el registro de la venta que se
     * realizo con el codigo de venta que recibe del metodo EliminarVenta del Servlet_Venta.
     * @param v
     * @return true si se borro correctamente el registro de la venta.
     */
    public static boolean eliminarVenta(Venta v){
        
    	boolean resp = false;
        
        Conexion con = new Conexion();
        Connection cn = con.getConnection();
        
        try{
            CallableStatement cs = cn.prepareCall("CALL ELIMINAR_VENTA (?)");
            cs.setString(1, v.getCodigoVenta());
            int i = cs.executeUpdate();
            
            if(i == 1){
                resp = true;
            }else{
                resp = false;
            }
            
            cn.close();
            
        }catch(Exception e){
        	System.out.println();
        }
        
        return resp;
    }
	
}
