/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * This is Search Servlet which find the Product by its Id or its name
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
import javax.persistence.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 Created on : Apr 18, 2015, 5:27:44 AM
 Author:
 Nader    12195219
 Mamnoon  14037262
 Khaled   12195227
 Yaser    13171852
 */
@WebServlet(name = "Search", urlPatterns = {"/Search"})
public class Search extends HttpServlet {
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
            out.println("<title>Servlet Search</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("Name:" + request.getParameter("searchbyname") + "ID: " + request.getParameter("searchbyID"));
            // If Search by ID is not passed, only Name is passed
            if (!request.getParameter("searchbyname").equals("") && request.getParameter("searchbyID").equals("")) {
                this.entityManager = emf.createEntityManager();
                Query query = this.entityManager.createNamedQuery("Product.findByProductName");
                query.setParameter("pname", "%" + request.getParameter("searchbyname") + "%");
                // Creating the Product List
                List<Product> product = (List<Product>) query.getResultList();
                request.setAttribute("listProduct", product);
            }
            // If Search by name is not passed, only ID is passed
            if (request.getParameter("searchbyname").equals("") && !request.getParameter("searchbyID").equals("")) {
                this.entityManager = emf.createEntityManager();
                Query query = this.entityManager.createNamedQuery("Product.findByProductCode");
                query.setParameter("pid", request.getParameter("searchbyID"));
                // Creating the Product List
                List<Product> product = (List<Product>) query.getResultList();
                request.setAttribute("listProduct", product);
            }

            // If user enter both Parameters
            if (!request.getParameter("searchbyname").equals("") && !request.getParameter("searchbyID").equals("")) {
                this.entityManager = emf.createEntityManager();
                Query query = this.entityManager.createNamedQuery("Product.findByProductNameandID");
                query.setParameter("pid", request.getParameter("searchbyID"));
                query.setParameter("pname", request.getParameter("searchbyname"));
                // Creating the Product List
                List<Product> product = (List<Product>) query.getResultList();
                request.setAttribute("listProduct", product);
            }
            // If both the Paramter is blank and user press search button
            if (request.getParameter("searchbyname").equals("") && request.getParameter("searchbyID").equals("")) {
                this.entityManager = emf.createEntityManager();
                Query query = this.entityManager.createNamedQuery("Product.findAll");
                List<Product> product = (List<Product>) query.getResultList();
                request.setAttribute("listProduct", product);
            }

            // Forwarding the data to the jsp page
            RequestDispatcher rd = getServletContext()
                    .getRequestDispatcher("/Search.jsp");
            rd.forward(request, response);

            out.println("</body>");
            out.println("</html>");
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
