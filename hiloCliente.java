import java.io.*;
import java.net.Socket;
import java.util.Map;

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

            for (Map.Entry<String, String> entry : preguntasYRespuestas.entrySet()) {
                out.println(entry.getKey()); // Enviar pregunta
                String respuestaCliente = in.readLine(); // Leer respuesta

                if (respuestaCliente.equalsIgnoreCase(entry.getValue())) {
                    puntaje++;
                    out.println("Correcto!");
                } else {
                    out.println("Incorrecto! La respuesta correcta era: " + entry.getValue());
                }
            }

            out.println("Juego terminado. Tu puntaje es: " + puntaje);
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
