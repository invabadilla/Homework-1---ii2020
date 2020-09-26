import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Esta clase contiene el contructor para agregar un nuevo chat en la applicacion
 * @author Ingrid Vargas
 *
 */

public class NewChat {

    public int puerto;


    /**
     * Clase para la validacion de la creacion del nuevo boton para el chat
     * @param puerto Para la asignacion del puerto al boton
     */
    public void NewChat(int puerto){

        if (!Main.bottons.containsKey(puerto)) {

            if (!Main.messageDB.containsKey(puerto)) {

                Main.bottons.put(puerto, "");

                this.puerto = puerto;

                JButton newContact = new JButton(String.valueOf(puerto));

                update myevent = new update();

                newContact.addActionListener(myevent);

                Main.leftPanel.add(newContact);

                Main.messageDB.put(puerto, "");

            }else {

                Main.bottons.put(puerto, "");

                this.puerto = puerto;

                JButton newContact = new JButton(String.valueOf(puerto));

                update myevent = new update();

                newContact.addActionListener(myevent);

                Main.leftPanel.add(newContact);

            }

        }else{
            System.out.println("Chat ya existente");
        }

        Main.leftPanel.updateUI();
        Main.rightPanel.updateUI();

    }


    /**
     * Clase para actualizar el area de chat cuando se selecciona chats distintos, accionado con el boton respectivo al chat
     *
     */
    class update implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            Main.reciverPort = puerto;

            Main.chatArea.setText("");
            Main.chatArea.append((String) Main.messageDB.get(Main.reciverPort));


        }
    }

}

