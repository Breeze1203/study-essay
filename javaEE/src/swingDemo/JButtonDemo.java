package swingDemo;

import javax.swing.*;
import java.awt.*;

public class JButtonDemo{
    public static void main(String[] args) {
        JFrame jFrame=new JFrame("title");
        jFrame.setSize(300,400);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
        jFrame.setLocationRelativeTo(null);
        JButton jButton=new JButton("登录");
        jFrame.add(jButton);
    }
}
