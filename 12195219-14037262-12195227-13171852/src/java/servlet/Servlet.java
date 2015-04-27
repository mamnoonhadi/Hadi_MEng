/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * This is the main Page or home Servlet, When you run the project the page will call and it will redirect to index jsp
 */
package servlet;

import EntityClass.Product;
import UserBean.UserBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 Created on : Apr 16, 2015, 5:27:44 AM
 Author:
 Nader    12195219
 Mamnoon  14037262
 Khaled   12195227
 Yaser    13171852
 */
@WebServlet(name = "Servlet", urlPatterns = {"/Servlet"})
public class Servlet extends HttpServlet {
    // Defining the Destination, queue, EJB, Bean, Persistance, EMF and the entity manager

    @EJB
    UserBean UBean;
    @PersistenceUnit
    EntityManagerFactory emf;
    EntityManager entityManager;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Servlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Welcome to Online Shopping<h1>");

            // creating the Product list by using entity class, Creating new EMF and getting the result in the list
            List<Product> product = (List<Product>) emf.createEntityManager().createNamedQuery("Product.findAll").getResultList();
            request.setAttribute("listProduct", product);
            // Getting the value from the session and validating it
            if (request.getSession().getAttribute("username") != null) {
                // if session is not null then confirming the role and based on the role redirecting the page
                if (request.getSession().getAttribute("urole").equals("Admin")) {
                    // forwarding the data to the admin jsp page
                    RequestDispatcher rd = request.getRequestDispatcher("/Admin.jsp");
                    System.out.println(rd != null);
                    rd.forward(request, response);

                } else {
                    // when the role is customer, forwarding the data to the Customer jsp page
                    RequestDispatcher rd = getServletContext()
                            .getRequestDispatcher("/Customer.jsp");
                    rd.forward(request, response);
                }
            } else {
                // When the session is null, forwarding the data to the admin jsp page
                RequestDispatcher rd = getServletContext()
                        .getRequestDispatcher("/index.jsp");
                rd.forward(request, response);
            }

            out.println("</body>");
            out.println("</html>");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            out.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
