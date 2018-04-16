import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.print("Tu będzie co innego");
            //model.MakeConnection(view.GetDialogWindow().GetUsername(), view.GetDialogWindow().GetPassword());

//           System.out.println(model.error);
        }

}
