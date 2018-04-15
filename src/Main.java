import java.sql.SQLException;
import java.util.*;

public class Main {
    public static void main(String args[]) throws SQLException {

        BDModel Model = new BDModel();
        BDView View = new BDView();
        BDLoginWindow LoginWindow = View.createLoginDialog();
        BDController Controller = new BDController(Model, View, LoginWindow);
        View.setVisible(true);

    }
}
