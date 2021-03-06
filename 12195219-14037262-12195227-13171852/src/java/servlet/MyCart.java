/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * This Servlet is for displaying the My cart
 */
package servlet;

import EntityClass.Product;
import EntityClass.ShoppingCart;
import UserBean.UserBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
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
 Created on : Apr 18, 2015, 5:27:44 AM
 Author:
 Nader    12195219
 Mamnoon  14037262
 Khaled   12195227
 Yaser    13171852
 */
public class MyCart extends HttpServlet {
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
            out.println("<title>Servlet MyCart</title>");
            out.println("</head>");
            out.println("<body>");

            //  Query to find the Product for particulars Users
            this.entityManager = emf.createEntityManager();
            Query querycartid = this.entityManager.createNamedQuery("ShoppingCart.findByUserId");

            querycartid.setParameter("userid", request.getSession().getAttribute("uid"));
            // Getting the list from the Shopping Cart , Creating new EMF and getting the result in the list
            List<ShoppingCart> cartlist = (List<ShoppingCart>) querycartid.getResultList();
            List<Product> productcart = null;
            // Creating the array list to fetch the data from the Shopping Cart and the Product
            List<List> lst = new ArrayList<List>();
            for (ShoppingCart cart : cartlist) {
                //Creating Enityt Manager
                this.entityManager = emf.createEntityManager();
                Query cartpdt = this.entityManager.createNamedQuery("Product.findByProductId");
                cartpdt.setParameter("pid", cart.getProductId());
                productcart = (List<Product>) cartpdt.getResultList();
                lst.add(productcart);

            }
            // Forwarding the data to the MyCart jsp
            request.setAttribute("listProduct", lst);
            RequestDispatcher rd = getServletContext()
                    .getRequestDispatcher("/MyCart.jsp");
            rd.forward(request, response);

            //  End here
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
