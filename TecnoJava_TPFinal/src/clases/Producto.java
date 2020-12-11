package clases;

public class Producto {
	
	private int CodigoP;
    private String ClaseProducto;
    private String MarcaProducto;
    private String Descripcion;
    private double PrecioP;
    private int Stock;
    private String EstadoP;

    public Producto() {
    }

    public Producto(int CodigoP, String ClaseProducto, String MarcaProducto, String Descripcion, double PrecioP, int Stock, String EstadoP) {
        this.CodigoP = CodigoP;
        this.ClaseProducto = ClaseProducto;
        this.MarcaProducto = MarcaProducto;
        this.Descripcion = Descripcion;
        this.PrecioP = PrecioP;
        this.Stock = Stock;
        this.EstadoP = EstadoP;
    }

    public int getCodigoP() {
        return CodigoP;
    }

    public void setCodigoP(int CodigoP) {
        this.CodigoP = CodigoP;
    }

    public String getClaseProducto() {
        return ClaseProducto;
    }

    public void setClaseProducto(String ClaseProducto) {
        this.ClaseProducto = ClaseProducto;
    }

    public String getMarcaProducto() {
        return MarcaProducto;
    }

    public void setMarcaProducto(String MarcaProducto) {
        this.MarcaProducto = MarcaProducto;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public double getPrecioP() {
        return PrecioP;
    }

    public void setPrecioP(double PrecioP) {
        this.PrecioP = PrecioP;
    }

    public int getStock() {
		return Stock;
	}

	public void setStock(int Stock) {
		this.Stock = Stock;
	}

	public String getEstadoP() {
        return EstadoP;
    }

    public void setEstadoP(String EstadoP) {
        this.EstadoP = EstadoP;
    }
	
}
