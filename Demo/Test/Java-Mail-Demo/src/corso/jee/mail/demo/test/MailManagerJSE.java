package corso.jee.mail.demo.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailManagerJSE {

	
	private static final String SMTP_SERVER="smtp.gmail.com";
	private static final String MAIL_FROM="corsi.stefano74@gmail.com";
	private static final String MAIL_TO="corsi.stefano74@gmail.com";
	private static final String MAIL_TO_CC="";	
	private static final String TESTO_EMAIL="un testo di prova";
	private static final String OGGETTO_EMAIL="oggetto di prova";
	private static final String USER_NAME="corsi.stefano74@gmail.com";
	private static final String PASSWORD="corsostefano";
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		send(SMTP_SERVER,MAIL_FROM,MAIL_TO,OGGETTO_EMAIL,TESTO_EMAIL);
		//receiveImap(USER_NAME,PASSWORD);
		receive(USER_NAME,PASSWORD);
	}
	
	
	public static void receiveImap(String username,String password){
		
		Properties props = System.getProperties();
		props.setProperty("mail.store.protocol", "imaps");
		try {
		  Session session = Session.getDefaultInstance(props, null);
		  Store store = session.getStore("imaps");
		  store.connect("imap.gmail.com", username, password);
		  
          Folder inbox = store.getDefaultFolder().getFolder("INBOX");
          inbox.open(Folder.READ_ONLY);

          Message[] msg = inbox.getMessages();
          
          System.out.println("Ricevuti "+msg.length+" messaggi");
          Object contenuto =msg[0].getContent();
          System.out.println("testo primo messaggio "+contenuto);
          
		} catch (NoSuchProviderException e) {
		  e.printStackTrace();
		  System.exit(1);
		} catch (MessagingException e) {
		  e.printStackTrace();
		  System.exit(2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	
	public static void receive(String username,String password) 

	{
	        String host = "pop.gmail.com";
	        try {
	            Properties prop = new Properties();
	            prop.put("mail.pop3.host", host);
	            prop.put("mail.pop3.auth", "true");
	            prop.put("mail.store.protocol", "pop3s");
	            
	            
	            Session session = Session.getDefaultInstance(prop,null);
	            Store store = session.getStore("pop3s");
	            System.out.println("your ID is : "+ username);
	            System.out.println("Connecting...");
	            store.connect(host, username, password);
	            System.out.println("Connected...");
	            
	            Folder inbox = store.getDefaultFolder().getFolder("INBOX");
	            inbox.open(Folder.READ_ONLY);
	 
	            Message[] msg = inbox.getMessages();
	            
	            System.out.println("Ricevuti "+msg.length+" messaggi");
//	            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	 
//	            for (int i = 0; i < msg.length; i++) {
//	              System.out.println("Subject:" + msg[i].getSubject());
//	               System.out.println("Read message? [Y to read / N to end]");
//	              String line = reader.readLine();
//	              if ("Y".equalsIgnoreCase(line))
//	              {
//	                handleMultipart(msg[i]);
//	                System.out.println("****************************");
//	              }
//	              else if ("N".equalsIgnoreCase(line))
//	               {
//	                break;
//	              }
//	              else
//	              {}
//	            }
	            if (inbox != null) {
	                inbox.close(true);
	            }
	            if (store != null) {
	                store.close();
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    private static void handle(Message msg) throws Exception {
	        System.out.println("From:" + msg.getFrom()[0].toString());
	        System.out.println("SentDate:" + msg.getSentDate());
	    }
	 
//	    private static void handleText(Message msg) throws Exception {
//	        handle(msg);
//	        System.out.println("Content:"+msg.getContent());
//	    }
//	 
//	    private static void handleMultipart(Message msg) throws Exception {
//	        String disposition;
//	        BodyPart part;
//	        Multipart mp = (Multipart) msg.getContent();
//	        int mpCount = mp.getCount();
//	        for (int m = 0; m < mpCount; m++) {
//	            handle(msg);
//	            part = mp.getBodyPart(m);
//	            disposition = part.getDisposition();
//	            System.out.println(disposition);
//	            if (disposition != null && disposition.equals(Part.ATTACHMENT))
//	            {
//	              BufferedReader areader = new BufferedReader(new InputStreamReader                 (System.in));
//	              System.out.println("you get an attachment : " + part.getFileName());
//	              System.out.println("Save attachment? [Y to read / N to end]");
//	              String line = areader.readLine();
//	              if ("Y".equalsIgnoreCase(line))
//	              {
//	                saveAttach(part);
//	              }
//	              else{}
//	            } else if(disposition.equals(Part.INLINE)){
//	                System.out.println(part.getContent());
//	            } else {}
//	        }
//	    
//
//	}
	
	private static void printMessage(Message message) {
		try
	    {
	      // Get the header information
	      String from=((InternetAddress)message.getFrom()[0]).getPersonal();
	      if (from==null) from=((InternetAddress)message.getFrom()[0])
	       .getAddress();
	      System.out.println("FROM: "+from);
	      String subject=message.getSubject();
	      System.out.println("SUBJECT: "+subject);
	      // -- Get the message part (i.e. the message itself) --
	      Part messagePart=message;
	      Object content=messagePart.getContent();
	      // -- or its first body part if it is a multipart message --
	      if (content instanceof Multipart)
	      {
	        messagePart=((Multipart)content).getBodyPart(0);
	        System.out.println("[ Multipart Message ]");
	      }
	      // -- Get the content type --
	      String contentType=messagePart.getContentType();
	      // -- If the content is plain text, we can print it --
	      System.out.println("CONTENT:"+contentType);
	      if (contentType.startsWith("text/plain")
	       || contentType.startsWith("text/html"))
	      {
	        InputStream is = messagePart.getInputStream();
	        BufferedReader reader
	         =new BufferedReader(new InputStreamReader(is));
	        String thisLine=reader.readLine();
	        while (thisLine!=null)
	        {
	          System.out.println(thisLine);
	          thisLine=reader.readLine();
	        }
	      }
	      System.out.println("-----------------------------");
	    }
	    catch (Exception ex)
	    {
	      ex.printStackTrace();
	    }
	}
		
	


	public static void send(String smtpServer, String to, String from, String subject, String body){
	    try
	    {
	    	
	      Properties props=getGmailProperties();			      
	      Session session = Session.getDefaultInstance(props, null);
	      // -- Create a new message --
	      Message msg = new MimeMessage(session);
	      // -- Set the FROM and TO fields --
	      msg.setFrom(new InternetAddress(from));
	      msg.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to, false));
	      
	      // -- per includere CC recipients  --
	      if (MAIL_TO_CC != null && !MAIL_TO_CC.equals("")){
	    	  msg.setRecipients(Message.RecipientType.CC,InternetAddress.parse(MAIL_TO_CC, false));
		  }
	
	      // -- Set the subject and body text --
	      msg.setSubject(subject);
	      msg.setText(body);
	      
	      // -- Set some other header information --
	      msg.setHeader("unHeader", "provaHeader");
	      msg.setSentDate(new Date());
	      
	      // -- Send the message --
	      Transport transport = session.getTransport("smtp");
	      transport.connect(smtpServer, USER_NAME, PASSWORD);
	      transport.sendMessage(msg, msg.getAllRecipients());
	      transport.close();
	      System.out.println("Message sent OK.");
	    }
	    catch (Exception ex)
	    {
	      ex.printStackTrace();
	    }
	  }
	
	
	public static Properties getGmailProperties(){
		
		  Properties props = new Properties();
	      props.put("host", "smtp.gmail.com");
	      props.put("mail.transport.protocol", "smtp");
	      props.put("mail.smtp.auth", true);
	      props.put("mail.smtp.starttls.enable", true);
	      props.put("mail.debug", true);
	      return props;
	}
}
