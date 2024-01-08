import java.util.ArrayList;

public class Main {

    public static final String HOST = "localhost";

    public static void main(String[] args) {
        ArrayList<Thread> clients = new ArrayList<>();
        for(int i = 0; i < 5; i++) {
            clients.add(new HiloCliente(i));
        }
        for(Thread thread : clients) {
            thread.start();
        }
    }
}