import java.sql.SQLException;
import java.util.*;

public class Main {
    public static void main(String args[]) throws SQLException {
        BDModel Model = new BDModel();
        BDView View = new BDView();
        BDController Controller = new BDController(Model, View);
        View.setVisible(true);


    }
}
