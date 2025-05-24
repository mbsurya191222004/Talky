import java.io.IOException;
import java.net.*;
import java.util.Scanner;
import GUI.ChatAppGUI;
import Connection.Server;

import javax.swing.*;


public class Main {




    public static void main(String[] args) {
        init();

    }

    public static void init(){
            Server server = new Server();
            SwingUtilities.invokeLater(() -> {
                ChatAppGUI gui = new ChatAppGUI(server);
                gui.setVisible(true);
            });
    }

    public static void forConsoleInterface(Server server){
        Scanner sc = new Scanner(System.in);
        System.out.println("wanna connect?(y/n)");
        String temp = sc.nextLine();
        if(temp.equals("y")){
            System.out.println("IP>>>");
            String ip = sc.nextLine();
            System.out.println("Port>>>");
            int port = sc.nextInt();
            if(server.connect(ip,port)){
                System.out.println("NOW YOU CAN TEXT....");
                CONreadOnThread(server);
                CONwriteOnThread(sc,server);


            }
        }else {
            System.out.println("wanna let others connect?(y/n)");
            String temp1 = sc.nextLine();
            if (temp1.equals("y")) {
                if(server.letConnect()){
                    System.out.println("NOW YOU CAN TEXT....");
                    CONreadOnThread(server);
                    CONwriteOnThread(sc,server);
                }

            }
        }
    }
    public static void CONwriteOnThread(Scanner sc ,Server server){
        new Thread(() -> {
            while (true) {
                String mesg = sc.nextLine();
                System.out.println("ME : "+ mesg);
                server.write(mesg);

            }
        }).start();
    }
    public static void CONreadOnThread(Server server){
        new Thread(() -> {
            while (true) {
                String mesg = server.read();
                System.out.println("PEER : "+ mesg);
            }
        }).start();
    }

}