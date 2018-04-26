package app;

import database.CalculatorDatabase;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Main class of the servlet presenting history calculations.
 * 
 */
public class History extends HttpServlet 
{
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        
        try(PrintWriter output = response.getWriter()) 
        {
            output.println("<html>");
            output.println("<body>");
            output.println("<h1>The history of calculations</h1>");

            Cookie[] cookies = request.getCookies();

            String lastCalculation = "Empty!";

            if(cookies != null)
            {
                for(Cookie cookie : cookies) 
                {
                    if(cookie.getName().equals("last_calculation")) 
                    {
                        lastCalculation = cookie.getValue();

                        break;
                    }
                }
            }
            
            output.println("<h2>Last calculation:</h2>");
            output.println("<h4>" + lastCalculation + "</h4>");
            output.println("<h2>Older calculations:</h2>");
            
            CalculatorDatabase calculatorDatabase = (CalculatorDatabase)this.getServletContext().getAttribute("calculator_database");

            ArrayList<String> calculations = null;
            
            try 
            {
                calculations = calculatorDatabase.select();
                         
                if(calculations.isEmpty())
                {
                    output.println("<h4>Empty!</h4>");
                }
                else
                {
                    for(int i = calculations.size() - 1; i >= 0; i--)
                    {
                        output.println("<h4>" + calculations.get(i) + "</h4>");
                    }
                }
            } 
            catch(SQLException e) 
            {
                response.sendError(response.SC_BAD_REQUEST, "SQL exception: " + e.getMessage()); 
            }
                
            output.println("</body>");
            output.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}