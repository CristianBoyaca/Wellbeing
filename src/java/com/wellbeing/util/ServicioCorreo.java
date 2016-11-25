/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wellbeing.util;

import java.util.List;

/**
 *
 * @author cristian
 */
public interface ServicioCorreo {
    public boolean enviarCorreo();
    public boolean enviarCorreosMasivos(List<String> corrreos);
}
