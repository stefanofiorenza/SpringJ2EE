package apps.stockexchange.borsa.main;

	import lombok.extern.slf4j.Slf4j;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Slf4j
public class BorsaMain {

	public static void main(String[] args) {

		ApplicationContext context =new ClassPathXmlApplicationContext("se-borsa-main.xml");
	

	}
	
	

}
