import java.sql.*;
import java.util.ArrayList;

public class OracleConnection {

    private Connection con;
    private Statement stmnt;

    public OracleConnection(String User, String Password) throws SQLException, ClassNotFoundException {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection
                    ("jdbc:oracle:thin:@ora3.elka.pw.edu.pl:1521:ora3inf",User,Password);
            System.out.println("Connection established");
            stmnt = con.createStatement();
    }

    void CloseConnection() throws SQLException
    {
        con.close();
    }

    ResultSet DoQuery(String query) throws SQLException {
        return stmnt.executeQuery(query);
    }
}
