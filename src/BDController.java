import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;
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
        this.view.AddRemoveListener(new RemoveButtonListener());
        this.view.AddShowButtonListener(new ShowButtonListener());
        this.view.AddTableListener(new TableListener());
    }

    ArrayList<String> getAttributes(){
        return attributes;
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
            entity = box.getItemAt(box.getSelectedIndex());
            attributes = defaultNames;
        }
    }
    class AddButtonListener extends ButtonListener{
        @Override
        public void actionPerformed(ActionEvent e)
        {
            view.OpenAddWindow(new EnterButtonListener(), new CancelButtonListener());
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

        }

    }

    class CheckBoxAlwaysSelectedListener extends ButtonListener{
        @Override
        public void actionPerformed(ActionEvent e){
            JCheckBox box = (JCheckBox) e.getSource();

            box.setSelected(true);
        }
    }
    class EnterButtonListener extends ButtonListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(model.InsertIntoTable(view.getTableHeaders(), view.getAddWindowLabels(), view.getAddWindowValues())!=0)
            {
                view.DisplayErrorMessage(model.getError());
            }
            else
            {
                view.CloseDialogWindow();
            }
        }
    }
    class CancelButtonListener extends ButtonListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.CloseDialogWindow();
        }
    }
    class ConfirmButtonListener extends ButtonListener
    {
        //Confirm button used in removeWindow
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("removing row");
            view.CloseDialogWindow();
            //todo model remove ,refresh Jtable
        }
    }
    class ShowButtonListener extends ButtonListener{
        @Override
        public void actionPerformed(ActionEvent e){
           // System.out.println(entity);

            //System.out.println(attributes);
            model.GetSelectedTable(entity, attributes);
            try {
                view.ShowResultTable(getAttributes(),model.getResult(),entity);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
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
    class RemoveButtonListener extends ButtonListener{

        @Override
        public void actionPerformed(ActionEvent e){
            if(view.getDataTable().getSelectedRow()>0){
                System.out.println("DELETE" + view.getRowValues() + " " + view.getTableName());
                view.OpenRemoveWindow(new ConfirmButtonListener(), new CancelButtonListener());
            }
        }
    }

    class TableListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e){
            if( view.getDataTable().getSelectedRow() > 0 ){
                //view.getColumnNames();
                //view.getRowValues();
                System.out.println("single click");
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if(e.getClickCount() ==2 && !e.isConsumed() ){
                e.consume();
                System.out.println("double click");
                view.getColumnNames();
                view.getRowValues();
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

}


