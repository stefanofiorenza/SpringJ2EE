package corso.spring.j2ee.mail.service;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import corso.spring.j2ee.mail.model.Order;

public class OrderManagerService {

	
	 	private MailSender mailSender;
	    private SimpleMailMessage templateMessage;

	    public void setMailSender(MailSender mailSender) {
	        this.mailSender = mailSender;
	    }

	    public void setTemplateMessage(SimpleMailMessage templateMessage) {
	        this.templateMessage = templateMessage;
	    }

	    public void placeOrder(Order order) {

	        // Do the business calculations...

	        // Call the collaborators to persist the order...

	        // Create a thread safe "copy" of the template message and customize it
	        SimpleMailMessage msg = new SimpleMailMessage(this.templateMessage);
	        msg.setTo(order.getCustomer().getEmailAddress());
	        msg.setText(
	            "Dear " + order.getCustomer().getFirstName()
	                + order.getCustomer().getLastName()
	                + ", thank you for placing order. Your order number is "
	                + order.getOrderNumber());
	        try{
	            this.mailSender.send(msg);
	        }
	        catch(MailException ex) {
	            // simply log it and go on...
	            System.err.println(ex.getMessage());            
	        }
	    }

}
