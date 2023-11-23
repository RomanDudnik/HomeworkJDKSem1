package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
    Создать простейшее окно управления сервером (по сути, любым), содержащее две кнопки
    (JButton) – запустить сервер и остановить сервер. Кнопки должны просто логировать нажатие
    (имитировать запуск и остановку сервера, соответственно) и выставлять внутри интерфейса
    соответствующее булево isServerWorking.

    1. Выполнить все задания семинара, если они не были решены, без ограничений по времени;
    2. Отправлять сообщения из текстового поля сообщения в лог по нажатию кнопки или по нажатию клавиши Enter
        на поле ввода сообщения;
    3. Продублировать импровизированный лог (историю) чата в файле;
    4. При запуске клиента чата заполнять поле истории из файла, если он существует.
       Обратите внимание, что чаще всего история сообщений хранится на сервере и заполнение истории чата
       лучше делать при соединении с сервером, а не при открытии окна клиента.

 */
public class ServerRun extends JFrame{
    private static final int WINDOW_HEIGHT = 555;
    private static final int WINDOW_WIDTH = 507;
    private static final int WINDOW_POSX = 800;
    private static final int WINDOW_POSY = 300;
    JButton btnStart = new JButton("Start Server");
    JButton btnStop = new JButton("Stop Server");
    boolean isServerWorking;
    ServerRun(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(WINDOW_POSX, WINDOW_POSY);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("TicTacToe");
        setResizable(false);

        btnStart.addActionListener(e -> {
            if (!isServerWorking) {
                isServerWorking = true;
            }
            System.out.println("Статус сервера: " + isServerWorking);
        });
        btnStop.addActionListener(e -> {
            if (isServerWorking) {
                isServerWorking = false;
            }
            System.out.println("Статус сервера: " + isServerWorking);
        });
        setLayout(new GridLayout(1,2));
        add(btnStart);
        add(btnStop);
        setVisible(true);
    }

    public static void main(String[] args) {
        new ServerRun();
    }
}
