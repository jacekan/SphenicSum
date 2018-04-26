package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Class to service the database.
 * 
 */
public class CalculatorDatabase 
{
    /**
     * Connection to the database.
     */
    private Connection connection;
    
    /**
     * Name of the database driver.
     */
    private String dbDriver;
    
    /**
     * Database URL.
     */
    private String dbUrl;
    
    /**
     * Username with access to the database.
     */
    private String dbUser;
    
    /**
     * User password with access to the database.
     */
    private String dbPassword;
            
    public CalculatorDatabase(String dbDriver, String dbUrl, String dbUser, String dbPassword)
    {
        this.dbDriver = dbDriver;
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }
    
    /**
     * Opens the connection to the database.
     * 
     * @throws ClassNotFoundException class not found exception
     * @throws SQLException SQL exception
     */
    public void openConnection() throws ClassNotFoundException, SQLException
    {
        Class.forName(dbDriver);

        connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }
    
    /**
     * Closes the connection to the database.
     * 
     * @throws SQLException SQL exception
     */
    public void closeConnection() throws SQLException
    {
        connection.close();
    }
    
    /**
     * Creates a table to store calculations in the database.
     * 
     * @throws SQLException SQL exception
     */
    public void create() throws SQLException
    {
        final String QUERY_CREATE = "CREATE TABLE calculation"
                                  + "("
                                  + "id INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
                                  + "operation VARCHAR(10) NOT NULL,"
                                  + "number_of_sphenic_numbers INT NOT NULL,"
                                  + "result INT NOT NULL"
                                  + ")";
        
        Statement statement = connection.createStatement();
        
        statement.executeUpdate(QUERY_CREATE);
    }
    
    /**
     * Inserts a new calculation into the database.
     * 
     * @param operation name of the mathematical operation
     * @param numberOfSphenicNumbers number of first sphenic numbers
     * @param result result of calculations
     * @throws SQLException SQL exception
     */
    public void insert(String operation, int numberOfSphenicNumbers, int result) throws SQLException
    {
        final String QUERY_INSERT = "INSERT INTO calculation(operation, number_of_sphenic_numbers, result) VALUES('" + operation + "', " + numberOfSphenicNumbers + ", " + result + ")";
        
        Statement statement = connection.createStatement();
        
        statement.executeUpdate(QUERY_INSERT);
    }
    
    /**
     * Returns the list of calculations stored in the database.
     * 
     * @return list of calculations
     * @throws SQLException SQL exception
     */
    public ArrayList<String> select() throws SQLException
    {
        ArrayList<String> calculations = new ArrayList();
        
        final String QUERY_SELECT = "SELECT * FROM calculation";
        
        Statement statement = connection.createStatement();
        
        statement.executeQuery(QUERY_SELECT);
        
        ResultSet resultSet = statement.getResultSet();
        
        while(resultSet.next()) 
        {
            String operation = resultSet.getString("operation");
            int numberOfSphenicNumbers = resultSet.getInt("number_of_sphenic_numbers");
            int result = resultSet.getInt("result");
            
            calculations.add(operation + " " + numberOfSphenicNumbers + " = " + result);
        }
        
        return calculations;
    }
}
