import java.io.IOException;
import java.net.*;
import java.util.Scanner;

import Connection.Server;


public class Main {




    public static void main(String[] args) {

        init();




    }

    public static void init(){
            Server server = new Server();

        Scanner sc = new Scanner(System.in);
        System.out.println("wanna connect?(y/n)");
        String temp = sc.nextLine();
        if(temp.equals("y")){
            System.out.println("IP>>>");
            String ip = sc.nextLine();
            System.out.println("Port>>>");
            int port = sc.nextInt();
            connect(ip,port);
        }
        System.out.println("wanna let others connect?(y/n)");
        String temp1 = sc.nextLine();
        if(temp1.equals("y")){
            server.letConnect();
        }



    }

    public static void connect(String ip,int port) {

        try(Socket socket= new Socket()){
            socket.connect(new InetSocketAddress(ip,port),1000);
            System.out.println("connected to : "+ socket.getRemoteSocketAddress());

        } catch (IOException e) {
            System.out.println("error in  connect");
            throw new RuntimeException(e);
        }
    }
}