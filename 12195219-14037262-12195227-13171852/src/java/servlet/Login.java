/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * This Servlet is validating the user name and the password
 */
package servlet;

import EntityClass.Users;
import UserBean.UserBean;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
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
public class Login extends HttpServlet {
    // Defining the Destination, queue, EJB, Bean, Persistance, EMF and the entity manager

    @EJB
    UserBean UBean;
    @PersistenceUnit
    EntityManagerFactory emf;
    EntityManager entityManager;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Login</title>");
            out.println("</head>");
            out.println("<body>");
            //Creating Enityt Manager
            this.entityManager = emf.createEntityManager();
            Query query = this.entityManager.createNamedQuery("findUserByUserIdAndPassword");
            // Setting the Paramter
            query.setParameter("username", request.getParameter("UName"));
            query.setParameter("password", request.getParameter("pwd"));
            Users uid = (Users) query.getSingleResult();
            request.setAttribute("urole", uid);
            // Forwarding the data to the admin page
            if (null != uid) {
                RequestDispatcher rd = getServletContext()
                        .getRequestDispatcher("/Admin");
                rd.forward(request, response);
            }
            entityManager.close();
            out.println("</body>");
            out.println("</html>");
            // Exception handling
        } catch (javax.persistence.NoResultException ex) {
            // Forwarding the data to the Error page
            RequestDispatcher rd = getServletContext()
                    .getRequestDispatcher("/Errorpage.jsp");
            rd.forward(request, response);

        } catch (Exception ex) {
            ex.printStackTrace();
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
