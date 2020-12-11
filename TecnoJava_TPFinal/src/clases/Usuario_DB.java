package clases;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import clases.Usuario;
import conexion.Conexion;

public class Usuario_DB {
	
	/**
	 * Ejecuta el procedimiento almacenado LOGEAR_USUARIO recibe el usuario y contraseña desde el 
	 * metodo Login del Servlet_Usu y verifica que el usuario y la contraseña sean los mismo
	 * que los de la base de datos.
	 * @param id_usuario
	 * @return El usuario y sus datos.
	 */
	public static Usuario VerificarUsuario(String id_usuario){
        
		Usuario usu = new Usuario();
        
        Conexion con = new Conexion();
        Connection cn = con.getConnection();
        
        try{
            CallableStatement cs = cn.prepareCall("CALL LOGEAR_USUARIO (?)");
            cs.setString(1, id_usuario);
            ResultSet rs = cs.executeQuery();
            
            while(rs.next()){
            	
                usu.setCodigoUsuario(rs.getString("Codigo_U")); 
                usu.setIdUsuario(rs.getString("Id_U"));
                usu.setClaveUsuario(rs.getString("Clave_U"));
                usu.setEstadoUsuario(rs.getString("Estado_U"));
                
            }
            
            cn.close();
            
        }catch(Exception e){
        	System.out.println(e);
        }
        
        return usu;
    }
    /**
     * Ejecuta el procedimiento almacenado MOSTRAR_USUARIOS_CLIENTES_HABILITADOS, llena el ArrayList
     * lista con los usuarios que estan habilitados.
     * @return ArrayList con usuarios habilitados.
     */
    public static ArrayList<Usuario> MostrarUsuarioHabilitado(){
        
    	ArrayList<Usuario> lista = new ArrayList<Usuario>();
        
        Conexion con = new Conexion();
        Connection cn = con.getConnection();
        
        try{
            CallableStatement cs = cn.prepareCall("CALL MOSTRAR_USUARIOS_CLIENTES_HABILITADOS");
            ResultSet rs = cs.executeQuery();
            
            while(rs.next()){
                Usuario usu = new Usuario();
                usu.setCodigoUsuario(rs.getString("Codigo_U"));
                usu.setNombreUsuario(rs.getString("Nombres_U"));
                usu.setApellidosUsuario(rs.getString("Apellidos_U"));
                usu.setEmailUsuario(rs.getString("Email_U")); 
                usu.setIdUsuario(rs.getString("Id_U"));
                usu.setTipoUsuario(rs.getString("Tipo_U"));
                usu.setEstadoUsuario(rs.getString("Estado_U")); 
                lista.add(usu);
            }
            
            cn.close();
            
        }catch(Exception e){
        	System.out.println(e);
        }
        
        return lista;
    }
    /**
     * Ejecuta el procedimiento almacenado MOSTRAR_USUARIOS_CLIENTES_INHABILITADOS, llena el 
     * ArrayList lista con los usuarios que estan inhabilitados.
     * @return ArrayList con usuarios inhabilitados.
     */
    public static ArrayList<Usuario> MostrarUsuarioInhabilitado(){
       
    	ArrayList<Usuario> lista = new ArrayList<Usuario>();
        
        Conexion con = new Conexion();
        Connection cn = con.getConnection();
        
        try{
            CallableStatement cs = cn.prepareCall("CALL MOSTRAR_USUARIOS_CLIENTES_INHABILITADOS");
            ResultSet rs = cs.executeQuery();
            
            while(rs.next()){
                Usuario usu = new Usuario();
                usu.setCodigoUsuario(rs.getString("Codigo_U"));
                usu.setNombreUsuario(rs.getString("Nombres_U"));
                usu.setApellidosUsuario(rs.getString("Apellidos_U"));
                usu.setEmailUsuario(rs.getString("Email_U")); 
                usu.setIdUsuario(rs.getString("Id_U"));
                usu.setTipoUsuario(rs.getString("Tipo_U"));
                usu.setEstadoUsuario(rs.getString("Estado_U")); 
                lista.add(usu);
            }
            
            cn.close();
            
        }catch(Exception e){
        	System.out.println(e);
        }
        
        return lista;
    }
    
    /**
     * Ejecuta el procedimiento almacenado REGISTRAR_USUARIO_CLIENTE, registra el cliente con los 
     * datos que recibe desde el metodo RegistrarUsuario del Servlet_Usu.
     * @param usu
     * @return true si se registro el usuario correctamente.
     */
    public static boolean RegistrarUsuario(Usuario usu){
        
    	boolean resp = false;
        
        Conexion con = new Conexion();
        Connection cn = con.getConnection();
        
        try{
            CallableStatement cs = cn.prepareCall("CALL REGISTRAR_USUARIO_CLIENTE (?,?,?,?,?)");
            cs.setString(1, usu.getNombreUsuario());
            cs.setString(2, usu.getApellidosUsuario());
            cs.setString(3, usu.getEmailUsuario());
            cs.setString(4, usu.getIdUsuario());
            cs.setString(5, usu.getClaveUsuario());
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
     * Ejecuta el procedimiento almacenado MODIFICAR_USUARIO, modifica los datos con los que recibe
     * desde el metodo ModificarUsuario del Servlet_Usu.
     * @param usu
     * @return true si se modifico el usuario correctamente.
     */
    public static boolean ModificarUsuario(Usuario usu){
        
    	boolean resp = false;
        
        Conexion con = new Conexion();
        Connection cn = con.getConnection();
        
        try{
            CallableStatement cs = cn.prepareCall("CALL MODIFICAR_USUARIO (?,?,?)");
            cs.setString(1, usu.getCodigoUsuario());
            cs.setString(2, usu.getNombreUsuario());
            cs.setString(3, usu.getApellidosUsuario());
            
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
     * Ejecuta el procedimiento almacenado MODIFICAR_CLAVE_USUARIO, modifica la clave del usuario
     * con la nueva que recibe desde el metodo ModificarClaveUsuario del Servlet_Usu.
     * @param usu
     * @return true si se modifico la clave correctamenta.
     */
    public static boolean ModificarClaveUsuario(Usuario usu){
        
    	boolean resp = false;
        
        Conexion con = new Conexion();
        Connection cn = con.getConnection();
        
        try{
            CallableStatement cs = cn.prepareCall("CALL MODIFICAR_CLAVE_USUARIO (?,?)");
            cs.setString(1, usu.getCodigoUsuario());
            cs.setString(2, usu.getClaveUsuario()); 
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
     * Ejecuta el procedimiento almacenado MODIFICAR_EMAIL_USUARIO, modifica el email con el nuevo 
     * que recibe desde el metodo ModificarEmailUsuario del Servlet_Usu.
     * @param usu
     * @return true si se modifico el email correctamente.
     */
    public static boolean ModificarEmailUsuario(Usuario usu){
        
    	boolean resp = false;
        
        Conexion con = new Conexion();
        Connection cn = con.getConnection();
        
        try{
            CallableStatement cs = cn.prepareCall("CALL MODIFICAR_EMAIL_USUARIO (?,?)");
            cs.setString(1, usu.getCodigoUsuario());
            cs.setString(2, usu.getEmailUsuario()); 
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
     * Ejecuta el procedimiento almacenado ELIMINAR_USUARIO, inhabilita el usuario del codigo que 
     * recibe metodo DarBajaUsuario del Servlet_Usu.
     * @param usu
     * @return true si el usuario se inhabilito correctamente.
     */
    public static boolean DarDeBajaUsuario(Usuario usu){
        
    	boolean resp = false;
        
        Conexion con = new Conexion();
        Connection cn = con.getConnection();
        
        try{
            CallableStatement cs = cn.prepareCall("CALL ELIMINAR_USUARIO (?)");
            cs.setString(1, usu.getCodigoUsuario());
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
     * Ejecuta el procedimiento almacenado RECUPERAR_USUARIO, habilita el usuario del codigo que 
     * recibe metodo DarAltaUsuario del Servlet_Usu.
     * @param usu
     * @return true si el usuario se habilito correctamente.
     */
    public static boolean DarDeAltaUsuario(Usuario usu){
        
    	boolean resp = false;
        
        Conexion con = new Conexion();
        Connection cn = con.getConnection();
        
        try{
            CallableStatement cs = cn.prepareCall("CALL RECUPERAR_USUARIO (?)");
            cs.setString(1, usu.getCodigoUsuario());
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
     * Ejecuta el procedimiento almacenado MOSTRAR_USUARIO_POR_CODIGO, muestra el usuario que busca
     * por el codigo.
     * @param codigo
     * @return el usuario que se busca por el id.
     */
    public static Usuario listarUsuarioPorCodigo(String codigo){
        
    	Usuario usu = new Usuario();
        
        Conexion con = new Conexion();
        Connection cn = con.getConnection();
        
        try{
            CallableStatement cs = cn.prepareCall("CALL MOSTRAR_USUARIO_POR_CODIGO (?)");
            cs.setString(1, codigo);
            ResultSet rs = cs.executeQuery();
            
            while(rs.next()){
            	
                usu.setCodigoUsuario(rs.getString("Codigo_U"));
                usu.setNombreUsuario(rs.getString("Nombres_U"));
                usu.setApellidosUsuario(rs.getString("Apellidos_U"));
                usu.setEmailUsuario(rs.getString("Email_U")); 
                usu.setIdUsuario(rs.getString("Id_U"));
                usu.setClaveUsuario(rs.getString("Clave_U"));
                usu.setTipoUsuario(rs.getString("Tipo_U")); 
                usu.setEstadoUsuario(rs.getString("Estado_U")); 
                
            }
            
            cn.close();
            
        }catch(Exception e){
        	System.out.println(e);
        }
        
        return usu;
    }
	
}
