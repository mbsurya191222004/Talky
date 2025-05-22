package Connection;

import java.io.*;
import java.net.*;

import java.util.Scanner;

public class Server {
    int port;
    ServerSocket Server;
    BufferedReader in;
    BufferedWriter out;

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

    public boolean letConnect(){
        try{
            System.out.println("listening for clients...");
            Socket client = this.Server.accept();

            String clientIp = client.getInetAddress().toString();
            int clientPort = client.getPort();
            System.out.println("Client connected : " + clientIp +" : "+ clientPort);

            this.in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            this.out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));

            return true;
        } catch (IOException e) {
            System.out.println("error at Server letconnect");
            return false;
        }
    }

    public boolean connect(String ip,int port) {

        try(Socket socket= new Socket()){
            socket.connect(new InetSocketAddress(ip,port),1000);
            System.out.println("connected to : "+ socket.getRemoteSocketAddress() + " : " + socket.getLocalPort());
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            return true;

        } catch (IOException e) {
            System.out.println("error in  connect");
            return false;
        }
    }

    public void write(String message){
        try{
            this.out.write(message);
        } catch (IOException e) {
            System.out.println("error in write");
            throw new RuntimeException(e);
        }
    }

    public String read(){
        try{
            String mesg= this.in.readLine();
            System.out.println(mesg);
            return mesg;
        } catch (IOException e) {
            System.out.println("error in read");
            throw new RuntimeException(e);
        }
    }
}

