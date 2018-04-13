import java.sql.BatchUpdateException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BDModel {

    private OracleConnection MyBase;
    public ResultSet result;
    public String error;

    BDModel()
    {
        error = "";
    }

    void MakeConnection(String User, String Password)
    {

        MyBase = new OracleConnection(User, Password);

    }

    void CloseConnection() throws SQLException
    {
        MyBase.CloseConnection();
    }

    void CreateSelectedTable(String Entity, ArrayList<String> Columns)
    {
        String query = "SELECT " + Columns.get(0);
        for(int i=1; i<Columns.size(); i++) {
            query += ", " + Columns.get(i);
        }
        query += " FROM " + Entity;
        try {
            result = MyBase.DoQuery(query);
        }
        catch (SQLException e) {
            error = e.getMessage();
        }
    }

    void InsertIntoTable(String Entity, ArrayList<String> Columns, ArrayList<String> Values)
    {
        String query = "INSERT INTO " + Entity + "(" + Columns.get(0);
        for (int i=1; i<Columns.size(); i++)
        {
            query += ", " + Columns.get(i);
        }
        query += ") VALUES (" + "'" + Values.get(0) + "'";
        for (int i=1; i<Values.size(); i++)
        {
            query += ", " + "'" + Values.get(i) + "'";
        }
        query += ")";
        try {
            MyBase.DoQuery(query);
        }
        catch (java.sql.SQLIntegrityConstraintViolationException e)
        {
            if(e.getErrorCode()==1)
                error = "W bazie jest juz wpis z taka wartoscia pola unikatowego!";
        }
        catch (SQLException e) {
            error = e.getMessage();
        }
    }

    void DeleteFromTable(String Entity, ArrayList<String> Columns, ArrayList<String> Values)
    {
        String query = "DELETE FROM  " + Entity + " WHERE ";
        query += Columns.get(0) + " = " + "'" + Values.get(0) + "'";
        for (int i=1; i<Columns.size() && i<Values.size(); i++)
        {
            query += " AND " + Columns.get(i) + " = " + "'" + Values.get(i) + "'";
        }
        try {
            MyBase.DoQuery(query);
        }
        catch (SQLException e) {
            error = e.getMessage();
        }
    }
}
