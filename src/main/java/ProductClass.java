public class ProductClass {

    // Atributos
    String nombre;
    double precio;
    private int ventas = 0;

    // Constructor
    public ProductClass (String nombre, double precio) {
        this.nombre=nombre;
        this.precio=precio;
    }

    //Sets & Gets
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getVentas() {
        return ventas;
    }

    // Función para incrementar el número de ventas
    public void addVentas (int cantidad){
        this.ventas = this.ventas + cantidad;
    }
}
