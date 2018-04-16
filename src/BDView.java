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
    private JTable dataTable = new JTable();
    private JScrollPane scrollPane;

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

    private JButton addButton = new JButton("+");
    private JButton removeButton = new JButton("-");
    private JButton updateButton = new JButton("Update");



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
            tableHeader.addItem("Courses_clients");
            tableHeader.addItem("Clients");
            tableHeader.addItem("Equipment");


            buttonPanel.add(addButton);
            buttonPanel.add(removeButton);
            buttonPanel.add(updateButton);


            comboPanel.add(tableHeader);
            tablePanel.add(dataTable);
            //tablePanel.add(scrollPane);


            setCheckBoxPanel("Employees");


            this.add(comboPanel, BorderLayout.NORTH);
            this.add(tablePanel, BorderLayout.CENTER);
            this.add(buttonPanel, BorderLayout.EAST);

            //setVisible(true);
    }
    void setCheckBoxPanel(String tableName){
        checkBoxPanel.removeAll();
        JPanel checkBox = new JPanel();
        switch (tableName){
            case "Employees":{
                checkBox.add(pesel);
                pesel.setSelected(true);
                checkBox.add(firstName);
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
                checkBox.add(schoolIdPK);
                checkBox.add(address);
                checkBox.add(managerPesel);
                checkBox.add(name);
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
                checkBox.add(firstName);
                checkBox.add(secondName);
                checkBox.add(address);
                checkBox.add(dateOfBirth);
                break;
            }
            case "Equipment":{
                checkBox.add(eqId);
                checkBox.add(prodYear);
                checkBox.add(schoolId);
                checkBox.add(clientPeselFK);
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

    BDLoginWindow createLoginDialog()
    {
        loginDialog = new BDLoginWindow(this);

        return loginDialog;
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
        //button.addWindowListener(listener);   tu jak zrobisz okno pojawiajace sie po zalogowaniu to do niego dodasz listenera,
                                                //ktory zamyka polaczenie
    }

}
