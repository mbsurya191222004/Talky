package Connection;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class client {
    Socket socket;



    public client(String ip,int port){
        this.socket= new Socket();
        try{
            this.socket.connect(new InetSocketAddress(ip,port),1000);
        } catch (IOException e) {
            System.out.println("error at client constr");
            throw new RuntimeException(e);
        }
    }

}
