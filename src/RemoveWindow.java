import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class RemoveWindow extends JDialog{
    private static int HEIGHT = 100;
    private static int WIDTH = 450;
    private JLabel label = new JLabel();
    private JButton confirmButton = new JButton("Yes");
    private JButton cancelButton = new JButton("Cancel");
    private String header;

    RemoveWindow(JFrame owner, String header){
        super(owner, "Remove Window", true);

        this.header = header;
        JPanel labelPanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        this.setSize(WIDTH, HEIGHT);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width/2 - this.getSize().width/2 , dim.height/2 - this.getSize().height/2);

        GridLayout removeLayout = new GridLayout(1,2);
        buttonPanel.setLayout(removeLayout);

        label.setText("Do you really want to remove selected record from " + header + " table?");

        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);
        labelPanel.add(label);

        this.add(labelPanel,BorderLayout.NORTH);
        this.add(buttonPanel,BorderLayout.SOUTH);
    }

    void AddConfirmButtonListener(ActionListener a)
    {
        confirmButton.addActionListener(a);
    }
    void AddCancelListener (ActionListener a)
    {
        cancelButton.addActionListener(a);
    }}
