import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

public class BDView extends JFrame{
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 600;

    private BDLoginWindow loginDialog;

    private JPanel tablePanel;
    private JPanel comboPanel;
    private JPanel checkBoxPanel;
    private JPanel buttonPanel;

    // elementy właściwego view
    private JComboBox<String> tableHeader;
    private JTable dataTable;
    private JScrollPane scrollPane;

    private JCheckBox pesel = new JCheckBox("PESEL");
    private JCheckBox name = new JCheckBox("Name");
    private JCheckBox secondName = new JCheckBox("Second Name");
    private JCheckBox dateOfBirth = new JCheckBox("dateOfBirth");
    private JCheckBox address = new JCheckBox("Address");
    private JCheckBox jobType = new JCheckBox("Job Type");
    private JCheckBox salaray = new JCheckBox("Salary");
    private JCheckBox supPesel = new JCheckBox("Supervisor Pesel");
    private JCheckBox begDate = new JCheckBox("Beginning Date");
    private JCheckBox endDate = new JCheckBox("Ending Date");
    private JCheckBox prodYear = new JCheckBox("production Year");
    private JCheckBox schoolId = new JCheckBox("School_id");
    private JCheckBox id = new JCheckBox("id");

    private JButton addButton = new JButton("+");
    private JButton removeButton = new JButton("-");
    private JButton updateButton = new JButton("Update");
    private JTextField fNameField = new JTextField(15);
    private JTextField lNameField = new JTextField(15);
    private JTextField peselField = new JTextField(11);
    private JTextField addressField = new JTextField(30);


    BDView(){

            tablePanel = new JPanel();
            comboPanel = new JPanel();
            checkBoxPanel = new JPanel();
            buttonPanel = new JPanel();
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


            buttonPanel.add(addButton);
            buttonPanel.add(removeButton);
            buttonPanel.add(updateButton);


            comboPanel.add(tableHeader);
            //tablePanel.add(dataTable);
            //tablePanel.add(scrollPane);
            tablePanel.add(fNameField);
            tablePanel.add(lNameField);
            tablePanel.add(peselField);
            tablePanel.add(addressField);

            setCheckBoxPanel("Employee");

            this.add(comboPanel, BorderLayout.NORTH);
            this.add(tablePanel, BorderLayout.CENTER);
            //this.add(checkBoxPanel, BorderLayout.SOUTH);
            this.add(buttonPanel, BorderLayout.EAST);

            //setVisible(true);
    }
    void setCheckBoxPanel(String tableName){
        checkBoxPanel.removeAll();
        JPanel checkBox = new JPanel();
        switch (tableName){
            case "Employee":{
                checkBox.add(pesel);
                checkBox.add(name);
                checkBox.add(secondName);
                checkBox.add(dateOfBirth);
                checkBox.add(address);
                checkBox.add(jobType);
                checkBox.add(salaray);
                checkBox.add(schoolId);
                checkBox.add(supPesel);
                break;
            }
            case "Schools":{
                checkBox.add(schoolId);
                checkBox.add(address);
                checkBox.add(pesel);
                checkBox.add(name);
                break;
            }
            case "Courses":{
                checkBox.add(begDate);
                checkBox.add(endDate);
                checkBox.add(pesel);
                break;
            }
            case "Clients":{
                checkBox.add(pesel);
                checkBox.add(name);
                checkBox.add(secondName);
                checkBox.add(address);
                checkBox.add(dateOfBirth);
                break;
            }
            case "Equipment":{
                checkBox.add(id);
                checkBox.add(prodYear);
                checkBox.add(schoolId);
                checkBox.add(pesel);
                break;
            }
        }
        checkBoxPanel = checkBox;
        this.add(checkBoxPanel, BorderLayout.SOUTH);
        checkBoxPanel.revalidate();
    }

    String getTableHeaders(){
        return tableHeader.getItemAt(tableHeader.getSelectedIndex());
    }

    BDLoginWindow createLoginDialog()
    {
        loginDialog = new BDLoginWindow(this);

        return loginDialog;
    }

    void AddComboListener(ActionListener a)
    {
        tableHeader.addActionListener(a);
    }

    void AddWindowListener(WindowAdapter listener)
    {
        //button.addWindowListener(listener);   tu jak zrobisz okno pojawiajace sie po zalogowaniu to do niego dodasz listenera,
                                                //ktory zamyka polaczenie
    }

}
