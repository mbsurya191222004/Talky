package Connection;

import java.io.*;
import java.net.*;

import java.util.Scanner;

public class Server {
    public int port;
    public InetAddress ip;
    public ServerSocket Server;
    Socket socket;
    BufferedReader in;
    BufferedWriter out;

    public Server(){
        try{
            this.Server = new ServerSocket(0);
            this.ip = this.Server.getInetAddress();
            if(ip.isAnyLocalAddress()){
                this.ip = InetAddress.getLocalHost();
            }
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

            this.out.write("hello from server\n");
            this.out.flush();

            String msg = this.in.readLine();
            System.out.println(msg);


            return true;
        } catch (IOException e) {
            System.out.println("error at Server letconnect");
            return false;
        }
    }
    public boolean connect(String ip,int port) {

        try{
            this.socket= new Socket();
            this.socket.connect(new InetSocketAddress(ip,port),1000);
            System.out.println("connected to : "+ this.socket.getRemoteSocketAddress() + " : " + this.socket.getLocalPort());
            this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.out = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));


            this.out.write("hello from client\n");
            this.out.flush();

            String msg = this.in.readLine();
            System.out.println(msg);


            return true;

        } catch (IOException e) {
            System.out.println("error in  connect");
            return false;
        }
    }
    public void write(String message){
        try{
            this.out.write(message);
            this.out.newLine();
            this.out.flush();
        } catch (IOException e) {
            System.out.println("error in write");
            throw new RuntimeException(e);
        }
    }
    public String read() throws IOException{

            String mesg= this.in.readLine();
            return mesg;

    }
}

