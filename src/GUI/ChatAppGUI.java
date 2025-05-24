package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatAppGUI extends JFrame {

    // Components for the initial screen
    private JPanel initialPanel;
    private JButton hostButton, joinButton;

    // Components for join inputs
    private JPanel joinPanel;
    private JTextField ipField, portField;
    private JButton joinSubmitButton;

    // Components for chat screen
    private JPanel chatPanel;
    private JTextArea chatArea;
    private JTextField messageField;
    private JButton sendButton;

    public ChatAppGUI() {
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
            String ip = getLocalIP(); // dummy function, implement your own
            int port = 12345;         // example port

            // Display IP and port (you could make a popup or label)
            JOptionPane.showMessageDialog(this, "Hosting on IP: " + ip + " Port: " + port);

            // Switch to chat screen
            switchToChatScreen();
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
                switchToChatScreen();

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
        setContentPane(chatPanel);
        revalidate();
        repaint();
    }

    // Placeholder function to get local IP (implement your own)
    private String getLocalIP() {
        return "127.0.0.1"; // example IP
    }

    // Custom function to call when user joins with IP and port
    private void onJoin(String ip, int port) {
        System.out.println("Joining chat at IP: " + ip + " Port: " + port);
        // Add your networking join logic here
    }

    // Custom function to call when user sends a message
    private void onSendMessage(String message) {
        System.out.println("Sending message: " + message);
        // Add your send message logic here

        // Also display the sent message in the chat area
        displayReceivedMessage("Me: " + message);
    }

    // Function to display received message on chat screen
    public void displayReceivedMessage(String message) {
        chatArea.append(message + "\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ChatAppGUI gui = new ChatAppGUI();
            gui.setVisible(true);
        });
    }
}

