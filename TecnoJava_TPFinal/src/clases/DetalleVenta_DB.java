package clases;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import clases.DetalleVenta;
import conexion.Conexion;

public class DetalleVenta_DB {
	
	/**
	 * Ejecuta el procedimiento almacenado MOSTRAR_DETALLE_VENTA_POR_CODIGO, llena el ArrayList lista
	 * con el detalle de las venta que se realizaron.
	 * @param CodigoVenta
	 * @return ArrayLista con el detalle de las ventas.
	 */
	public static ArrayList<DetalleVenta> obtenerDetalleVenta(String CodigoVenta){
        
		ArrayList<DetalleVenta> lista = new ArrayList<DetalleVenta>();
        
        Conexion con = new Conexion();
        Connection cn = con.getConnection();
        
        try{
            CallableStatement cs = cn.prepareCall("CALL MOSTRAR_DETALLE_VENTA_POR_CODIGO (?)");
            cs.setString(1, CodigoVenta);
            ResultSet rs = cs.executeQuery();
            
            while(rs.next()){
                DetalleVenta dv = new DetalleVenta();
                dv.setCodigoVenta(rs.getString("Codigo_V"));
                dv.setNombreProducto(rs.getString("Producto"));
                dv.setPrecio(rs.getDouble("Precio"));
                dv.setCantidad(rs.getInt("Cantidad"));
                dv.setSubTotal(rs.getDouble("SubTotal")); 
                lista.add(dv);
            }
            
            cn.close();
            
        }catch(Exception e){
        	System.out.println(e);
        }
        
        return lista;
    }
	
}
