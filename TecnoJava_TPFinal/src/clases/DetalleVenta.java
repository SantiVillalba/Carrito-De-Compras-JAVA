package clases;

import clases.Producto;
import clases.Venta;

public class DetalleVenta {
	
	private String numero;
    private String CodigoVenta;
    private int codigoProducto;
    private String NombreProducto;
    private double Precio;
    private int Cantidad;
    private double SubTotal;
    private Producto Producto;
    private Venta Venta;

    public DetalleVenta() {
    }

    public DetalleVenta(String numero, String CodigoVenta, int codigoProducto, String NombreProducto, double Precio, int Cantidad, double SubTotal, Producto Producto, Venta Venta) {
        this.numero = numero;
        this.CodigoVenta = CodigoVenta;
        this.codigoProducto = codigoProducto;
        this.NombreProducto = NombreProducto;
        this.Precio = Precio;
        this.Cantidad = Cantidad;
        this.SubTotal = SubTotal;
        this.Producto = Producto;
        this.Venta = Venta;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCodigoVenta() {
        return CodigoVenta;
    }

    public void setCodigoVenta(String CodigoVenta) {
        this.CodigoVenta = CodigoVenta;
    }

    public int getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(int codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getNombreProducto() {
        return NombreProducto;
    }

    public void setNombreProducto(String NombreProducto) {
        this.NombreProducto = NombreProducto;
    }

    public double getPrecio() {
        return Precio;
    }

    public void setPrecio(double Precio) {
        this.Precio = Precio;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int Cantidad) {
        this.Cantidad = Cantidad;
    }

    public double getSubTotal() {
        return SubTotal;
    }

    public void setSubTotal(double SubTotal) {
        this.SubTotal = SubTotal;
    }

    public Producto getProducto() {
        return Producto;
    }

    public void setProducto(Producto Producto) {
        this.Producto = Producto;
    }

    public Venta getVenta() {
        return Venta;
    }

    public void setVenta(Venta Venta) {
        this.Venta = Venta;
    }
	
}
