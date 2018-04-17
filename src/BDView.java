import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class BDView extends JFrame{
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 600;

    private BDLoginWindow loginDialog;
    private AddWindow addWindow;
    private JPanel tablePanel;
    private JPanel comboPanel;
    private JPanel checkBoxPanel;
    private JPanel buttonPanel;

    // elementy właściwego view
    private JComboBox<String> tableHeader;
    private JTable dataTable = new JTable();
    //private JScrollPane scrollPane = new JScrollPane(dataTable);

    private JCheckBox pesel = new JCheckBox("PESEL");
    private JCheckBox firstName = new JCheckBox("firstname");
    private JCheckBox secondName = new JCheckBox("secondname");
    private JCheckBox dateOfBirth = new JCheckBox("dateofbirth");
    private JCheckBox address = new JCheckBox("address");
    private JCheckBox jobType = new JCheckBox("jobtype");
    private JCheckBox salaray = new JCheckBox("salary");
    private JCheckBox schoolId = new JCheckBox("school_id");
    private JCheckBox supPesel = new JCheckBox("supervisor_PESEL");
    private JCheckBox schoolIdPK = new JCheckBox("school_id");
    private JCheckBox managerPesel = new JCheckBox("manager_PESEL");
    private JCheckBox clientPesel = new JCheckBox("client_PESEL");
    private JCheckBox clientPeselFK = new JCheckBox("client_PESEL");
    private JCheckBox employeePesel = new JCheckBox("employee_PESEL");
    private JCheckBox name = new JCheckBox("name");
    private JCheckBox eqId = new JCheckBox("equipment_id");
    private JCheckBox begDate = new JCheckBox("beginning_date");
    private JCheckBox endDate = new JCheckBox("end_date");
    private JCheckBox prodYear = new JCheckBox("productionyear");
    private JCheckBox courseBegDate = new JCheckBox("course_beginning_date");
    private JCheckBox courseEndDate = new JCheckBox("course_end_date");
    private JCheckBox courseEmpPesel = new JCheckBox("course_employee_PESEL");
    private JCheckBox courseClientPesel = new JCheckBox("client_PESEL");

    private JCheckBox id = new JCheckBox("id");

    private JList<JButton> buttonJList = new JList<>();
    private JButton addButton = new JButton("+");
    private JButton removeButton = new JButton("-");
    private JButton updateButton = new JButton("Update");
    private JButton showButton = new JButton("Show");



    BDView(){
            createLoginDialog();
            tablePanel = new JPanel();
            comboPanel = new JPanel();
            checkBoxPanel = new JPanel();
            buttonPanel = new JPanel();
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setSize(WIDTH, HEIGHT);
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            setLocation(dim.width/2 - this.getSize().width/2 , dim.height/2 - this.getSize().height/2);


            tableHeader = new JComboBox<>();
            tableHeader.addItem("None");
            tableHeader.addItem("Employees");
            tableHeader.addItem("Schools");
            tableHeader.addItem("Courses");
            tableHeader.addItem("Courses_clients");
            tableHeader.addItem("Clients");
            tableHeader.addItem("Equipment");


            buttonPanel.add(addButton);
            buttonPanel.add(removeButton);
            buttonPanel.add(updateButton);
            buttonPanel.add(showButton);



            comboPanel.add(tableHeader);
            tablePanel.add(dataTable);
            //tablePanel.add(scrollPane);


            setCheckBoxPanel("-");


            this.add(comboPanel, BorderLayout.NORTH);
            this.add(tablePanel, BorderLayout.CENTER);
            this.add(buttonPanel, BorderLayout.EAST);
            //this.add(scrollPane,BorderLayout.CENTER);

    }
    void setCheckBoxPanel(String tableName){
        checkBoxPanel.removeAll();
        JPanel checkBox = new JPanel();
        switch (tableName){
            case "Employees":{
                checkBox.add(pesel);
                checkBox.add(firstName);        firstName.setSelected(false);
                checkBox.add(secondName);       secondName.setSelected(false);
                checkBox.add(dateOfBirth);      dateOfBirth.setSelected(false);
                checkBox.add(address);          address.setSelected(false);
                checkBox.add(jobType);          jobType.setSelected(false);
                checkBox.add(salaray);          salaray.setSelected(false);
                checkBox.add(schoolId);         schoolId.setSelected(false);
                checkBox.add(supPesel);         supPesel.setSelected(false);
                break;
            }
            case "Schools":{
                checkBox.add(schoolIdPK);
                checkBox.add(address);          address.setSelected(false);
                checkBox.add(managerPesel);     managerPesel.setSelected(false);
                checkBox.add(name);             name.setSelected(false);
                break;
            }
            case "Courses":{
                checkBox.add(begDate);
                checkBox.add(endDate);
                checkBox.add(employeePesel);
                break;
            }
            case "Clients":{
                checkBox.add(clientPesel);
                checkBox.add(firstName);        firstName.setSelected(false);
                checkBox.add(secondName);       secondName.setSelected(false);
                checkBox.add(address);          address.setSelected(false);
                checkBox.add(dateOfBirth);      dateOfBirth.setSelected(false);
                break;
            }
            case "Equipment":{
                checkBox.add(eqId);
                checkBox.add(prodYear);         prodYear.setSelected(false);
                checkBox.add(schoolId);         schoolId.setSelected(false);
                checkBox.add(clientPeselFK);    clientPeselFK.setSelected(false);
                break;
            }
            case "Courses_clients":{
                checkBox.add(courseBegDate);
                checkBox.add(courseEndDate);
                checkBox.add(courseEmpPesel);
                checkBox.add(courseClientPesel);
                break;
            }
        }
        checkBoxPanel = checkBox;
        this.add(checkBoxPanel, BorderLayout.SOUTH);
        checkBoxPanel.validate();
        checkBoxPanel.revalidate();
        checkBoxPanel.repaint();
    }


    String getTableHeaders(){
        return tableHeader.getItemAt(tableHeader.getSelectedIndex());
    }

    void createLoginDialog()
    {
        loginDialog = new BDLoginWindow(this);
    }

    String[] getLoginData()
    {
        String[] lap = new String[2];
        lap[0] = loginDialog.GetUsername();
        lap[1] = loginDialog.GetPassword();
        return lap;
    }
    void CloseLoginDialog()
    {
        loginDialog.setVisible(false);
    }
    void OpenAddWindow(ActionListener a, ActionListener b)
    {
        addWindow = new AddWindow(this, getTableHeaders());
        addWindow.AddEnterListener(a);
        addWindow.AddCancelListener(b);
        addWindow.setVisible(true);
    }
    void CloseAddWindow()
    {
        addWindow.setVisible(false);
    }
    ArrayList<String> getAddWindowLabels()
    {
        ArrayList<String> result = new ArrayList<>();
        for(int i = 0; i<addWindow.getLabels().size(); i++)
            result.add(addWindow.getLabels().get(i));
        return result;
    }
    ArrayList<String> getAddWindowValues()
    {
        ArrayList<String> result = new ArrayList<>();
        if(addWindow.isEmployee())
        {
            for(int i = 0; i<4; i++)
                result.add(addWindow.getTextFields().get(i).getText());
            result.add(addWindow.getJobType());
            for(int i =4; i<7;i++)
            {
                result.add(addWindow.getTextFields().get(i).getText());
            }
        }
        else {
            for (int i = 0; i < addWindow.getTextFields().size(); i++)
                result.add(addWindow.getTextFields().get(i).getText());
        }
        return result;
    }
    void AddAddButtonListener(ActionListener a)
    {
        addButton.addActionListener(a);
    }
    void AddShowButtonListener(ActionListener a)
    {
        showButton.addActionListener(a);
    }
    void AddConnectListener(ActionListener a)
    {
        loginDialog.addConnectListener(a);
    }

    void AddComboListener(ActionListener a)
    {
        tableHeader.addActionListener(a);
    }

    void AddCheckBoxListener(ActionListener a)
    {
        firstName.addActionListener(a);
        secondName.addActionListener(a);
        dateOfBirth.addActionListener(a);
        address.addActionListener(a);
        jobType.addActionListener(a);
        salaray.addActionListener(a);
        schoolId.addActionListener(a);
        supPesel.addActionListener(a);
        managerPesel.addActionListener(a);
        name.addActionListener(a);
        prodYear.addActionListener(a);
        clientPeselFK.addActionListener(a);
    }
    void AddCheckBoxAlwaysSelectedListener(ActionListener a){
        pesel.addActionListener(a);             pesel.setSelected(true);
        schoolIdPK.addActionListener(a);        schoolIdPK.setSelected(true);
        clientPesel.addActionListener(a);       clientPesel.setSelected(true);
        begDate.addActionListener(a);           begDate.setSelected(true);
        endDate.addActionListener(a);           endDate.setSelected(true);
        employeePesel.addActionListener(a);     employeePesel.setSelected(true);
        eqId.addActionListener(a);              eqId.setSelected(true);
        courseBegDate.addActionListener(a);     courseBegDate.setSelected(true);
        courseEndDate.addActionListener(a);     courseEndDate.setSelected(true);
        courseEmpPesel.addActionListener(a);    courseEmpPesel.setSelected(true);
        courseClientPesel.addActionListener(a); courseClientPesel.setSelected(true);
    }


    void AddWindowListener(WindowAdapter listener)
    {
        this.addWindowListener(listener);
    }
    void DisplayErrorMessage(String message)
    {
        JOptionPane.showMessageDialog(this, message);
    }
    void OpenMainMenu()
    {
        comboPanel.setVisible(true);
        checkBoxPanel.setVisible(true);
        buttonPanel.setVisible(true);

    }
    void OpenLoginDialog()
    {
        loginDialog.setVisible(true);
    }

    void ShowResultTable(ArrayList<String> attributes, ResultSet resultSet) throws SQLException {
        ResultSetMetaData rsmd = resultSet.getMetaData();

        String[] arrayOfAttributes = new String[attributes.size()];
        for(int i = 0 ; i < attributes.size(); i++){
            arrayOfAttributes[i] = attributes.get(i);
        }
        DefaultTableModel tableModel = new DefaultTableModel(arrayOfAttributes , 0);
        while (resultSet.next())
        {
            String[] tmp = new String[attributes.size()];

            for(int i = 1; i <= rsmd.getColumnCount(); i++)
            {
                tmp[i-1] = resultSet.getString(i);
            }
            tableModel.addRow(tmp);
        }
        JScrollPane scrollPane = new JScrollPane(dataTable);
        dataTable.setModel(tableModel);
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        tablePanel.validate();
        tablePanel.revalidate();
        tablePanel.repaint();
    }

}
