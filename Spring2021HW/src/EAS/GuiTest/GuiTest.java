package GuiTest;

import javax.swing.*;
import java.awt.*;

public class GuiTest {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        TextField textField = new TextField();
        frame.setLayout(new BorderLayout());
        frame.setPreferredSize(new Dimension(800, 600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.add(textField);

        Runtime runtime = Runtime.getRuntime();
        System.out.println(runtime);
        runtime.exit(-1);
        System.out.println(1);
    }
}
