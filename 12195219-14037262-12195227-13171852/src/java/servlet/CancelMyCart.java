/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
public class CancelMyCart extends HttpServlet {

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
            out.println("<title>Servlet CancelMyCart</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Welcome to Online Shopping</h1>");
            this.entityManager = emf.createEntityManager();
            Query querycartid = this.entityManager.createNamedQuery("ShoppingCart.findByUserId");
            querycartid.setParameter("userid", request.getSession().getAttribute("uid"));

            List<ShoppingCart> cartlist = (List<ShoppingCart>) querycartid.getResultList();
            List<Product> productcart = null;
            // Creating the array list to fetch the data from the Shopping Cart and the Product
            List<List> lst = new ArrayList<List>();
            for (ShoppingCart cart : cartlist) {
                //Creating Enityt Manager

                int pId = cart.getProductId();

                // Delete from the Shopping cart
                entityManager = emf.createEntityManager();
                EntityTransaction tx = entityManager.getTransaction();
                tx.begin();
                Query query = this.entityManager.createNamedQuery("ShoppingCart.findByProductId");
                query.setParameter("pid", pId);
                ShoppingCart cartdelete = (ShoppingCart) query.getSingleResult();
                entityManager.remove(cartdelete);

                //Update the Product quantity
                Query query1 = this.entityManager.createNamedQuery("Product.findByProductId");
                query1.setParameter("pid", pId);
                Product product = (Product) query1.getSingleResult();
                product.setProductQuantity(product.getProductQuantity() + 1);
                // Merge is for updating the table
                entityManager.merge(product);
                // End here
                tx.commit();
                entityManager.close();
                // Displaying the Message and creating a link

            }
            String str = "Items are removed from your cart and Item Count Set back ";
            out.println(str + "\n\n <h2><a href=\"Servlet\"> Back to Page</a></h2");
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
