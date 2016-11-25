/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wellbeing.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Transport;

/**
 *
 * @author cristian
 */
public class Correo implements ServicioCorreo{

    public final static String HOST_CORREO_GMAIL = "smtp.gmail.com";
    private final static String CORREO_REMITENTE="iskenderungroup@gmail.com";
    private final static String CONTRASENIA_REMITENTE="Wellbeing2016*";
    private String correoDestinatario;
    private String asunto;
    private String contenido;
    private Session session;
    private MimeMessage mimeMessage;
    private InternetAddress[] internetAddresses;

    public Correo() {
       
    }

    public Correo(String correoDestinatario) {
        this.correoDestinatario = correoDestinatario;
    }

   
    private void init() {
        try {
            Properties propiedades = new Properties();
            propiedades.setProperty("mail.smtp.host", HOST_CORREO_GMAIL);
            propiedades.setProperty("mail.smtp.starttls.enable", "true");
            propiedades.setProperty("mail.smtp.port", "587");
            propiedades.setProperty("mail.smtp.user", CORREO_REMITENTE);
            propiedades.setProperty("mail.smtp.auth", "true");
            session = Session.getDefaultInstance(propiedades);
            mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(CORREO_REMITENTE));
  

        } catch (Exception e) {
            Logger.getLogger(Correo.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }
    
    

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }


    public String getCorreoDestinatario() {
        return correoDestinatario;
    }

    public void setCorreoDestinatario(String correoDestinatario) {
        this.correoDestinatario = correoDestinatario;
    }

    
    
    public boolean enviarCorreo() {
        try {
            init();
            mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(correoDestinatario));
            mimeMessage.setSubject(asunto);
            //mimeMessage.setText(contenido);
            mimeMessage.setContent(contenido, "text/html");
            Transport transport=session.getTransport("smtp");
            transport.connect(CORREO_REMITENTE,CONTRASENIA_REMITENTE);
            transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
            transport.close();
            return true;
        } catch (Exception e) {
             Logger.getLogger(Correo.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }
    
    public boolean enviarCorreosMasivos(List<String> corrreos) {
        try {
            init();
            
            mimeMessage.setSubject(asunto);
            //mimeMessage.setText(contenido);
            mimeMessage.setContent(contenido, "text/html");
            Transport transport=session.getTransport("smtp");
            transport.connect(CORREO_REMITENTE,CONTRASENIA_REMITENTE);
            internetAddresses=new InternetAddress[2];
            int i=0;
            for (String corrreo : corrreos) {
                  internetAddresses[i] = new InternetAddress(corrreo);
                  i++;
            }
            mimeMessage.setRecipients(Message.RecipientType.TO, internetAddresses);
            transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
            transport.close();
            return true;
        } catch (Exception e) {
             Logger.getLogger(Correo.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }
    
    public String agregarHtml(String url) {
        InputStream is = getClass().getResourceAsStream(url);
        BufferedInputStream bis = new BufferedInputStream(is);
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        int result;
        try {
            result = bis.read();
            while (result != -1) {
                byte b = (byte) result;
                buf.write(b);
                result = bis.read();
            }
            return buf.toString("UTF-8");
        } catch (IOException iOE) {
            iOE.printStackTrace();
        }
        return "";
    }

   
}
