/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wellbeing.facade;

import com.wellbeing.entidades.Certificado;
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
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import javax.servlet.http.HttpServletResponse;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author cristian
 */
@Stateless
public class CertificadoFacade extends AbstractFacade<Certificado> {

    @PersistenceContext(unitName = "WellbeingPU")
    private EntityManager em;
    private StreamedContent archivo;


    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CertificadoFacade() {
        super(Certificado.class);
    }

    public void registrarSolicitudCertificado(String documento, Integer tipoSolicitud) {
        StoredProcedureQuery procedimientoAlmacenado = em.createStoredProcedureQuery("insertarSolicitud");
        procedimientoAlmacenado.registerStoredProcedureParameter("iden", String.class, ParameterMode.IN);
        procedimientoAlmacenado.registerStoredProcedureParameter("tipo", Integer.class, ParameterMode.IN);
        procedimientoAlmacenado.setParameter("iden", documento);
        procedimientoAlmacenado.setParameter("tipo", tipoSolicitud);
        procedimientoAlmacenado.execute();
    }

    public List<Certificado> listarCertificados(String usuario) {
        Query query=em.createQuery("SELECT c FROM Certificado c JOIN c.idSolicitud s JOIN s.usuarioRadicador u WHERE u.idUsuario=?1 AND c.imgCertificado!=null");
        query.setParameter(1,usuario);
        return query.getResultList();
    }

    public StreamedContent descargarCertificado(Integer idCertificado) {
        ResultSet rs;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/bdwellbeing?user=cristian&&password=123456");
            PreparedStatement st =cn.prepareStatement("SELECT imgCertificado FROM certificados WHERE idCertificado=(?)");
            st.setInt(1, idCertificado);
            rs=st.executeQuery();
            while (rs.next()) {
                InputStream stream=rs.getBinaryStream("imgCertificado");
                archivo=new DefaultStreamedContent(stream,"image/jpg","certificado.jpg");
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Descarga", "Se ha descargado correctamente su archivo "));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Descarga", "Su archivo no se pudo descargar correctamente"));
        }
       return archivo;
    }

    public void ver(Integer idCertificado) {
        ResultSet rs;
        try {
            byte[] bytes = null;
            Class.forName("com.mysql.jdbc.Driver");
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdwellbeing?user=cristian&password=123456");
            PreparedStatement st = cn.prepareStatement("SELECT imgCertificado FROM certificados WHERE idCertificado=(?)");
            st.setInt(1, idCertificado);

            rs = st.executeQuery();
            while (rs.next()) {
                bytes = rs.getBytes("imgCertificado");
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
