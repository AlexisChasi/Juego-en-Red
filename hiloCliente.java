import java.io.*;
import java.net.Socket;
import java.util.*;

public class hiloCliente extends Thread {
    private final Socket clientSocket;
    private final Map<String, String> preguntasYRespuestas;

    public hiloCliente(Socket socket, Map<String, String> preguntasYRespuestas) {
        this.clientSocket = socket;
        this.preguntasYRespuestas = preguntasYRespuestas;
    }

    public void run() {
        try {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            int puntaje = 0;
            List<String> keysAsArray = new ArrayList<>(preguntasYRespuestas.keySet());
            Collections.shuffle(keysAsArray); // Mezclar las preguntas

            for (int i = 0; i < 5; i++) { // Seleccionar y enviar 5 preguntas aleatorias
                String pregunta = keysAsArray.get(i);
                out.println(pregunta); // Enviar pregunta
                String respuestaCliente = in.readLine(); // Leer respuesta

                if (respuestaCliente.equalsIgnoreCase(preguntasYRespuestas.get(pregunta))) {
                    puntaje += 10; // 10 puntos por respuesta correcta
                    out.println("Correcto! +10 puntos");
                } else {
                    out.println("Incorrecto! +0 puntos");
                }
            }

            out.println("Juego terminado. Tu puntaje es: " + puntaje);
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
