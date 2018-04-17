import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddWindow extends JDialog {
    private static int HEIGHT = 300;
    private static int WIDTH = 400;
    private static JLabel[] labels = {new JLabel("PESEL"),   //0
        new JLabel("FirstName"),                //1
        new JLabel("SecondName"),               //2
        new JLabel("Address"),                  //3
        new JLabel("JobType"),                  //4
        new JLabel("Salary"),                   //5
        new JLabel("School_ID"),                //6
        new JLabel("Supervisor_PESEL"),         //7
        new JLabel("Manager_PESEL"),            //8
        new JLabel("Name"),                     //9
        new JLabel("Equipment_id"),             //10
        new JLabel("ProductionYear"),           //11
        new JLabel("Client_PESEL"),             //12
        new JLabel("Course_Beginning_Date"),    //13
        new JLabel("Course_End_Date"),          //14
        new JLabel("Course_Employee_PESEL"),    //15
        new JLabel("Beginning_Date"),           //16
        new JLabel("End_Date"),                 //17
        new JLabel("Employee_PESEL")};          //18
    private ArrayList<String> columns = new ArrayList<String>();
    private ArrayList<JTextField> textFields = new ArrayList<JTextField>();
    private JComboBox<String> JobType = new JComboBox<>();
    private String header;

    private JButton EnterButton = new JButton("Wprowadz");
    private JButton cancelButton = new JButton("Anuluj");

    AddWindow(JFrame owner , String header){
        super(owner, "Dodaj rekord", true);

        this.header = header;
        JPanel panel = new JPanel();
        JPanel buttonPanel = new JPanel();
        this.setSize(WIDTH, HEIGHT);
        setUndecorated(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width/2 - this.getSize().width/2 , dim.height/2 - this.getSize().height/2);

        GridLayout myLayout = new GridLayout(0,2);
        panel.setLayout(myLayout);

        buttonPanel.add(EnterButton);
        buttonPanel.add(cancelButton);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        this.add(buttonPanel, BorderLayout.SOUTH);

        JobType.addItem("Manager");
        JobType.addItem("Instructor");
        JobType.addItem("Bosman");
        JobType.addItem("Receptionist");
        JobType.addItem("Supervisor");

        if(header.equals("Employees")) {
            for(int i = 0; i<7; i++)
            {
                textFields.add(new JTextField(25));
                columns.add(labels[i].getText());
            }
            columns.add(labels[7].getText());

            panel.add(labels[0]);
            panel.add(textFields.get(0));
            panel.add(labels[1]);
            panel.add(textFields.get(1));
            panel.add(labels[2]);
            panel.add(textFields.get(2));
            panel.add(labels[3]);
            panel.add(textFields.get(3));
            panel.add(labels[4]);
            panel.add(JobType);
            for(int i = 5; i<8;i++)
            {
                panel.add(labels[i]);
                panel.add(textFields.get(i-1));
            }
            this.add(panel, BorderLayout.CENTER);

            }
            else if(header.equals("Schools"))
            {
                for(int i = 0; i<4; i++)
                {
                    textFields.add(new JTextField(25));
                }
                columns.add(labels[6].getText());
                columns.add(labels[3].getText());
                columns.add(labels[8].getText());
                columns.add(labels[9].getText());

                panel.add(labels[6]);
                panel.add(textFields.get(0));
                panel.add(labels[3]);
                panel.add(textFields.get(1));
                panel.add(labels[8]);
                panel.add(textFields.get(2));
                panel.add(labels[9]);
                panel.add(textFields.get(3));


                this.add(panel, BorderLayout.CENTER);
            }
        else if(header.equals("Equipment")) {
            for (int i = 0; i < 4; i++) {
                textFields.add(new JTextField(25));
            }
            columns.add(labels[10].getText());
            columns.add(labels[11].getText());
            columns.add(labels[6].getText());
            columns.add(labels[12].getText());

            panel.add(labels[10]);
            panel.add(textFields.get(0));
            panel.add(labels[11]);
            panel.add(textFields.get(1));
            panel.add(labels[6]);
            panel.add(textFields.get(2));
            panel.add(labels[12]);
            panel.add(textFields.get(3));

            this.add(panel, BorderLayout.CENTER);
        }
        else if(header.equals("Clients")) {
            for (int i = 0; i < 4; i++) {
                textFields.add(new JTextField(25));
                columns.add(labels[i].getText());
            }

            for (int i = 0; i < 4; i++) {
                panel.add(labels[i]);
                panel.add(textFields.get(i));
            }


            this.add(panel, BorderLayout.CENTER);
        }
        else if(header.equals("Courses_clients"))
            {
                for(int i = 0; i<2; i++)
                {
                    textFields.add(new JTextField("RRRRMMDD", 25));
                }
                for(int i = 0; i<2; i++)
                {
                    textFields.add(new JTextField( 25));
                }
                columns.add(labels[13].getText());
                columns.add(labels[14].getText());
                columns.add(labels[15].getText());
                columns.add(labels[12].getText());

                panel.add(labels[13]);
                panel.add(textFields.get(0));
                panel.add(labels[14]);
                panel.add(textFields.get(1));
                panel.add(labels[15]);
                panel.add(textFields.get(2));
                panel.add(labels[12]);
                panel.add(textFields.get(3));

                this.add(panel, BorderLayout.CENTER);
            }
            else if(header.equals("Courses"))
            {
                for(int i = 0; i<2; i++)
                {
                    textFields.add(new JTextField("RRRRMMDD",25));
                }
                textFields.add(new JTextField(25));
                columns.add(labels[16].getText());
                columns.add(labels[17].getText());
                columns.add(labels[18].getText());

                panel.add(labels[16]);
                panel.add(textFields.get(0));
                panel.add(labels[17]);
                panel.add(textFields.get(1));
                panel.add(labels[18]);
                panel.add(textFields.get(2));

                this.add(panel, BorderLayout.CENTER);
            }

    }
    ArrayList<JTextField> getTextFields()
    {
        return textFields;
    }
    ArrayList<String> getLabels()
    {
        return columns;
    }
    void AddEnterListener(ActionListener a)
    {
        EnterButton.addActionListener(a);
    }
    void AddCancelListener (ActionListener a)
    {
        cancelButton.addActionListener(a);
    }
    boolean isEmployee()
    {
        return header.equals("Employees");
    }
    String getJobType()
    {
        return JobType.getItemAt(JobType.getSelectedIndex());
    }
}
