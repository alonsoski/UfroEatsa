

import javax.swing.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Servidor extends Thread{
    public static void main(String[] args) {
        Servidor a = new Servidor();
        a.start();
        ejecucionServidor();

    }

    private static void ejecucionServidor() {
        int eleccion= 0;
        while (eleccion<1 || eleccion>3){
            System.out.println("1.-Ver Pedidos");
            System.out.println("2.-Despachar Pedido");
            System.out.println("3.-Cancelar Pedido");
            eleccion = eleccion(2);
            switchMenuServidor(eleccion);
        }
    }

    private static void switchMenuServidor(int eleccion) {
        switch (eleccion){
            case 1:
                verPedidos();
                ejecucionServidor();
                break;
            case 2:
                break;
        }

    }

    private static void verPedidos() {
        String[] listaArchivos;
        String[] texto = obtenerTextoArchivo();
        try {
            File carpeta = new File("./Pedidos");
            listaArchivos = carpeta.list();
            for (int i = 0; i < listaArchivos.length; i++) {
                System.out.println((i+1)+":"+listaArchivos[i]);

            }
            System.out.println("--------------------------");
        }catch (Exception e){
            System.out.println("ha habido un error");
        }
    }

    private static String[] obtenerTextoArchivo() {
        String[] listaArchivos;
        String[] aa=new String[3];


            /*
            FileReader fr = new FileReader(archivo);
            BufferedReader br = new BufferedReader(fr);
            String linea;
            while ((linea= br.readLine())!=null){
                comida+=linea;
            }
        }catch (Exception e){
            System.out.println("ha habido un error");
        }*/
        return aa;
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

    @Override
    public void run() {
        while (true){
            Socket miSocket;
            ServerSocket sockSer;
            try  {
                sockSer = new ServerSocket(9991);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                miSocket = sockSer.accept();
                DataInputStream flujoEntrada= new DataInputStream(miSocket.getInputStream());
                String mensajeT= flujoEntrada.readUTF();
                flujoEntrada.close();
                miSocket.close();
                sockSer.close();
                agregarArchivoPedido(mensajeT);
                System.out.println("Nuevo pedido");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void agregarArchivoPedido(String mensajeT) {
        String[] listaArchivos;
        try {
            File archivoACrear= new File("./Pedidos/"+mensajeT+".txt");
            archivoACrear.createNewFile();
            File carpeta = new File("./Pedidos");
            listaArchivos = carpeta.list();
            /*for (int i = 0; i < listaArchivos.length; i++) {
                System.out.println((i+1)+":"+listaArchivos[i]);

            }*/
        }catch (Exception e){
            System.out.println("ha habido un error");
        }
    }
}
