import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewChat {


    public int puerto;


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
                System.out.println("agregado");

            }

        }else{
            System.out.println("Chat ya existente");
        }

        Main.leftPanel.updateUI();
        Main.rightPanel.updateUI();

    }

    class update implements ActionListener{


        @Override
        public void actionPerformed(ActionEvent e) {


            Main.reciverPort = puerto;

            Main.chatArea.setText("");
            Main.chatArea.append((String) Main.messageDB.get(Main.reciverPort));
            System.out.println("actua;izadp");

        }
    }

}


