import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class HiloCliente extends Thread{
    
    protected Socket socket;
    protected DataOutputStream dataOutputStream;
    protected DataInputStream dataInputStream;
    
    private final int id;
    private final String host;

    public HiloCliente(int id, String host) {
        this.id = id;
        this.host = host;
    }
    
    @Override
    public void run() {
        try {
            socket = new Socket(host, 8080);
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataInputStream = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            System.out.println("ERROR: Ha ocurrido un error creando el socket");
            throw new RuntimeException(e);
        }
        System.out.println(id  + ": Envia saludo");
        String respuesta = "";
        try {
            dataOutputStream.writeUTF("Saludos desde el cliente!");
        } catch (IOException e) {
            System.out.println("ERROR: Ha ocurrido un error al enviar el mensaje");
            throw new RuntimeException(e);
        }
        try {
            respuesta = dataInputStream.readUTF();
        } catch (IOException e) {
            System.out.println("ERROR: Ha ocurrido un error leyendo la respuesta");
            throw new RuntimeException(e);
        }
        if(!respuesta.isEmpty()) {
            System.out.println(id + ": Servidor -> " + respuesta);
        }
        try {
            dataInputStream.close();
            dataOutputStream.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("ERROR: Ha ocurrido un error cerrando el socket");
            throw new RuntimeException(e);
        }
    }
}
