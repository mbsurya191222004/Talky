package GUI;

import Connection.Server;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.Scanner;

public class ChatAppGUI extends JFrame {

    Server server;

    private JPanel initialPanel;
    private JButton hostButton, joinButton;

    private JPanel joinPanel;
    private JTextField ipField, portField;
    private JButton joinSubmitButton;

    private JPanel chatPanel;
    private JTextArea chatArea;
    private JTextField messageField;
    private JButton sendButton;

    public ChatAppGUI(Server server) {
        this.server=server;
        setTitle("Chat App");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        createInitialPanel();
        createJoinPanel();
        createChatPanel();

        // Start with the initial screen
        setContentPane(initialPanel);
    }
    private void createInitialPanel() {
        initialPanel = new JPanel();
        hostButton = new JButton("Host");
        joinButton = new JButton("Join");
        hostButton.addActionListener(e -> {
            // You can replace this with your custom function to get IP and port
            String ip = this.server.ip.getHostAddress(); // dummy function, implement your own
            int port = this.server.port;         // example port


            Thread hostPanel = new Thread(() -> {
                JOptionPane.showMessageDialog(this, "Waiting for connection.....\n -->IP: " + ip +"\n"+ " -->Port: " + port);
            });
            hostPanel.start();
            new Thread(() -> {
                boolean connected = this.server.letConnect();
                if(connected){
                    hostPanel.interrupt();
                    switchToChatScreen();
                }
            }).start();

            // Switch to chat screen

        });
        joinButton.addActionListener(e -> {
            // Show join inputs panel instead of initial panel
            setContentPane(joinPanel);
            revalidate();
            repaint();
        });
        initialPanel.add(hostButton);
        initialPanel.add(joinButton);
    }
    private void createJoinPanel() {
        joinPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        ipField = new JTextField();
        portField = new JTextField();
        joinSubmitButton = new JButton("Submit");
        joinPanel.add(new JLabel("IP:"));
        joinPanel.add(ipField);
        joinPanel.add(new JLabel("Port:"));
        joinPanel.add(portField);
        joinPanel.add(new JLabel()); // empty cell
        joinPanel.add(joinSubmitButton);
        joinSubmitButton.addActionListener(e -> {
            String ip = ipField.getText().trim();
            String portStr = portField.getText().trim();
            if(ip.isEmpty() || portStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter both IP and Port");
                return;
            }
            try {
                int port = Integer.parseInt(portStr);
                // Call your custom function for join with IP and port here
                onJoin(ip, port);
                // Switch to chat screen
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Port must be a number");
            }
        });
    }
    private void createChatPanel() {
        chatPanel = new JPanel(new BorderLayout());
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatArea);

        JPanel inputPanel = new JPanel(new BorderLayout());
        messageField = new JTextField();
        sendButton = new JButton("Send");

        inputPanel.add(messageField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);

        chatPanel.add(scrollPane, BorderLayout.CENTER);
        chatPanel.add(inputPanel, BorderLayout.SOUTH);




        sendButton.addActionListener(e -> {
            String message = messageField.getText().trim();
            if (!message.isEmpty()) {
                // Call your custom function to send message here
                onSendMessage(message);
                messageField.setText("");
            }
        });
    }
    private void switchToChatScreen() {
        readOnThread(this.server);
        setContentPane(chatPanel);
        revalidate();
        repaint();
    }
    private void onJoin(String ip, int port) {
        System.out.println("Joining chat at IP: " + ip + " Port: " + port);
        boolean connected=this.server.connect(ip,port);
        if(connected){
            switchToChatScreen();
        }else{
            JOptionPane.showMessageDialog(this, "CaN't connect");
        }
        // Add your networking join logic here
    }
    private void onSendMessage(String message) {
        System.out.println("Sending message: " + message);
        // Add your send message logic here
        this.server.write(message);
        // Also display the sent message in the chat area
        displayReceivedMessage("Me: " + message);
    }
    public void displayReceivedMessage(String message) {
        chatArea.append(message + "\n");
    }
    public void readOnThread(Server server){
        new Thread(() -> {
            while (true) {
                String mesg = server.read();
                displayReceivedMessage("PEER : " + mesg);
            }
        }).start();
    }
    public static void main(String[] args) {
        //SwingUtilities.invokeLater(() -> {
        //    ChatAppGUI gui = new ChatAppGUI();
        //    gui.setVisible(true);
        //});
    }

    //useless method from now on
    public static void writeOnThread(Server server, String mesg){
        new Thread(() -> {
            while (true) {
                String message=mesg;
                server.write(message+"\n");
                System.out.println("sent: "+mesg);
            }
        }).start();
    }
}

