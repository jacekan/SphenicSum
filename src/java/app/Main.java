package app;

import database.CalculatorDatabase;
import java.sql.SQLException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Class for receiving notification events about ServletContext lifecycle changes.
 * 
 */
public class Main implements ServletContextListener
{
    /**
     * Class object to service the database
     */
    CalculatorDatabase calculatorDatabase;
    
    /**
     * Receives notification that the web application initialization process is starting.
     * 
     * @param servletContextEvent this is the event class for notifications about changes to the servlet context of a web application.
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) 
    {
        ServletContext context = servletContextEvent.getServletContext();
        
        String dbDriver = context.getInitParameter("dbDriver");
        String dbUrl = context.getInitParameter("dbUrl");
        String dbUser = context.getInitParameter("dbUser");
        String dbPassword = context.getInitParameter("dbPassword");
        
        calculatorDatabase = new CalculatorDatabase(dbDriver, dbUrl, dbUser, dbPassword);
        
        try 
        {
            calculatorDatabase.openConnection();
            
            calculatorDatabase.create();
        } 
        catch(ClassNotFoundException e) 
        {
            System.err.println("ClassNotFound exception: " + e.getMessage());
        } 
        catch (SQLException e) 
        {
            System.err.println("SQL exception: " + e.getMessage());
        }
        
        context.setAttribute("calculator_database", calculatorDatabase);
    }

    /**
     * Receives notification that the ServletContext is about to be shut down.
     * 
     * @param servletContextEvent this is the event class for notifications about changes to the servlet context of a web application.
     */
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) 
    {
        try 
        {
            calculatorDatabase.closeConnection();
        } 
        catch(SQLException e) 
        {
            System.err.println("SQL exception: " + e.getMessage());
        }
    }
}
