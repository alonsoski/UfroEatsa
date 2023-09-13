
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {

        menuLobby();
        //printMenu();


    }

    private static void menuLobby() {
        int eleccion=0;
        System.out.println("Bienvenido a UfroEats");
        System.out.println("que quieres hacer?");
        while (eleccion<1 || eleccion>3){
            System.out.println("1.-Iniciar sesion");
            System.out.println("2.-Crear usuario");
            System.out.println("3.-Salir");
            eleccion = eleccion(3);
        }
        switchLobby(eleccion);
    }

    private static void switchLobby(int eleccion) {
        switch (eleccion){
            case 1:
                iniciarSesion();
                break;
            case 2:
                
                break;
            case 3:
                System.out.println("Ok, adios");
                break;
        }
    }

    private static void iniciarSesion() {
        Scanner t= new Scanner(System.in);
        System.out.println("Escriba su correo electronico");
        String nombre=t.nextLine();
        System.out.println("Escriba su contraseña");
        String contrasena=t.nextLine();
        Usuario u = new Usuario(nombre,contrasena);
        if (u.usuarioExiste()){
            if (u.contraCorrecta(contrasena)){
                System.out.println("has ingresado");
                printMenu(nombre);
            }else{
                System.out.println("Correo o contraseña incorrectos");
            }
        }else {
            System.out.println("Correo o contraseña incorrectos");
            menuLobby();
        }

    }

    private static void printMenu(String correo) {

        System.out.println("que quieres hacer?");
        int eleccion = 0;
        while (eleccion<1 || eleccion>3){
            System.out.println("1.-Ver menu");
            System.out.println("2.-Cancelar orden");
            System.out.println("3.-pedir comida del dia");
            System.out.println("4.-pedir comida personalizada");
            System.out.println("5.-Salir");
            eleccion = eleccion(5);
        }
        switchMenuPrincipal(eleccion);
    }

    private static void switchMenuPrincipal(int eleccion) {
        switch (eleccion){
            case 1:
                menuComida();
                break;
            case 2:
                break;
            case 3:
                comidaDelDia();

                break;
            case 4:
                break;
            case 5:
                System.out.println("Ok, adios");
                break;
        }
    }

    private static void comidaDelDia() {
        Scanner t = new Scanner(System.in);
        System.out.println("indique su nombre porfavor");
        String nombreCliente= t.nextLine();
        int precio = 2500;
        String comida = obtenerComidaTxt().split("/")[1];

        String mensaje=(nombreCliente+"-"+precio+"-"+comida);
        enviarPorSocket(mensaje);

    }

    private static void menuComida() {
        String comida = obtenerComidaTxt();
        imprimirComidaDelDia(comida);
    }

    private static void imprimirComidaDelDia(String comida) {
        String[] comidaA=comida.split("/");
        System.out.println("comida del dia");
        for (int i = 0; i <comidaA.length ; i++) {
            System.out.println(comidaA[i]);
        }
    }

    private static void enviarPorSocket(String comidaA) {
        Scanner t = new Scanner(System.in);
        //String mensaje= t.nextLine();
        try  {
            Socket miSocketa = new Socket("192.168.1.7", 9991);

            DataOutputStream flujoSalida= new DataOutputStream(miSocketa.getOutputStream());
            flujoSalida.writeUTF(comidaA);
            flujoSalida.close();
            miSocketa.close();
        }catch (UnknownHostException e){
            e.printStackTrace();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    private static String obtenerComidaTxt() {
        String dia = obtenerdia();
        String comida="";
        try {
            File archivo = new File("./MenuSemanal/"+dia+".txt");
            FileReader fr = new FileReader(archivo);
            BufferedReader br = new BufferedReader(fr);
            String linea;
            while ((linea= br.readLine())!=null){
                comida+=linea;
            }
        }catch (Exception e){
            System.out.println("ha habido un error");
        }

        return comida;
    }

    private static String obtenerdia() {
        Date fecha = new Date();
        String[] fechaS = fecha.toString().split(" ");
        return fechaS[0];
    }

    private static int eleccion(int cantidadOpciones) {
        Scanner t = new Scanner(System.in);
        int eleccion=0;
        try {
            eleccion=t.nextInt();
        }catch (Exception e){
            t.nextLine();
        }
        if (eleccion<1 || eleccion>cantidadOpciones){
            System.out.println("eso no se puede");
        }
        return eleccion;
    }
    public static void avece(){
        System.out.println("aaa");
    }
}
