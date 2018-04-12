public class BDModel {
    private OracleConnection Connection;
    BDModel()
    {
    }
    void MakeConnection(String User, String Password)
    {
        Connection = new OracleConnection(User, Password);
    }
    void CloseConnection()
    {

    }
}
