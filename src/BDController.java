import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class BDController {
    private BDModel model;
    private BDView view;
    private String entity;
    private ArrayList<String> attributes = new ArrayList<>();

    BDController(BDModel model, BDView view) {
        this.model = model;
        this.view = view;
        this.view.AddConnectListener(new LoginListener());
        this.view.OpenLoginDialog();
//        this.view.AddSetListener(new LoginListener());
        this.view.AddWindowListener(new WindowListener());
        this.view.AddComboListener(new ComboListener());
        this.view.AddCheckBoxAlwaysSelectedListener(new CheckBoxAlwaysSelectedListener());
        this.view.AddCheckBoxListener(new CheckBoxListener());
        this.view.AddAddButtonListener(new AddButtonListener());
    }

    class LoginListener extends ButtonListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(model.MakeConnection(view.getLoginData()[0], view.getLoginData()[1])!=0)
                view.DisplayErrorMessage(model.getError());
            else
            {
                view.CloseLoginDialog();
                view.OpenMainMenu();
            }
        }
    }
    //    class ShowATableListener implements ActionListener{
//            model.MakeConnection(loginWindow.GetUsername(), loginWindow.GetPassword());
//
//            if(model.TestConnection()){
//                loginWindow.setVisible(false);
//            }
////           System.out.println(model.error);
//        }
//    }
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
            attributes = defaultNames;
            System.out.print(defaultNames);
        }
    }
    class AddButtonListener extends ButtonListener{
        @Override
        public void actionPerformed(ActionEvent e)
        {
            view.OpenAddWindow();
        }
    }
    //        @Override
//        public void actionPerformed(ActionEvent e) {
//            //entity = menu.getEntity();
//            if ( entity=="Courses" || entity=="Courses_Clients")
//            {
//                if(model.SelectWholeTable(entity)!=0)
//                    //menu.DisplayErrorMessage(model.error);
//            }
//            else
//            {
//                //attributes.add(getBoxLabel(0));
//                //petla dodajaca nazwy tych boxow, ktore zostaly wybrane
//                if(model.GetSelectedTable(entity, attributes)!=0)
//                    //menu.DisplayErrorMessage(model.error);
//
//            catch (SQLException e1) {
//
//               System.out.println(e1);
//            }
//            //menu.openTable(model.result);
//
//        }
    class CheckBoxListener extends ButtonListener{

        @Override
        public void actionPerformed(ActionEvent e)
        {
            JCheckBox box = (JCheckBox) e.getSource();

            if(box.isSelected()){
                attributes.add(box.getText());
            }
            else{
                attributes.remove(attributes.indexOf(box.getText()));
            }
            System.out.println(attributes);

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
            if(model.CloseConnection()!=0)
                view.DisplayErrorMessage(model.getError());
            else
                System.out.println("Connection closed");
        }
    }


}


