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
            server.connect(ip,port);
        }else {
            System.out.println("wanna let others connect?(y/n)");
            String temp1 = sc.nextLine();
            if (temp1.equals("y")) {
                server.letConnect();
            }
        }

//test me hu

    }


}