import java.io.IOException;
import java.net.*;
import java.util.Scanner;

import Connection.Server;


public class Main {

    Socket client= new Socket();
    Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        init();
    }

    public static void init(){
            Server server = new Server();
            server.letConnect();

            Scanner sc = new Scanner(System.in);
    }

    public static void connect(String ip,int port,Socket socket) {
        try{
            socket.connect(new InetSocketAddress(ip,port));
        } catch (IOException e) {
            System.out.println("error in  connect");
            throw new RuntimeException(e);
        }
    }
}