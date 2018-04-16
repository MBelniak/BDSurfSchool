import javafx.scene.control.ComboBox;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;

public class BDController {
    private BDModel model;
    private BDView view;
    private BDLoginWindow loginWindow;

    class LoginListener extends ButtonListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            model.MakeConnection(loginWindow.GetUsername(), loginWindow.GetPassword());

            if(model.TestConnection()){
                loginWindow.setVisible(false);
            }
//           System.out.println(model.error);
        }
    }
    class ComboListener extends ButtonListener{
        @Override
        public void actionPerformed(ActionEvent e)
        {
            view.setCheckBoxPanel(view.getTableHeaders());
            JComboBox<String> box;
            box =(JComboBox<String>) e.getSource();
            ArrayList<String> defaultNames = new ArrayList<>();
            switch (box.getItemAt(box.getSelectedIndex())){
                case "Employees":{
                    defaultNames.add("PESEL");
                    break;
                }
                case "Schools":{
                    defaultNames.add("school_id");
                    break;
                }
                case "Courses_clients":{
                    defaultNames.add("course_beginning_date");
                    defaultNames.add("course_end_date");
                    defaultNames.add("course_employee_PESEL");
                    defaultNames.add("client_PESEL");
                    break;
                }
                case "Clients":{
                    defaultNames.add("client_PESEL");
                    break;
                }
                case "Courses":{
                    defaultNames.add("beginning_date");
                    defaultNames.add("end_date");
                    defaultNames.add("employee_PESEL");
                    break;
                }
                case "Equipment":{
                    defaultNames.add("equipment_id");
                    break;
                }
            }

            System.out.print(defaultNames);
        }
    }
    class AddButtonListener extends ButtonListener{
        @Override
        public void actionPerformed(ActionEvent e)
        {
            //TODO listener przyciusku 'add'
        }
    }
    class CheckBoxListener extends ButtonListener{
        private ArrayList<String> columnNames = new ArrayList<>();

        @Override
        public void actionPerformed(ActionEvent e)
        {
            JCheckBox box = (JCheckBox) e.getSource();

            if(box.isSelected()){
                columnNames.add(box.getText());
            }
            else{
                columnNames.remove(columnNames.indexOf(box.getText()));
                /*for(int i = 0 ; i < columnNames.size(); i++){
                    if(box.getText() == columnName.in){

                    }
                }*/
            }
            System.out.println(columnNames);

        }

    }

    class CheckBoxAlwaysSelectedListener extends ButtonListener{
        @Override
        public void actionPerformed(ActionEvent e){
            JCheckBox box = (JCheckBox) e.getSource();

                box.setSelected(true);
        }
    }

    class WindowListener extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e)
        {
            try {
                model.CloseConnection();
            }
            catch (SQLException e1) {

               System.out.println(e1);
            }
            System.out.println("Connection closed");
        }
    }


    BDController(BDModel model, BDView view, BDLoginWindow loginWindow)
    {
        this.model = model;
        this.view = view;
        this.loginWindow = loginWindow;
        this.loginWindow.AddSetListener(new LoginListener());
        loginWindow.setVisible(true);
        this.view.AddWindowListener(new WindowListener());
        this.view.AddComboListener(new ComboListener());
        this.view.AddCheckBoxAlwaysSelectedListener(new CheckBoxAlwaysSelectedListener());
        this.view.AddCheckBoxListener(new CheckBoxListener());

    }

}


