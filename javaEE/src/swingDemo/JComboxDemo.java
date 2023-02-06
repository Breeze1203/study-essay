package swingDemo;

import javax.swing.*;

public class JComboxDemo {
    public static void main(String[] args) {
        // 创建窗口
        JFrame jframe=new JFrame("选择界面");
        // 创建面板
        JPanel jPanel=new JPanel();
        // 创建标签
        JLabel jLabel=new JLabel("证件类型");
        // 创建下拉列表框
        JComboBox com=new JComboBox();
        // 在下拉列表框中添加自组
        com.addItem("军人证");
        com.addItem("身份证");
        com.addItem("学生证");
        com.addItem("士兵证");
        // 将组键添加到面板中
        jPanel.add(jLabel);
        jPanel.add(com);
        // 将面板添加到窗口中
        jframe.add(jPanel);
        jframe.setSize(300,400);
        jframe.setVisible(true);
        jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jframe.setLocationRelativeTo(null);
    }
}
