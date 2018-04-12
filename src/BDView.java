import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

public class BDView extends JFrame{
    private JButton set = new JButton("Set");
    private JTextField Username = new JTextField(30);
    private JPasswordField Password = new JPasswordField(30);
    private JLabel text1 = new JLabel("Username: ");
    private JLabel text2 = new JLabel("Password: ");

    BDView()
    {
        JPanel panel = new JPanel();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 200);

        panel.add(text1);
        panel.add(Username);
        panel.add(text2);
        panel.add(Password);
        panel.add(set);

        this.add(panel);
    }
    String GetUsername()
    {
        return Username.getText();
    }
    String GetPassword()
    {
        return String.valueOf(Password.getPassword());
    }
    void AddSetListener(ActionListener a)
    {
        set.addActionListener(a);
    }
    void AddWindowListener(WindowAdapter listener)
    {
        this.addWindowListener(listener);
    }
}
