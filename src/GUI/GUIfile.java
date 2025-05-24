package GUI;

import javax.swing.*;

public class GUIfile {


    public static void main(String[] args) {
        int width=600,height=800;

        JFrame frame=new JFrame();
        frame.setBounds(0,0,width,height);

        JButton hostButton = new JButton("HOST");
        JButton joinButton = new JButton("JOIN");
        hostButton.setBounds(0,0,100,100);
        hostButton.setBounds(100,100,50,50);


        frame.add(hostButton);
        frame.add(joinButton);




        frame.setVisible(true);
    }
}
