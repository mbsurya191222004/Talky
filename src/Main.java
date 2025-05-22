import java.io.IOException;
import java.net.*;
import java.util.Scanner;

import Connection.Server;


public class Main {




    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        init();


        System.out.println("want to connect?(y/n)");
        String var = sc.nextLine();
        if(var.equals("y")){
            System.out.println("IP>>>");
            String ip = sc.nextLine();
            System.out.println("Port>>>");
            int port = sc.nextInt();
            connect(ip,port);
        }


    }

    public static void init(){
            Server server = new Server();
            //server.letConnect();



    }

    public static void connect(String ip,int port) {

        try(Socket socket= new Socket()){
            socket.connect(new InetSocketAddress(ip,port),1000);
            System.out.println("connected to : "+ socket.getRemoteSocketAddress() + socket.getPort());

        } catch (IOException e) {
            System.out.println("error in  connect");
            throw new RuntimeException(e);
        }
    }
}