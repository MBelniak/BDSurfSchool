import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;

public class BDController {
    private BDModel model;
    private BDView view;
    private String entity;
    private ArrayList<String> attributes;
    BDController(BDModel model, BDView view)
    {
        this.model = model;
        this.view = view;
        this.view.AddConnectListener(new ConnectButtonListener());
        this.view.AddWindowListener(new WindowListener());

    }
    class ConnectButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
           // if(model.MakeConnection(view.GetUsername(), view.GetPassword())!=0)
               // view.DisplayErrorMessage(model.getError());
            //else{ widok.zamknijekranlogowania();
            //widok.otworzmenu();}

        }
    }
    class ShowATableListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            //entity = menu.getEntity();
            if ( entity=="Courses" || entity=="Courses_Clients")
            {
                if(model.SelectWholeTable(entity)!=0)
                    //menu.DisplayErrorMessage(model.error);
            }
            else
            {
                //attributes.add(getBoxLabel(0));
                //petla dodajaca nazwy tych boxow, ktore zostaly wybrane
                if(model.GetSelectedTable(entity, attributes)!=0)
                    //menu.DisplayErrorMessage(model.error);
            }
            //menu.openTable(model.result);

        }
    }

    class WindowListener extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e)
        {
            if(model.CloseConnection()!=0)
                System.out.println(model.getError());
            else
                System.out.println("Connection closed");
        }
    }


}


