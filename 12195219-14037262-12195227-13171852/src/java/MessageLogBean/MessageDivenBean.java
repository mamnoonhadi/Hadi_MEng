/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * This is Message Driven Bean class
 */
package MessageLogBean;

import java.io.IOException;
import java.util.StringTokenizer;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/*
 Created on : Apr 16, 2015, 5:27:44 AM
 Author:
 Nader    12195219
 Mamnoon  14037262
 Khaled   12195227
 Yaser    13171852
 */
// I am defining the destination and connectoion factory in the domain console
@MessageDriven(mappedName = "jms/dest", activationConfig = {
    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class MessageDivenBean implements MessageListener {

    public MessageDivenBean() {
    }

    @Override
    public void onMessage(Message message) {
        try {
            TextMessage tmsg = null;
            //casting the textmessage
            tmsg = (TextMessage) message;
            // getting the whole string and splitting it on the basis of delimeter:
            String str = tmsg.getText();
            StringTokenizer stk = new StringTokenizer(str, ":");
            String className = stk.nextToken();
            String msg = stk.nextToken();

            Logger logger;
            logger = Logger.getLogger(className);

            //
            // Create an instance of FileHandler that write log to a file called
            // app.log. Each new message will be appended at the at of the log file.
            //
            // True will append the data in the file
            FileHandler fileHandler = new FileHandler("C:\\Users\\ASUS\\Desktop\\Nader\\12195219-14037262-12195227-13171852\\LogDetails.log", Boolean.TRUE);
            logger.addHandler(fileHandler);
            logger.info(str);

            System.out.println(tmsg.getText());
        } catch (JMSException ex) {
            Logger.getLogger(MessageDivenBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MessageDivenBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(MessageDivenBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
