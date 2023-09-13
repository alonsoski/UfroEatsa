
import java.util.Date;

public class Pedido {
    String nombreCliente;
    String comida;
    int precio;
    String hora;
    String contraseña;
    public Pedido(String nombreCliente, int precio, String comida){
        this.nombreCliente = nombreCliente;
        this.precio = precio;
        this.hora=calculoHora();
        this.comida=comida;
    }

    private String calculoHora() {
        Date fecha = new Date();
        String hora= fecha.toString().split(" ")[3];
        return hora;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public int getPrecio() {
        return precio;
    }
}

