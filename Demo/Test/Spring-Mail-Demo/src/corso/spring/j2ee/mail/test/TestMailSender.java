package corso.spring.j2ee.mail.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import corso.spring.j2ee.mail.model.Customer;
import corso.spring.j2ee.mail.model.Order;
import corso.spring.j2ee.mail.service.OrderManagerService;

public class TestMailSender {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ApplicationContext context =new ClassPathXmlApplicationContext("corso/spring/j2ee/mail/config/beans-email.xml");
		OrderManagerService servizio = context.getBean("orderManager",OrderManagerService.class);
		
		Customer customer = new Customer();
		customer.setFirstName("Stefano");
		customer.setLastName("Fiorenza");
		customer.setEmailAddress("corsi.j2ee.stefano@gmail.com");
		//password per accedervi: corsostefano
		
		Order order = new Order();
		order.setCustomer(customer);
		order.setOrderNumber(123L);
		servizio.placeOrder(order);
	}

}
