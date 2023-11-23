package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;

/*
Создать окно клиента чата. Окно должно содержать JtextField для ввода логина, пароля,
IP-адреса сервера, порта подключения к серверу, область ввода сообщений, JTextArea
область просмотра сообщений чата и JButton подключения к серверу и отправки сообщения в чат.
Желательно сразу сгруппировать компоненты, относящиеся к серверу сгруппировать на JPanel
сверху экрана, а компоненты, относящиеся к отправке сообщения – на JPanel снизу
 */
public class Chat extends JFrame{
    private static final int WINDOW_HEIGHT = 400;
    private static final int WINDOW_WIDTH = 700;
    private static final int WINDOW_POSX = 800;
    private static final int WINDOW_POSY = 300;
    JButton btnSend = new JButton("Отправить.");
    JLabel lblLogin = new JLabel("Login:");
    JLabel lblPassword = new JLabel("Password:");
    JLabel lblIP = new JLabel("IP:");
    JLabel lblMessage = new JLabel("Сообщение:");
    JTextField txtFieldLogin = new JTextField();
    JTextField txtFieldPassword = new JTextField();
    JTextField txtFieldIP = new JTextField();
    JTextField txtFieldMessage = new JTextField();
    JTextArea areaMessage = new JTextArea();
    JScrollPane scrollPane = new JScrollPane(areaMessage);
    JPanel panServer = new JPanel(new GridLayout(6, 2));
    JPanel panClient = new JPanel(new GridLayout(4, 1));
    String login;
    String password;
    String IP;
    String message;

    Chat(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(WINDOW_POSX, WINDOW_POSY);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("ChatWindow");
        setResizable(false);

        panServer.add(lblLogin);
        panServer.add(txtFieldLogin);
        panServer.add(lblPassword);
        panServer.add(txtFieldPassword);
        panServer.add(lblIP);
        panServer.add(txtFieldIP);
        panClient.add(lblMessage);
        panClient.add(scrollPane);
        panClient.add(areaMessage);
        panClient.add(txtFieldMessage);
        panClient.add(btnSend);

        txtFieldMessage.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    sendMessage();
                }
            }
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {}
        });

        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        setLayout(new GridLayout(2,1));
        add(panServer);
        add(panClient);

        loadChatHistory(); // Загрузка истории чата

        setVisible(true);
    }

    private void sendMessage() {
        message = txtFieldLogin.getText() + ": " + txtFieldMessage.getText() + "\n";
        areaMessage.append(message);
        System.out.println("Отправлено сообщение: " + message);
        txtFieldMessage.setText("");

        try (FileWriter fileWriter = new FileWriter("chat_history.txt", true)) {
            fileWriter.write(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadChatHistory() {
        File file = new File("chat_history.txt");
        if (!file.exists()) { // Проверяем, существует ли файл
            try {
                file.createNewFile(); // Создаем новый файл, если он не существует
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (FileReader fileReader = new FileReader(file)) {
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                areaMessage.append(line + "\n");
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Chat();
    }
}
