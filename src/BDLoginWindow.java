import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class BDLoginWindow extends JDialog {
    private static int HEIGHT = 150;
    private static int WIDTH = 380;

    private JButton connect = new JButton("Polacz");
    private JButton quit = new JButton("Wyjdz");
    private JTextField Username = new JTextField(30);
    private JPasswordField Password = new JPasswordField(30);
    private JLabel text1 = new JLabel("Login: ");
    private JLabel text2 = new JLabel("Haslo: ");
    private boolean success = false;

    BDLoginWindow(JFrame owner)
    {
        super(owner, "Login", true);
        JPanel loginPanel = new JPanel();
        this.setSize(WIDTH, HEIGHT);
        setUndecorated(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width/2 - this.getSize().width/2 , dim.height/2 - this.getSize().height/2);

        loginPanel.add(text1);
        loginPanel.add(Username);
        loginPanel.add(text2);
        loginPanel.add(Password);
        loginPanel.add(connect);
        loginPanel.add(quit);


        this.add(loginPanel, BorderLayout.CENTER);
        //this.setVisible(true);

    }
    void addConnectListener(ActionListener a)
    {
        connect.addActionListener(a);
    }
    void addQuitListener(ActionListener a){
        quit.addActionListener(a);
    }
    String GetUsername()
    {
        return Username.getText();
    }
    String GetPassword()
    {
        return String.valueOf(Password.getPassword());
    }
}
