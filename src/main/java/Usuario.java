import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Usuario {
    String nombre;
    String correo;
    String contrasena;

    public Usuario (){

    }
    public Usuario (String correo, String contrasena){
        this.correo = correo;
        this.contrasena = contrasena;
    }
    public boolean usuarioExiste() {
        boolean existe=false;
        File carpeta= new File("./Usuarios");
        String[] usuarios = carpeta.list();
        existe=recorrerCarpeta(usuarios, correo);
        return existe;
    }public boolean contraCorrecta(String contrasena){
        boolean correcta =false;
        File usuario = new File("./Usuarios/"+this.getCorreo()+".txt");
        correcta = lecturaContra(usuario);
        return correcta;
    }

    private boolean lecturaContra(File usuario) {
        String contra="";
        try{
            FileReader fr = new FileReader (usuario);
            BufferedReader br = new BufferedReader(fr);
            String linea;
        while((linea=br.readLine())!=null)
            contra+=linea;
    }
      catch(Exception e){
        e.printStackTrace();
    }
        System.out.println(contra);
        return (contra.equals(this.getContrasena()));
    }

    public int getIndex(String[] usuarios) {
        int index=0;
        for (int i = 0; i < usuarios.length ; i++) {
            if (this.getCorreo().equals(usuarios[i])){
                index=i;
                break;
            }
        }
        return index;
    }

    private boolean recorrerCarpeta(String[] usuarios, String correo) {
        boolean existe =false;
        if(usuarios==null){
            System.out.println("no hay usuarios aun");
        }else{
            for (int i = 0; i <usuarios.length ; i++) {
                if (usuarios[i].equals(this.getCorreo()+".txt")) {
                    existe= true;
                    break;
                }
            }
        }

        return existe;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getCorreo() {
        return correo;
    }

    public String getNombre() {
        return nombre;
    }

}

