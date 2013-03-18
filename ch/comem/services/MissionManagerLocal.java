/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.services;

import ch.comem.models.Media;
import ch.comem.models.Statut;
import java.util.Date;
import javax.ejb.Local;

/**
 *
 * @author bastieneichenberger
 */
@Local
public interface MissionManagerLocal {

    Long createMission(String titre, String description, Date dateMission, Date duree, int nbPoints, Statut statut, String categorie, Media media);
    
}
