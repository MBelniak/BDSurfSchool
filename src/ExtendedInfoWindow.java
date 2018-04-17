import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class ExtendedInfoWindow extends JDialog {
    private static int HEIGHT = 600;
    private static int WIDTH = 600;

    private JTable extendedTable = new JTable();
    private JButton cancelButton = new JButton("Cancel");
    JPanel panel = new JPanel();
    JPanel buttonPanel = new JPanel();

    ExtendedInfoWindow(JFrame owner, String header)
    {
        super(owner, header, true);

        this.setSize(WIDTH, HEIGHT);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width/2 - this.getSize().width/2, dim.height/2 - this.getSize().height/2);

        GridLayout extendedLayout = new GridLayout(0,1 );
        this.setLayout(extendedLayout);

        panel.add(extendedTable);
        buttonPanel.add(cancelButton, BorderLayout.SOUTH);

        this.add(panel);
        this.add(buttonPanel);
    }
    void showExtendedTable(ResultSet rs, ArrayList<String> columnNames) throws SQLException
    {
        ResultSetMetaData rsmd = rs.getMetaData();
        String[] arrayOfColumns = new String[columnNames.size()];

        panel.removeAll();

        for(int i = 0 ; i < columnNames.size(); i++){
            arrayOfColumns[i] = columnNames.get(i);
        }
        DefaultTableModel extendedModel = new DefaultTableModel(arrayOfColumns, 0);
        String[] tmpValues = new String[rsmd.getColumnCount()];
        while (rs.next())
        {
            String[] tmp = new String[columnNames.size()];

            for(int i = 1; i <= rsmd.getColumnCount(); i++)
            {
                if(rs.getString(i) != null)
                    tmp[i-1] = rs.getString(i);
            }
            extendedModel.addRow(tmp);
        }
        extendedModel.addRow(tmpValues);
        JScrollPane scrollPane = new JScrollPane(extendedTable);
        extendedTable.setModel(extendedModel);
        panel.add(scrollPane,BorderLayout.CENTER);
        panel.validate();
        panel.revalidate();
        panel.repaint();
    }
    void AddCancelListener(ActionListener a)
    {
        cancelButton.addActionListener(a);
    }
}
