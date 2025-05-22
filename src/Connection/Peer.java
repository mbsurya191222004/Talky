package Connection;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Peer {
    private static final int SERVER_PORT = 12345; // fixed port

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Start the server thread
        new Thread(() -> startServer()).start();

        // Ask for peer to connect to
        System.out.print("Enter peer IP (or 'localhost'): ");
        String peerIP = scanner.nextLine();

        System.out.print("Enter message to send: ");
        String message = scanner.nextLine();

        // Send message to the peer
        sendMessage(peerIP, SERVER_PORT, message);

        scanner.close();
    }

    // Server part to receive messages
    public static void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(SERVER_PORT)) {
            System.out.println("Server listening on port " + SERVER_PORT + "...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                String received = in.readLine();
                System.out.println("Received: " + received);

                in.close();
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Client part to send messages
    public static void sendMessage(String host, int port, String message) {
        try (Socket socket = new Socket(host, port)) {
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            out.write(message);
            out.newLine();
            out.flush();
            System.out.println("Message sent to " + host);
        } catch (IOException e) {
            System.out.println("Failed to connect to peer.");
        }
    }
}
