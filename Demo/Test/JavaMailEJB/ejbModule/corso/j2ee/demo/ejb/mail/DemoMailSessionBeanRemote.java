package corso.j2ee.demo.ejb.mail;

import javax.ejb.Remote;

@Remote
public interface DemoMailSessionBeanRemote {

	public void inviaEmai ();
}
