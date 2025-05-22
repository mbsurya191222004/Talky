import java.io.IOException;
import java.net.*;
import java.util.Scanner;

import Connection.Server;
import static Connection.Server.connect;


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
        System.out.println("allow others to connect?(y/n)");
        String temp2 = sc.nextLine();
        if(temp2.equals("y")){
            server.letConnect();
        }


    }


}