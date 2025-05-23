package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class ConnectionSetupGUI extends JFrame {

    private CardLayout cardLayout;
    private JPanel mainPanel;
    private JPanel startPanel, joinPanel, hostPanel;

    public ConnectionSetupGUI() {
        setTitle("Host or Join");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        initStartPanel();
        initJoinPanel();
        initHostPanel();

        add(mainPanel);
        cardLayout.show(mainPanel, "start");
    }

    private void initStartPanel() {
        startPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        JButton hostBtn = new JButton("Host");
        JButton joinBtn = new JButton("Join");

        hostBtn.addActionListener(e -> {
            int code = generateCode();
            onHost(code); // Custom host function
            showHostScreen(code);
        });

        joinBtn.addActionListener(e -> cardLayout.show(mainPanel, "join"));

        startPanel.add(new JLabel("Choose Connection Type", SwingConstants.CENTER));
        startPanel.add(hostBtn);
        startPanel.add(joinBtn);

        mainPanel.add(startPanel, "start");
    }

    private void initJoinPanel() {
        joinPanel = new JPanel(new BorderLayout(10, 10));
        JPanel inputPanel = new JPanel(new FlowLayout());

        JTextField codeField = new JTextField(10);
        JButton submitBtn = new JButton("Submit");

        submitBtn.addActionListener(e -> {
            String code = codeField.getText().trim();
            if (!code.isEmpty()) {
                onJoin(code); // Custom join function
            }
        });

        inputPanel.add(new JLabel("Enter Code:"));
        inputPanel.add(codeField);
        inputPanel.add(submitBtn);

        joinPanel.add(inputPanel, BorderLayout.CENTER);
        mainPanel.add(joinPanel, "join");
    }

    private void initHostPanel() {
        hostPanel = new JPanel();
        hostPanel.setLayout(new BoxLayout(hostPanel, BoxLayout.Y_AXIS));
        hostPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(hostPanel, "host");
    }

    private void showHostScreen(int code) {
        hostPanel.removeAll();
        JLabel codeLabel = new JLabel("Your Code: " + code, SwingConstants.CENTER);
        JLabel waitingLabel = new JLabel("Waiting for peer...", SwingConstants.CENTER);
        codeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        waitingLabel.setFont(new Font("Arial", Font.ITALIC, 14));

        hostPanel.add(Box.createVerticalGlue());
        hostPanel.add(codeLabel);
        hostPanel.add(Box.createVerticalStrut(20));
        hostPanel.add(waitingLabel);
        hostPanel.add(Box.createVerticalGlue());

        cardLayout.show(mainPanel, "host");
        hostPanel.revalidate();
        hostPanel.repaint();
    }

    private int generateCode() {
        return 1000 + new Random().nextInt(9000); // 4-digit code
    }

    // ===== Custom Function Hooks =====
    private void onHost(int code) {
        System.out.println("Hosting with code: " + code);
        // Add your host logic here
    }

    private void onJoin(String code) {
        System.out.println("Joining with code: " + code);
        // Add your join logic here
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ConnectionSetupGUI app = new ConnectionSetupGUI();
            app.setVisible(true);
        });
    }
}
