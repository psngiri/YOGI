package yogi.report.email;

import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import yogi.base.app.ErrorReporter;

public class EmailAssistant {

	public static void sendEmail(String toAddr, String fromAddr, String subject, String msgText, String[] fileNames,String mailServer) {
		try{
			 boolean debug = false;
			 Properties props = new Properties();
		     props.put("mail.smtp.user", fromAddr);
			 props.put("mail.smtp.host", mailServer);
			 Session session = Session.getDefaultInstance(props, null);
		     session.setDebug(debug);
			 javax.mail.Message msg = new MimeMessage(session);
			 msg.setFrom(new InternetAddress(fromAddr));
		     msg.setRecipients(Message.RecipientType.TO, 
			 InternetAddress.parse(toAddr, false));
			 msg.setSubject(subject);
			 msg.setSentDate(new Date()); 
			 MimeBodyPart mbp1 = new MimeBodyPart();
			 mbp1.setText(msgText == null ? " " : msgText);
			 Multipart mp = new MimeMultipart();
			 mp.addBodyPart(mbp1);
			 MimeBodyPart mbp2;
  			 for (int i =0, n=fileNames.length; i<n; i++ )
				{
					if (fileNames[i]!=null && fileNames[i].trim().length()>0)
					{
						mbp2 = new MimeBodyPart();
						FileDataSource fds = new FileDataSource(fileNames[i]);
						mbp2.setDataHandler(new DataHandler(fds));
						mbp2.setFileName(fds.getName());
						mbp2.setDisposition("ATTACHMENT");
						mp.addBodyPart(mbp2);			
					}
				}
				msg.setContent(mp);
				Transport trans = session.getTransport(new InternetAddress(fromAddr));
				trans.connect();
				trans.sendMessage(msg, msg.getAllRecipients());
			}
			catch (Exception e) {
				ErrorReporter.get().warning("Error in Sending Email",e);
			}

	}


}
