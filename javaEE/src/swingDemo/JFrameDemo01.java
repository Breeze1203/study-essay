package swingDemo;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class JFrameDemo01 {
    public static void main(String[] args) {
        // 创建一个可视化对象 同时设置标题
        JFrame frame=new JFrame("hello swing");
        // JLabel标签组件，用来显示文本或图片 该类加上final表明不会被继承，处于安全考虑
        final JLabel jLabel=new JLabel("a label");
        // 将文本添加到界面中
        frame.add(jLabel);
        // 设置窗口关闭，程序停止运行
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 设置窗口大小
        frame.setSize(300,400);
        // 设置窗口是否可见
        frame.setVisible(true);
        // 设置窗口位置居中
        frame.setLocationRelativeTo(null);
        try {
            // 让其休眠五分钟，然后显示下一段文本
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Swing事件分发线程提交的任务，可将该任务提交给SwingUtilities.invokeLater()
        // 此方法会将任务放置到待执行事件队伍中
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                jLabel.setText("你个大傻逼");
            }
        });
        // System.out.println(jLabel);
    }
}
