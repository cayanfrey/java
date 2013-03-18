/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.services;

import javax.ejb.Local;

/**
 *
 * @author bastieneichenberger
 */
@Local
public interface PhotoManagerLocal {

    Long createPhoto(String titre, String url, String vignetteUrl);
    
}
