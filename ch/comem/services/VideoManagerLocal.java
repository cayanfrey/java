/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.services;

import java.util.Date;
import javax.ejb.Local;

/**
 *
 * @author bastieneichenberger
 */
@Local
public interface VideoManagerLocal {

    Long createVideo(String titre, String url, double duree);
    
}
