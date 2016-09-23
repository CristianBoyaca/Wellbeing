/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wellbeing.util;

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
public class Correo {

    public final static String HOST_CORREO_GMAIL = "smtp.gmail.com";
    private String correoRemitente;
    private String contraseniaRemitente;
    private String correoDestinatario;
    private Session session;
    private MimeMessage mimeMessage;

    public Correo() {
    }

    public Correo(String correoRemitente, String contraseniaRemitente, String correoDestinatario) {
        this.correoRemitente = correoRemitente;
        this.contraseniaRemitente = contraseniaRemitente;
        this.correoDestinatario = correoDestinatario;
    }

    private void init() {
        try {
            Properties propiedades = new Properties();
            propiedades.setProperty("mail.smtp.host", HOST_CORREO_GMAIL);
            propiedades.setProperty("mail.smtp.starttls.enable", "true");
            propiedades.setProperty("mail.smtp.port", "587");
            propiedades.setProperty("mail.smtp.user", this.correoRemitente);
            propiedades.setProperty("mail.smtp.auth", "true");
            session = Session.getDefaultInstance(propiedades);
            mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(correoRemitente));
            mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(correoDestinatario));

        } catch (Exception e) {
            Logger.getLogger(Correo.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public String getCorreoRemitente() {
        return correoRemitente;
    }

    public void setCorreoRemitente(String correoRemitente) {
        this.correoRemitente = correoRemitente;
    }

    public String getContraseniaRemitente() {
        return contraseniaRemitente;
    }

    public void setContraseniaRemitente(String contraseniaRemitente) {
        this.contraseniaRemitente = contraseniaRemitente;
    }

    public String getCorreoDestinatario() {
        return correoDestinatario;
    }

    public void setCorreoDestinatario(String correoDestinatario) {
        this.correoDestinatario = correoDestinatario;
    }

    public boolean enviarCorreo(String asunto, String contenido) {
        try {
            init();
            mimeMessage.setSubject(asunto);
            mimeMessage.setText(contenido);
            //mimeMessage.setContent(contenido, "text/html");
            Transport transport=session.getTransport("smtp");
            transport.connect(correoRemitente,contraseniaRemitente);
            transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
            transport.close();
            return true;
        } catch (Exception e) {
             Logger.getLogger(Correo.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }

}
