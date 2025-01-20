package net.bxx2004.exe;

import javax.swing.*;
import java.awt.*;

/**
 * 预定义配置文件错误提示框
 */
public class ErrorView extends JFrame {
    public ErrorView(String content) {
        super("错误");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        JLabel label = new JLabel(content);
        label.setVisible(true);
        label.setFont(new Font("宋体", Font.BOLD, 16));
        label.setForeground(Color.RED);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        add(label);
        setVisible(true);
    }
}
