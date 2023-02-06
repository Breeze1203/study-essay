package swingDemo;

import javax.swing.*;
import java.awt.*;

public class TextFiledDemo {
    public static void main(String[] args) {
        JFrame jFrame=new JFrame();
        JPanel jPanel=new JPanel();
        // 创建文本框
        JTextField jTextField=new JTextField();
        // 创建复选框
        JCheckBox jCheckBox=new JCheckBox("java",true);
        // 创建文本框
        jTextField.setText("普通文本框");
        jTextField.setFont(new Font("楷体",Font.BOLD,16));
        // 创建一个数组存值
        String[] strings=new String[]{"身份证","学生证","军人证"};
        // 创建一个列表框
        JList jList=new JList<>(strings);
        jPanel.add(jTextField);
        jPanel.add(jList);
        jPanel.add(jCheckBox);
        jFrame.add(jPanel);
        jFrame.setSize(300,400);
        jFrame.setVisible(true);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}