import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class servidorTCP {
    private static final Map<String, String> preguntasYRespuestas = new HashMap<>();

    static {
        preguntasYRespuestas.put("¿Cuál es la capital de Francia?", "París");
        preguntasYRespuestas.put("¿2 + 2?", "4");
        // Añadir más preguntas y respuestas aquí
    }

    public static void main(String[] args) {
        try {
            int puerto = 5000; // Número de puerto
            ServerSocket serverSocket = new ServerSocket(puerto);
            System.out.println("Servidor esperando conexiones");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                new hiloCliente(clientSocket, preguntasYRespuestas).start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
