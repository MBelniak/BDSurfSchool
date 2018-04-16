import javax.swing.*;
import java.awt.*;

public class AddWindow extends JDialog {
    private static int HEIGHT = 200;
    private static int WIDTH = 700;

    private JLabel pesel = new JLabel("PESEL");
    private JLabel name = new JLabel("Name");
    private JLabel seconName = new JLabel("Second Name");
    private JLabel dateOfBirth = new JLabel("Date Of Birth");
    private JLabel address = new JLabel("Address");
    private JLabel jobType = new JLabel("Job Type");
    private JLabel salary = new JLabel("Salary");
    private JLabel schoolId = new JLabel("School_ID");
    private JLabel supPesel = new JLabel("Supervisor PESEL");
    private JLabel begDate = new JLabel("Beginning Date");
    private JLabel endDate = new JLabel("End Date");
    private JLabel prodYear = new JLabel("Production Year");

    private JTextField peselText = new JTextField();
    private JTextField nameText = new JTextField();
    private JTextField secondNameText = new JTextField();
    private JTextField dateBirthText = new JTextField();
    private JTextField addressText = new JTextField();
    private JTextField jobTypeText = new JTextField();
    private JTextField salaryText = new JTextField();
   // private JTextField peselText = new JTextField();
    private JTextField schoolIdText = new JTextField();
    private JTextField supPeselText = new JTextField();
    private JTextField begDateText = new JTextField();
    private JTextField prodYearText = new JTextField();

    private JButton addButton = new JButton("Add");
    private JButton cancelButton = new JButton("Cancel");

    AddWindow(JFrame owner , String header){
        super(owner, "Login Window", true);
        JPanel labelPanel = new JPanel();
        JPanel textFieldPanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        this.setSize(WIDTH, HEIGHT);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width/2 - this.getSize().width/2 , dim.height/2 - this.getSize().height/2);

        buttonPanel.add(addButton);
        buttonPanel.add(cancelButton);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        this.add(buttonPanel, BorderLayout.WEST);


        switch (header){
            case "Employees":{
                labelPanel.add(pesel);
                labelPanel.add(name);
                labelPanel.add(seconName);
                labelPanel.add(dateOfBirth);
                labelPanel.add(address);
                labelPanel.add(jobType);
                labelPanel.add(salary);
                labelPanel.add(schoolId);
                labelPanel.add(supPesel);

                textFieldPanel.add(peselText);
                textFieldPanel.add(nameText);
                textFieldPanel.add(secondNameText);
                textFieldPanel.add(dateBirthText);
                textFieldPanel.add(addressText);
                textFieldPanel.add(jobTypeText);
                textFieldPanel.add(salaryText);
                textFieldPanel.add(schoolIdText);
                textFieldPanel.add(supPeselText);


                this.add(labelPanel, BorderLayout.NORTH);
                this.add(textFieldPanel, BorderLayout.SOUTH);
                //TODO dokonczyć switcha
            }
        }
        this.setVisible(true);
    }
}
