import javax.swing.*;

public class Driver {
    public static void main (String[] args){
//        Business cnh = new Business("CNH Industrial", "cnh@cnhind.com", "SWE Intern", "U softengineer it", new String[]{"Softy", "Engineering"}, new String[]{"Remote"}, 24 );
//        Applicant charlie = new Applicant("Charlie DeWitt", "charliedewitt20@gmail.com", null, new String[]{"Handsome", "Smart", "Buff"}, new String[]{"Anywhere"}, 1000000);
//        System.out.println(cnh);
//        System.out.println(charlie);

        JFrame frame = new JFrame("Log In");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(750, 500);
        frame.setContentPane(new App().panelMain);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);

        ImageIcon logo = new ImageIcon("C://Users//charl//codeREPO//JobSift//resources//JobSift_logo.png");
        frame.setIconImage(logo.getImage());

    }
}
