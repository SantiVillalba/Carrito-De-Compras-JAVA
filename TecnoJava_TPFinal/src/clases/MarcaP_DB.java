package clases;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import clases.MarcaP;
import conexion.Conexion;

public class MarcaP_DB {
	
	/**
	 * Ejecuta el procedimiento almacenado MOSTRAR_MARCA_PRODUCTO_HABILITADOS, llena el ArrayList 
	 * lista con las marca producto habilitadas.
	 * @return ArrayList con las marca producto habilitadas.
	 */
	public static ArrayList<MarcaP> obtenerMPHabilitados(){
        
		ArrayList<MarcaP> lista = new ArrayList<MarcaP>();
        
        Conexion con = new Conexion();
        Connection cn = con.getConnection();
        
        try{
            CallableStatement cs = cn.prepareCall("CALL MOSTRAR_MARCA_PRODUCTO_HABILITADOS");
            ResultSet rs = cs.executeQuery();
            
            while(rs.next()){
                MarcaP MP = new MarcaP(rs.getString("Codigo_MP"), rs.getString("Nombre_MP"), rs.getString("Estado_MP"));
                lista.add(MP);
            }
            
            cn.close();
            
        }catch(Exception e){
            System.out.println(e);
        }
        
        return lista;
    }
    /**
     * Ejecuta el procedimiento almacenado MOSTRAR_MARCA_PRODUCTO_INHABILITADOS, llena el ArrayList 
	 * lista con las marca producto inhabilitadas.
     * @return ArrayList con las marca producto inhabilitadas.
     */
    public static ArrayList<MarcaP> obtenerMPInhabilitados(){
        
    	ArrayList<MarcaP> lista = new ArrayList<MarcaP>();
        
        Conexion con = new Conexion();
        Connection cn = con.getConnection();
        
        try{
            CallableStatement cs = cn.prepareCall("CALL MOSTRAR_MARCA_PRODUCTO_INHABILITADOS");
            ResultSet rs = cs.executeQuery();
            
            while(rs.next()){
                MarcaP MP = new MarcaP(rs.getString("Codigo_MP"), rs.getString("Nombre_MP"), rs.getString("Estado_MP"));
                lista.add(MP);
            }
            
            cn.close();
            
        }catch(Exception e){
            System.out.println(e);
        }
        
        return lista;
    }
    /**
     * Ejecuta el procedimiento almacenado REGISTRAR_MARCA_PRODUCTO y registra la marca producto
     * que recibe desde el metodo RegistrarMarcaProducto de el Servlet_MP.
     * @param mp
     * @return true si se registro correctamente.
     */
    public static boolean insertarMarcaProducto(MarcaP mp){
        
    	boolean resp = false;
        
        Conexion con = new Conexion();
        Connection cn = con.getConnection();
        
        try{
            CallableStatement cs = cn.prepareCall("CALL REGISTRAR_MARCA_PRODUCTO (?)");
            cs.setString(1, mp.getNombreMP());
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
     * Ejecuta el procedimiento almacenado MODIFICAR_MARCA_PRODUCTO y actualiza la marca producto que
     * recibe desde el metodo ActualizarMarcaProducto de el Servlet_MP.
     * @param mp
     * @return true si la actualización se realizo correctamente.
     */
    public static boolean actualizarMarcaProducto(MarcaP mp){
        
    	boolean resp = false;
        
        Conexion con = new Conexion();
        Connection cn = con.getConnection();
        
        try{
            CallableStatement cs = cn.prepareCall("CALL MODIFICAR_MARCA_PRODUCTO (?,?)");
            cs.setString(1, mp.getCodigoMP());
            cs.setString(2, mp.getNombreMP()); 
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
     * Ejecuta el procedimiento almacenado ELIMINAR_MARCA_PRODUCTO y inhabilita la marca producto
     * que recibe desde el metodo darBajaMarcaProducto de el Servlet_MP.
     * @param mp
     * @return true si se inhabilito correctamente.
     */
    public static boolean eliminarMarcaProducto(MarcaP mp){
        
    	boolean resp = false;
        
        Conexion con = new Conexion();
        Connection cn = con.getConnection();
        
        try{
            CallableStatement cs = cn.prepareCall("CALL ELIMINAR_MARCA_PRODUCTO (?)");
            cs.setString(1, mp.getCodigoMP());
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
     * Ejecuta el procedimiento almacenado RECUPERAR_MARCA_PRODUCTO y rehabilita la marca producto
     * que recibe desde el metodo darAltarMarcaProducto de el Servlet_MP.
     * @param mp
     * @return true si se habilito la marca producto correctamente.
     */
    public static boolean recuperarMarcaProducto(MarcaP mp){
        
    	boolean resp = false;
        
        Conexion con = new Conexion();
        Connection cn = con.getConnection();
        
        try{
            CallableStatement cs = cn.prepareCall("CALL RECUPERAR_MARCA_PRODUCTO (?)");
            cs.setString(1, mp.getCodigoMP());
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
