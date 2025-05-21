package Connection;

import java.io.IOException;
import java.net.*;
import java.util.HashMap;
import java.util.Scanner;

public class Server {
    int port;
    ServerSocket Server;



    public Server(){
        try{
            this.Server = new ServerSocket(0);
            this.port= Server.getLocalPort();
            System.out.println("Server started : " + this.port);
        } catch (IOException e) {
            System.out.println("error at Server constr");
            throw new RuntimeException(e);
        }
    }

    public void letConnect(){
        try{
            Socket client = this.Server.accept();
            String clientIp = client.getInetAddress().toString();
            int clientPort = client.getLocalPort();
            System.out.println("Client connected : " + clientIp + clientPort);
        } catch (IOException e) {
            System.out.println("error at Server letconnect");
            throw new RuntimeException(e);
        }
    }
}

