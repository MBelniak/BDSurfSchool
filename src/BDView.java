import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

public class BDView extends JFrame{
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 600;

    private BDLoginWindow loginDialog;

/*    private JButton set = new JButton("Set");
    private JTextField Username = new JTextField(30);
    private JPasswordField Password = new JPasswordField(30);
    private JLabel text1 = new JLabel("Username: ");
    private JLabel text2 = new JLabel("Password: ");*/

    // elementy właściwego view
    private JComboBox<String> tableHeader;
    private JTable dataTable;
    private JScrollPane scrollPane;
    private JTextField fNameField = new JTextField(15);
    private JTextField lNameField = new JTextField(15);
    private JTextField peselField = new JTextField(11);
    private JTextField addressField = new JTextField(30);

    BDView(){

            JPanel tablePanel = new JPanel();
            JPanel comboPanel = new JPanel();
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setSize(WIDTH, HEIGHT);
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            setLocation(dim.width/2 - this.getSize().width/2 , dim.height/2 - this.getSize().height/2);



            tableHeader = new JComboBox<>();
            tableHeader.addItem("Employees");
            tableHeader.addItem("Schools");
            tableHeader.addItem("Courses");
            tableHeader.addItem("Clients");
            tableHeader.addItem("Equipment");

            comboPanel.add(tableHeader);
            //tablePanel.add(dataTable);
            //tablePanel.add(scrollPane);
            tablePanel.add(fNameField);
            tablePanel.add(lNameField);
            tablePanel.add(peselField);
            tablePanel.add(addressField);

            this.add(comboPanel, BorderLayout.NORTH);
            this.add(tablePanel, BorderLayout.CENTER);

            setVisible(true);
    }
    BDLoginWindow createLoginDialog()
    {
        loginDialog = new BDLoginWindow(this);

        return loginDialog;
    }
    void displayColumns(){

    }

    BDLoginWindow GetDialogWindow()
    {
        System.out.print("jest");
        return loginDialog;
    }/*
    void AddSetListener(ActionListener a)
    {
        set.addActionListener(a);
    }*/
    void AddWindowListener(WindowAdapter listener)
    {
        //button.addWindowListener(listener);   tu jak zrobisz okno pojawiajace sie po zalogowaniu to do niego dodasz listenera,
                                                //ktory zamyka polaczenie
    }

}
