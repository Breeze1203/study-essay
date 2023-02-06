package swingDemo;

import javax.swing.*;
import java.awt.*;

public class Text01 extends JFrame{
    JButton button=new JButton("登录");
    public Text01(){
        setTitle("登录界面");
        setSize(300,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        add(button);
    }
    public static Text01 Text02;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Text02 = new Text01();
            }
        });
    }
}
