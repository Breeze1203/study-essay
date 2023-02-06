package swingDemo;

import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class JFrameDemo02 extends JFrame {
    JLabel jlabel;
    // JFrame的构造方法
    public JFrameDemo02(){
        super("hello world");
        jlabel=new JLabel("a label");
        add(jlabel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300,400);
        setVisible(true);
    }
    static JFrameDemo02 ssp;
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ssp=new JFrameDemo02();
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
                ssp.jlabel.setText("Hey! This is Different!");
            }
        });
    }
}
