import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import org.json.simple.JSONObject;

/**
 * Clase que implementa el servidor para la recepcion de mensajes
 * @author Ingrid Vargas
 *
 */

public class ServidorChat implements Runnable {

    /**
     * Creacion del socket y lectura de los paquetes entrantes
     * @param puerto Puerto con el que se crea el socket
     *
     */

    public void start(int puerto) {

        try {

            while (true) {

                ServerSocket servidor = new ServerSocket(puerto);

                Socket socket = servidor.accept();


                ObjectInputStream inData = new ObjectInputStream(socket.getInputStream());
                JSONObject data;

                data = (JSONObject) inData.readObject();

                Object receiver;
                Object sender;
                String message;

                sender = data.get("from");
                receiver = data.get("to");
                message = (String) data.get("message");

                int num1;
                num1 = (int) sender;

                int num2;
                num2 = (int) receiver;


                String antes, despues;

                if (num1 != num2){

                    if (Main.messageDB.containsKey(sender)) {

                        antes = (String) Main.messageDB.get(sender);

                        Main.messageDB.remove(sender);

                        despues = antes + "\n" + sender + ": " + message;

                        Main.messageDB.put(sender, despues);
                    } else {

                        despues = sender + ": " + message;
                        Main.messageDB.put(sender,  despues);

                        Main.auxPort = num1;

                        NewChat nuevo = new NewChat();
                        nuevo.NewChat(num1);

                    }

                }else{
                    continue;
                }

                Main.reciverPort = num1;
                Main.chatArea.setText("");
                Main.chatArea.append((String) Main.messageDB.get(sender));


                inData.close();
                socket.close();
                servidor.close();
            }

        } catch (IOException | ClassNotFoundException ioException) {
            System.out.println("Error al iniciar la conexion");
        }
    }

    @Override
    public void run() {
        try {
            Main.userPort = (int) Math.floor(Math.random() * (9000 - 6000 + 1) + 6000);
            this.start(Main.userPort);
        } catch (Exception e) {
            Main.userPort = (int) Math.floor(Math.random() * (9000 - 6000 + 1) + 6000);
        }//Asignacion del puerto

    }
}