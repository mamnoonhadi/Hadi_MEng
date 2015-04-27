/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * This Servlet is for mainting the comments done by customers
 */
package servlet;

import EntityClass.Product;
import UserBean.UserBean;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
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
public class CommentServlet extends HttpServlet {
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
            out.println("<title>Servlet CommentServlet</title>");
            out.println("</head>");
            out.println("<body>");
            // Getting the Product ID, Comments and User Id from the sessions
            String pId = request.getParameter("prtid");
            String cmt = request.getParameter("comments");
            String uName = request.getParameter("puid");
            //Creating Enityt Manager
            entityManager = emf.createEntityManager();
            EntityTransaction tx = entityManager.getTransaction();
            tx.begin();
            Query query = this.entityManager.createNamedQuery("Product.findByProductId");
            query.setParameter("pid", Integer.parseInt(pId));
            Product pdtupdate = (Product) query.getSingleResult();
            pdtupdate.setComment(cmt);
            // merge is for the update 
            entityManager.merge(pdtupdate);
            // End here
            tx.commit();
            entityManager.close();
            String str = "Comment : <span style='font-size: 18px;color: #969547;'>" + cmt + "</span>\n\n is Added ";
            // Printing the message and creating a ling
            out.println("<h1>Welcome to Online Shopping</h1>");
            out.println(str + "\n\n <h2><a href=\"Servlet\"> Back to Page</a></h2");
            out.println("</body>");
            out.println("</html>");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            out.close();
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
