/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * This is a Add Product Servlet, where we are adding the Product entered by admin
 */
package servlet;

import EntityClass.Product;
import UserBean.UserBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
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
public class AddProduct extends HttpServlet {

    // Defining the Destination, queue, EJB, Bean, Persistance, EMF and the entity manager
    @EJB
    UserBean UBean;
    @PersistenceUnit
    EntityManagerFactory emf;
    EntityManager entityManager;
    @Resource(mappedName = "jms/dest")
    private Queue dest;
    @Resource(mappedName = "jms/queue")
    private ConnectionFactory queue;

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
            out.println("<title>Servlet AddProduct</title>");
            out.println("</head>");
            out.println("<body>");

            // Creating the new class of the Product for adding new Product
            Product addprt = new Product();
            //Getting the Product name, Price, Category, Author name and quantity from the jsp page
            addprt.setProductName(request.getParameter("pname"));
            addprt.setProductPrice(request.getParameter("pprice"));
            addprt.setProductCode(request.getParameter("pcode"));
            addprt.setProductImage("Dummy.png");
            addprt.setProductQuantity(Integer.parseInt(request.getParameter("pquantity")));
            // It is for current datetimestamo
            java.util.Date date = new java.util.Date();
            addprt.setCreatedOn(date);
            addprt.setModifiedOn(date);
            //Creating Enityt Manager
            this.entityManager = emf.createEntityManager();
            //Creating Entity Transaction
            EntityTransaction tx = entityManager.getTransaction();
            tx.begin();
            //Persist is using for insert new record
            entityManager.persist(addprt);
            tx.commit();
            entityManager.close();
            String str = "Product Name: " + request.getParameter("pname") + " is Added";
            
            //Calling this method for mainting the logs
            try {
                sendJMSMessageToDest(str);
            } catch (JMSException ex) {
                Logger.getLogger(AddProduct.class.getName()).log(Level.SEVERE, null, ex);
            }
            out.println("<h1>Welcome to Online Shopping</h1>");
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
    // Creating the log when admin will add new product in the application

    private Message createJMSMessageForjmsDest(Session session, Object messageData) throws JMSException {
        // TODO create and populate message to send
        TextMessage tm = session.createTextMessage();
        tm.setText(messageData.toString());
        return tm;
    }

    // Sending the message to the log file after adding new product
    private void sendJMSMessageToDest(Object messageData) throws JMSException {
        Connection connection = null;
        Session session = null;
        try {
            connection = queue.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(dest);
            messageProducer.send(createJMSMessageForjmsDest(session, messageData));
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (JMSException e) {
                    Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Cannot close session", e);
                }
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}
