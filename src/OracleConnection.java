import java.sql.*;
public class OracleConnection {
    private Connection con;
    public OracleConnection(String User, String Password)
    {
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection
                    ("jdbc:oracle:thin:@ora3.elka.pw.edu.pl:1521:ora3inf",User,Password);
            System.out.println("Connection established");
        }
        catch(Exception e)
        { System.out.println(e);}
    }
    void CloseConnection() throws SQLException
    { con.close();}
}
