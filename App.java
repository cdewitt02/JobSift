import javax.swing.*;
import java.awt.*;

public class App {
    public App(){
        JFrame frame = new JFrame();
        frame.setTitle("Log In");
        frame.setSize(750,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);

        ImageIcon logo = new ImageIcon("C://Users//charl//codeREPO//JobLink//resources//JobSift_logo.png");
        frame.setIconImage(logo.getImage());
        frame.getContentPane().setBackground(Color.decode("#FFD700"));
    }

}
