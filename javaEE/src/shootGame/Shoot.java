package shootGame;

import javax.swing.*;
import java.awt.*;

public class Shoot extends JFrame {
    public void start(){
        // 设置大小
        this.setSize(300,400);
        // 是否可见
        this.setTitle("飞机大战");
        // 是否可见
        this.setVisible(true);
        // 设置位置
        this.setLocationRelativeTo(null);
        //  设置退出就关闭
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(GameUtils.t1,0,0,300,400,this);
        g.drawImage(GameUtils.t3,112,0,75,100,this);
        g.drawImage(GameUtils.t2,122,355,45,45,this);
    }


}
