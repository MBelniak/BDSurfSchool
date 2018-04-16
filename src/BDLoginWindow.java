import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class BDLoginWindow extends JDialog {
    private static int HEIGHT = 300;
    private static int WIDTH = 400;

    private JButton connect = new JButton("Connect");
    private JTextField Username = new JTextField(30);
    private JPasswordField Password = new JPasswordField(30);
    private JLabel text1 = new JLabel("Username: ");
    private JLabel text2 = new JLabel("Password: ");
    private boolean success = false;

    BDLoginWindow(JFrame owner)
    {
        super(owner, "Login Window", true);
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

        this.add(loginPanel, BorderLayout.CENTER);
        //this.setVisible(true);

    }
    void addConnectListener(ActionListener a)
    {
        connect.addActionListener(a);
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
