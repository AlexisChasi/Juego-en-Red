import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class servidorTCP {
    private static final Map<String, String> preguntasYRespuestas = new HashMap<>();

    static {
        // Agrega aquí tus 10 preguntas con sus respuestas
        preguntasYRespuestas.put("¿Cuál es la capital de Ecuador?", "Quito");
        preguntasYRespuestas.put("¿(4 * 5)/2?", "10");
        preguntasYRespuestas.put("¿ De que color eran las mangas del chaleco de Simon Bolivar?", "wl chaleco no tiene mangas");
        preguntasYRespuestas.put("Es completamente tuyo, sin embargo, todos lo usan... ¿qué es? ", "mi nombre");
        preguntasYRespuestas.put("Quien lo fabrica no lo necesita, a quien lo compra no le sirve y quien lo usa no puede ni verlo ni sentirlo, ¿qué es? ", "ataud");

        preguntasYRespuestas.put("¿Qué puedes sostener en tu mano derecha, pero nunca en tu mano izquierda? ", "la mano izquierda");
        preguntasYRespuestas.put("¿Cuál es el principal motivo por el cual la gente se divorcia? ", " matrimonio");
        preguntasYRespuestas.put("¿Qué entra duro pero sale blando y suave? ", "chicle");
        preguntasYRespuestas.put("¿De qué color es el caballo blanco de simon bolivar? ", "blanco");
        preguntasYRespuestas.put("¿Cuántos pares de cada animal subió Moisés a su arca?  ", "ninguno");


        // ... Agrega las preguntas restantes
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
