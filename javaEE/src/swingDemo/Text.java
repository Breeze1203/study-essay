package swingDemo;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class Text extends JFrame{
    JLabel jlabel=new JLabel("A label");

    public Text(){
        super("Hello Swing");
        // 将组建添加到窗口中
        add(jlabel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300,400);
        setVisible(true);
        setLocationRelativeTo(null);
    }
    static Text text;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                text=new Text();

            }
        });
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                text.jlabel.setText("Hey This is Different");
            }
        });
    }
}
