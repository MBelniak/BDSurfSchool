
import java.sql.*;
import java.util.ArrayList;

public class BDModel {

    private OracleConnection MyBase;
    private ResultSet result;
    private ResultSet subResult1;
    private ResultSet subResult2;
    private String error;

    BDModel()
    {
        error = "";
    }

    int MakeConnection(String User, String Password)
    {
        try {
            MyBase = new OracleConnection(User, Password);
        } catch (SQLException e) {
            error = "Blad logowania. \n" + e.getMessage();
            return e.getErrorCode();
        }
        catch (ClassNotFoundException e)
        {
            error = "Blad drivera. \n" + e.getMessage();
            return 1;
        }
        return 0;
    }

    int CloseConnection()
    {
        try {
            MyBase.CloseConnection();
        } catch (SQLException e) {
            error = e.getMessage();
            return e.getErrorCode();
        }
        return 0;
    }

    int GetSelectedTable(String Entity, ArrayList<String> a)
    {
        String query = "SELECT " + a.get(0);
        for(int i=1;i<a.size();i++)
        {
            query += ", " + a.get(i);
        }
        query+= " FROM " + Entity;
        try {
            result = MyBase.DoQuery(query);
        }
        catch (SQLException e) {
            error = e.getMessage();
            return e.getErrorCode();
        }
        return 0;
    }

    int InsertIntoTable(String Entity, ArrayList<String> Columns, ArrayList<String> Values)
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
            else
                error = e.getMessage();
            return e.getErrorCode();
        }
        catch (SQLException e) {
            error = e.getMessage();
            return e.getErrorCode();
        }
        return 0;
    }

    int DeleteFromTable(String Entity, ArrayList<String> Columns, ArrayList<String> Values)
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
            return e.getErrorCode();
        }
        return 0;
    }

    int SelectWholeRecord(String Entity, ArrayList<String> identifierName, ArrayList<String> identifier)
    {
        String query = "SELECT * FROM " + Entity + " WHERE ";
        query += identifierName.get(0) + " = " + identifier.get(0);
        for(int i=1; i<identifier.size();i++)
        {
            query+= " AND " + identifierName.get(i) + " = " + identifier.get(i);
        }
        try {
            result = MyBase.DoQuery(query);
        }
        catch (SQLException e) {
            error = e.getMessage();
            return e.getErrorCode();
        }
        return 0;
    }

    int SelectWholeTable(String Entity)
    {
        String query = "SELECT * FROM " + Entity;
        try {
            result = MyBase.DoQuery(query);
        }
        catch (SQLException e) {
            error = e.getMessage();
            return e.getErrorCode();
        }
        return 0;
    }

    int GetMoreEmployeeInfo(String identifier)
    {
        String query1 = "SELECT COURSES.BEGINNING_DATE, COURSES.END_DATE " +
                    "FROM EMPLOYEES INNER JOIN COURSES " +
                    "ON PESEL = COURSES.EMPLOYEE_PESEL " +
                    "WHERE PESEL = " + identifier;
        String query2 = "SELECT EMPLOYEES.PESEL FROM EMPLOYEES A " +
                    "INNER JOIN EMPLOYEES " +
                    "ON A.PESEL = EMPLOYEES.SUPERVISOR_PESEL " +
                    "WHERE A.PESEL = " + identifier;
        try {
            subResult1 = MyBase.DoQuery(query1);
            subResult2 = MyBase.DoQuery(query2);
        }
        catch (SQLException e) {
            error = e.getMessage();
            return e.getErrorCode();
        }
        return 0;
    }

    int GetMoreSchoolInfo(String identifier)
    {
        String query1 = "SELECT EMPLOYEES.FIRSTNAME, EMPLOYEES.SECONDNAME " +
                "FROM SCHOOLS INNER JOIN EMPLOYEES " +
                "ON SCHOOLS.SCHOOL_ID = EMPLOYEES.SCHOOL_ID " +
                "WHERE SCHOOLS.SCHOOL_ID = " + identifier;
        String query2 = "SELECT EQUIPMENT.EQUIPMENT_ID " +
                "FROM SCHOOLS INNER JOIN EQUIPMENT " +
                "ON SCHOOLS.SCHOOL_ID = EQUIPMENT.SCHOOL_ID " +
                "WHERE SCHOOLS.SCHOOL_ID = " + identifier;
        try {
            subResult1 = MyBase.DoQuery(query1);
            subResult2 = MyBase.DoQuery(query2);

        }
        catch (SQLException e) {
            error = e.getMessage();
            return e.getErrorCode();
        }
        return 0;
    }
    int GetMoreEquipmentInfo(String identifier)
    {
        String query = "SELECT * FROM EQUIPMENT " +
                "WHERE ID = " + identifier;
        try {
            subResult1 = MyBase.DoQuery(query);
        }
        catch (SQLException e) {
            error = e.getMessage();
            return e.getErrorCode();
        }
        return 0;
    }
    int GetMoreClientInfo(String identifier)
    {
        String query1 = "SELECT EQUIPMENT.ID " +
                "FROM CLIENTS INNER JOIN EQUIPMENT " +
                "ON CLIENTS.CLIENT_PESEL = EQUIPMENT.CLIENT_PESEL " +
                "WHERE CLIENTS.CLIENT_PESEL = " + identifier;
        String query2 = "SELECT COURSES_CLIENTS.* " +
                "FROM CLIENTS INNER JOIN COURSES_CLIENTS " +
                "ON CLIENTS.CLIENT_PESEL = COURSES_CLIENTS.CLIENT_PESEL " +
                "WHERE CLIENTS.CLIENT_PESEL = " + identifier;
        try {
            subResult1 = MyBase.DoQuery(query1);
            subResult2 = MyBase.DoQuery(query2);
        }
        catch (SQLException e) {
            error = e.getMessage();
            return e.getErrorCode();
        }
        return 0;
    }
    int GetMoreCourseClientInfo(String[] identifier)
    {
        String query = "SELECT * FROM COURSES_CLIENTS " +
                "WHERE COURSE_BEGINNING_DATE = " + identifier[0] + " AND " +
                "COURSE_END_DATE = " + identifier[1] + " AND " +
                "COURSE_EMPLOYEE_PESEL = " + identifier[2] + " AND " +
                "CLIENT_PESEL = " + identifier[3];
        try {
            subResult1 = MyBase.DoQuery(query);
        }
        catch (SQLException e) {
            error = e.getMessage();
            return e.getErrorCode();
        }
        return 0;
    }
    int GetMoreCourseInfo(String[] identifier)
    {
        String query1 = "SELECT COURSES_CLIENTS.CLIENT_PESEL FROM COURSES " +
                "LEFT JOIN COURSES_CLIENTS " +
                "ON COURSES.BEGINNING_DATE = COURSES_CLIENTS.COURSE_BEGINNING_DATE AND " +
                " COURSES.END_DATE = COURSES_CLIENTS.COURSE_END_DATE AND " +
                " COURSES.EMPLOYEE_PESEL = COURSES_CLIENTS.COURSE_EMPLOYEE_PESEL " +
                "WHERE COURSE_BEGINNING_DATE = " + identifier[0] + " AND " +
                "COURSE_END_DATE = " + identifier[1] + " AND " +
                "COURSE_EMPLOYEE_PESEL = " + identifier[2];
        try {
            subResult1 = MyBase.DoQuery(query1);
        }
        catch (SQLException e) {
            error = e.getMessage();
            return e.getErrorCode();
        }
        return 0;
    }

    public ResultSet getResult() {
        return result;
    }

    public ResultSet getSubResult1() {
        return subResult1;
    }

    public ResultSet getSubResult2() {
        return subResult2;
    }

    public String getError() {
        return error;
    }
}
