import java.io.*;
import java.net.Socket;

public class clienteTCP {
    public static void main(String[] args) {

        try {
            int puerto = 5000; // Número de puerto
            Socket socket = new Socket("localhost", puerto);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

            String preguntaDelServidor;
            while ((preguntaDelServidor = in.readLine()) != null && !preguntaDelServidor.equals("Juego terminado. Tu puntaje es:")) {
                System.out.println("Pregunta: " + preguntaDelServidor);
                String respuestaUsuario = stdIn.readLine();
                out.println(respuestaUsuario); // Enviar respuesta al servidor
                System.out.println("Respuesta del servidor: " + in.readLine()); // Recibir feedback
            }

            System.out.println(preguntaDelServidor + in.readLine()); // Mostrar puntaje final
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
