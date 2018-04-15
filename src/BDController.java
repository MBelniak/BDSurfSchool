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
//        this.view.AddSetListener(new LoginListener());
        this.view.AddWindowListener(new WindowListener());

    }

}


