import java.sql.SQLException;

public class BDModel {
    private OracleConnection MyBase;
    BDModel()
    {
    }
    void MakeConnection(String User, String Password)
    {
        MyBase = new OracleConnection(User, Password);
    }
    void CloseConnection() throws SQLException {
        MyBase.CloseConnection();
    }
}
