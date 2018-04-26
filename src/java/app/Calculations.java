package app;

import database.CalculatorDatabase;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CalculatorException;
import model.CalculatorLogic;

/**
 * Main class of the servlet responsible for the calculation.
 * 
 */
public class Calculations extends HttpServlet 
{
    /**
    * Model class used for calculations
    */
    private CalculatorLogic calculatorLogic;
    
    public Calculations()
    {
        calculatorLogic = new CalculatorLogic();
    }
    
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
    {
        response.setContentType("text/html; charset=ISO-8859-2");
        
        PrintWriter output = response.getWriter();
        
        String numberOfSphenicNumbersText = request.getParameter("number_of_sphenic_numbers");
        
        if(numberOfSphenicNumbersText.length() == 0)
        {
            response.sendError(response.SC_BAD_REQUEST, "You must give parameter!"); 
        }
        else
        {
            try  
            {  
                int numberOfSphenicNumbers = Integer.parseInt(numberOfSphenicNumbersText);  
                
                checkCorrectNumberOfSphenicNumbers(numberOfSphenicNumbers);
                
                calculatorLogic.calculateSum(numberOfSphenicNumbers);
                
                int result = calculatorLogic.getResult();
                
                output.println("<html>");
                output.println("<body>");
                output.println("<h1>Sum of " + numberOfSphenicNumbers + " first sphenic numbers: " + result + "</h1>");
                output.println("</body>");
                output.println("</html>");
                
                CalculatorDatabase calculatorDatabase = (CalculatorDatabase)this.getServletContext().getAttribute("calculator_database");
                
                try 
                {
                    calculatorDatabase.insert("sum", numberOfSphenicNumbers, result);
                } 
                catch(SQLException e) 
                {
                    response.sendError(response.SC_BAD_REQUEST, "SQL exception: " + e.getMessage()); 
                }
                        
                saveCookie(response, calculatorLogic.lastCalculation());
            }  
            catch(NumberFormatException e)  
            {  
                response.sendError(response.SC_BAD_REQUEST, "The parameter must be an integer!"); 
            }  
            catch(CalculatorException e) 
            {
                 response.sendError(response.SC_BAD_REQUEST, e.getMessage());
            }  
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
    {
        response.setContentType("text/html; charset=ISO-8859-2");
        
        PrintWriter output = response.getWriter();
        
        String numberOfSphenicNumbersText = request.getParameter("number_of_sphenic_numbers");
        
        if(numberOfSphenicNumbersText.length() == 0)
        {
            response.sendError(response.SC_BAD_REQUEST, "You must give parameter!"); 
        }
        else
        {
            try  
            {  
                int numberOfSphenicNumbers = Integer.parseInt(numberOfSphenicNumbersText);  
                
                checkCorrectNumberOfSphenicNumbers(numberOfSphenicNumbers);
                
                calculatorLogic.calculateSum(numberOfSphenicNumbers);
                
                int result = calculatorLogic.getResult();
                
                output.println("<html>");
                output.println("<body>");
                output.println("<h1>Sum of " + numberOfSphenicNumbers + " first sphenic numbers: " + result + "</h1>");
                output.println("</body>");
                output.println("</html>");
                
                CalculatorDatabase calculatorDatabase = (CalculatorDatabase)this.getServletContext().getAttribute("calculator_database");
                
                try 
                {
                    calculatorDatabase.insert("sum", numberOfSphenicNumbers, result);
                } 
                catch(SQLException e) 
                {
                    response.sendError(response.SC_BAD_REQUEST, "SQL exception: " + e.getMessage()); 
                }
                        
                saveCookie(response, calculatorLogic.lastCalculation());
            }  
            catch(NumberFormatException e)  
            {  
                response.sendError(response.SC_BAD_REQUEST, "The parameter must be an integer!"); 
            }  
            catch(CalculatorException e) 
            {
                 response.sendError(response.SC_BAD_REQUEST, e.getMessage());
            }  
        }
    }
    
    /**
     * Sets cookies that store the last calculation.
     * 
     * @param response servlet response.
     * @param lastCalculation the last calculations that were performed.
     */
    private void saveCookie(HttpServletResponse response, String lastCalculation)
    {
        Cookie cookie = new Cookie("last_calculation", lastCalculation);
        
        response.addCookie(cookie);
    }
    
    /**
    * Checks if the number is correct.
    * 
    * @param numberOfSphenicNumbers number of first sphenic numbers.
    * 
    * @exception CalculatorException when parameter is less than zero.
    */
    private void checkCorrectNumberOfSphenicNumbers(int numberOfSphenicNumbers) throws CalculatorException
    {
        if(numberOfSphenicNumbers < 0)
        {
            throw new CalculatorException("Number less than zero!");
        }
    }
}
