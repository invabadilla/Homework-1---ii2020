import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Main {
    static int userPort = (int) Math.floor(Math.random() * (9000 - 6000 + 1) + 6000);
    static String name, puerto;
    static int reciverPort, auxPort;
    static String message;
    static JSONObject messageDB = new JSONObject();
    static JSONObject bottons = new JSONObject();
    static JPanel leftPanel = new JPanel();
    static JPanel rightPanel = new JPanel();
    static JTextArea chatArea;

    public static String getMessage() {
        return message;
    }

    public static void main(String[] args) {

        Runnable server = new ServidorChat();
        Thread serverThread = new Thread(server);
        serverThread.start();


        System.setProperty("light green", "#ABEBC6");


        JFrame screen = new JFrame();
        screen.setBounds(400, 200, 480, 506);
        screen.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        screen.setLayout(new BorderLayout());
        screen.setResizable(false);


        JPanel centralPanel = new JPanel();


        centralPanel.setLayout(new GridLayout(1, 2));

        leftPanel.setBackground(ColorUIResource.lightGray);
        rightPanel.setBackground(Color.getColor("light green"));

        JLabel newChat = new JLabel("Nuevo chat con: ");
        leftPanel.add(newChat);

        JTextField contacto = new JTextField(5);
        leftPanel.add(contacto);

        JButton chat = new JButton("Crear");
        leftPanel.add(chat);


        createChat myevent = new createChat();

        chat.addActionListener(myevent);



        JScrollPane contactsScroll = new JScrollPane(leftPanel);
        //contactsScroll.setViewportView(leftPanel);

        contactsScroll.setVisible(true);


        JLabel id = new JLabel("        Mi puerto: " + String.valueOf(userPort) + "            ");
        rightPanel.add(id);

        JLabel mypuerto = new JLabel("Destinatario: " + String.valueOf(reciverPort));
        rightPanel.add(mypuerto);


        chatArea = new JTextArea(20, 20);
        chatArea.setEditable(false);


        JScrollPane scroll = new JScrollPane();
        scroll.setViewportView(chatArea);

        rightPanel.add(scroll);


        JTextField messageArea = new JTextField(20);
        rightPanel.add(messageArea);

        JButton sendButton = new JButton("Enviar");
        sendText myEvent = new sendText();
        sendButton.addActionListener(myEvent);
        rightPanel.add(sendButton);



        centralPanel.add(leftPanel);
        centralPanel.add(rightPanel);

        screen.add(centralPanel);
        screen.setVisible(true);



        while (true) {

            try {

                Main.auxPort = Integer.parseInt(contacto.getText());
                Main.message = messageArea.getText();
                mypuerto.setText("Destinatario: " + String.valueOf(reciverPort));
                leftPanel.updateUI();
                rightPanel.updateUI();


            }catch (java.lang.NumberFormatException e){

            }


        }

    }
}

class sendText implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {


        JSONObject outputPackage = new JSONObject();
        String antes, despues;


        outputPackage.put("from", Main.userPort);
        outputPackage.put("to", Main.reciverPort);
        outputPackage.put("message", Main.message);


        try {
            Socket mysocket = new Socket("127.0.0.1", Main.reciverPort);

            ObjectOutputStream exitData = new ObjectOutputStream(mysocket.getOutputStream());

            exitData.writeObject(outputPackage);

            System.out.println(outputPackage);

            if (Main.userPort != Main.reciverPort) {

                if (Main.messageDB.containsKey(Main.reciverPort)) {

                    antes = (String) Main.messageDB.get(Main.reciverPort);

                    Main.messageDB.remove(Main.reciverPort);

                    despues = antes + "\nYo: " + Main.message;

                    Main.messageDB.put(Main.reciverPort, despues);


                } else {
                    despues = "Yo: " + Main.message;
                    Main.messageDB.put(Main.reciverPort, despues);

                }
            }

            mysocket.close();

        }catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
        System.out.println(Main.reciverPort);
        System.out.println(Main.messageDB.get(Main.reciverPort));

        Main.chatArea.setText("");
        Main.chatArea.append((String) Main.messageDB.get(Main.reciverPort));


    }
}

class createChat implements ActionListener{


    @Override
    public void actionPerformed(ActionEvent e) {

        Main.reciverPort = Main.auxPort;

        NewChat nuevo = new NewChat();
        nuevo.NewChat(Main.auxPort);

    }
}

