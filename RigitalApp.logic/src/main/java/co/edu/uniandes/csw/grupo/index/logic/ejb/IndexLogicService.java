/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupo.index.logic.ejb;

import co.edu.uniandes.csw.grupo.contenedorweb.persistence.api.IContenedorWebPersistence;
import co.edu.uniandes.csw.grupo.index.logic.api.IIndexLogicService;
import co.edu.uniandes.csw.grupo.maquinavirtual.persistence.api.IMaquinaVirtualPersistence;
import co.edu.uniandes.csw.grupo.maquinavirtual.persistence.entity.MaquinaVirtualEntity;
import co.edu.uniandes.csw.grupo.repositorio.persistence.api.IRepositorioPersistence;
import co.edu.uniandes.csw.grupo.sqldev.persistence.api.ISQLDevPersistence;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;
import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.Timer;

/**
 *
 * @author estudiante
 */
public class IndexLogicService implements IIndexLogicService {

    @Inject
    protected IMaquinaVirtualPersistence maquinavirtualPersistance;
    @Inject
    protected IRepositorioPersistence repositorioPersistance;
    @Inject
    protected IContenedorWebPersistence contenedorWebPersistance;
    @Inject
    protected ISQLDevPersistence sqlDevPersistance;
    
    private Timer temporizador;
    
    public void iniciar() {
        System.out.println("Entro a IndexLogicService");
        if(temporizador == null)
            iniciarTimer();
    }
    
    public void revisarFechas() {
        
        String mensaje = "Estimado usuario, los siguientes recursos de encuentran vencidos o próximos a vencerse: ";
        
        mensaje += maquinavirtualPersistance.mensajeVencidos();
        mensaje += repositorioPersistance.mensajeVencidos();
        mensaje += contenedorWebPersistance.mensajeVencidos();
        mensaje += sqlDevPersistance.mensajeVencidos();
        
        if(!mensaje.equals("Estimado usuario, los siguientes recursos de encuentran vencidos o próximos a vencerse: "))
            mandarCorreo("js.salamanca1967@uniandes.edu.co", mensaje);
    }
    
    public void mandarCorreo(String correo, String mensaje){
               
        final String username = "johnathansalamanca@gmail.com";
	final String password = "arthas123.94";

	Properties props = new Properties();
	props.setProperty("mail.smtp.host",  "smtp.gmail.com");
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.port", "587");
        props.setProperty("mail.smtp.auth", "true");

	Session session = Session.getInstance(props,
	        new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);                  
                }
	});

	    try {

	        Message message = new MimeMessage(session);
	        message.setFrom(new InternetAddress("johnathansalamanca@gmail.com"));
	        message.setRecipients(Message.RecipientType.TO,
	                InternetAddress.parse(correo));
	        message.setSubject("Notificaciones Sistema Rigital");
	        message.setText(mensaje);

	        Transport.send(message);

	        System.out.println("Done");

	    } catch (MessagingException e) {
	        throw new RuntimeException(e);
	    }
    }
    
    public void iniciarTimer()    {
        final Long t2 = System.currentTimeMillis();
            
            ActionListener taskPerformer = new ActionListener() {
		      public void actionPerformed(ActionEvent evt) {
		          Long t1 = System.currentTimeMillis();
		          System.out.println((t1-t2));         
                          revisarFechas();
		      }
		  };
            
            temporizador = new Timer(86400000, taskPerformer);
            temporizador.isRepeats();
            temporizador.start();
            
    }
}
