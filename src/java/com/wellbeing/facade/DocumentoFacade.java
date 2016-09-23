/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wellbeing.facade;

import com.wellbeing.entidades.DatoEmpleado;
import com.wellbeing.entidades.Documento;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletResponse;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author cristian
 */
@Stateless
public class DocumentoFacade extends AbstractFacade<Documento> {

    @PersistenceContext(unitName = "WellbeingPU")
    private EntityManager em;
    private StreamedContent archivo;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DocumentoFacade() {
        super(Documento.class);
    }
    
    

    public List<Documento> buscarDocumentos(String identificacion) {
        Query query = em.createQuery("SELECT d  from Documento d JOIN d.identificacion e WHERE e.identificacion=?1");
        query.setParameter(1, identificacion);
        return query.getResultList();
    }

    public StreamedContent descargarDocumento(Integer idDocumento) {
        ResultSet rs;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/bdwellbeing?user=cristian&&password=123456");
            PreparedStatement st =cn.prepareStatement("SELECT imagen FROM documentos WHERE idDocumento=(?)");
            st.setInt(1, idDocumento);
            rs=st.executeQuery();
            while (rs.next()) {
                InputStream stream=rs.getBinaryStream("imagen");
                archivo=new DefaultStreamedContent(stream,"image/jpg","descarga.jpg");
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Descarga", "Se ha descargado correctamente su archivo "));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Descarga", "Su archivo no se pudo descargar correctamente"));
        }
       return archivo;
    }
    
     public void ver(Integer idDocumento) {
        ResultSet rs;
        try {
            byte[] bytes = null;
            Class.forName("com.mysql.jdbc.Driver");
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdwellbeing?user=cristian&password=123456");
            PreparedStatement st = cn.prepareStatement("SELECT imagen FROM documentos WHERE idDocumento=(?)");
            st.setInt(1, idDocumento);

            rs = st.executeQuery();
            while (rs.next()) {
                InputStream stream = rs.getBinaryStream("imagen");
                bytes = rs.getBytes("imagen");
            }
            cn.close();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Archivo", "Tu archivo ha sido descargado con exito"));
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.getOutputStream().write(bytes);
            response.getOutputStream().close();
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Archivo", "Tu archivo no se ha podido descargar"));
        }
     }
}
