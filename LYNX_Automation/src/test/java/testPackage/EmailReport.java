package testPackage;

	import java.util.*;  
	import javax.mail.*;  
	import javax.mail.internet.*;  
	import javax.activation.*;  
	  
	public class EmailReport extends BasePackage.LYNXBase{ 
		
	 public static void SendReportinEmail() {  
	  
	  String to=LYNXProp.getProperty("EmailReportTo");  
	  final String user=LYNXProp.getProperty("EmailUserid");  
	  final String password=LYNXProp.getProperty("EmailPassword");  
	   
	  //1) get the session object     
	  Properties properties = System.getProperties();  
	  //properties.setProperty("mail.smtp.host", "mail.javatpoint.com");
	  properties.setProperty("mail.smtp.host", "localhost");
	  properties.setProperty("mail.smtp.port", "465");
	  properties.put("mail.smtp.auth", "true");  
	  
	  Session session = Session.getDefaultInstance(properties,  
	   new javax.mail.Authenticator() {  
	   protected PasswordAuthentication getPasswordAuthentication() {  
	   return new PasswordAuthentication(user,password);  
	   }  
	  });  
	     
	  //2) compose message     
	  try{  
	    MimeMessage message = new MimeMessage(session);  
	    message.setFrom(new InternetAddress(user));  
	    message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
	    message.setSubject(LYNXProp.getProperty("EmailReportSubject"));  
	      
	    //3) create MimeBodyPart object and set your message text     
	    BodyPart messageBodyPart1 = new MimeBodyPart();  
	    messageBodyPart1.setText(LYNXProp.getProperty("EmailReportBody"));  
	      
	    //4) create new MimeBodyPart object and set DataHandler object to this object      
	    MimeBodyPart messageBodyPart2 = new MimeBodyPart();  
	  
	    String filename = LYNXProp.getProperty("EmailReportPath");  
	    DataSource source = new FileDataSource(filename);  
	    messageBodyPart2.setDataHandler(new DataHandler(source));  
	    messageBodyPart2.setFileName(filename);  
	     
	     
	    //5) create Multipart object and add MimeBodyPart objects to this object      
	    Multipart multipart = new MimeMultipart();  
	    multipart.addBodyPart(messageBodyPart1);  
	    multipart.addBodyPart(messageBodyPart2);  
	  
	    //6) set the multiplart object to the message object  
	    message.setContent(multipart );  
	     
	    //7) send message  
	    Transport.send(message);  
	   
	   System.out.println("Report Sent over email successfully");  
	   }
	  catch (MessagingException ex) {ex.printStackTrace();}  
	 }  
	}  
	
