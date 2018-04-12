import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class BDController {
    private BDModel model;
    private BDView view;
    class ButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            model.MakeConnection(view.GetUsername(), view.GetPassword());
        }
    }
    class WindowListener extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e)
        {
            try {
                model.CloseConnection();
                System.out.println("Connection closed");
            }
            catch(Exception exc)
            {
                System.out.println(exc);
            }
        }
    }
    BDController(BDModel model, BDView view)
    {
        this.model = model;
        this.view = view;
        this.view.AddSetListener(new ButtonListener());
        this.view.AddWindowListener(new WindowListener());

    }

}


