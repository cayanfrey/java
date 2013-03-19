/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.services;

import ch.comem.models.Groupe;
import ch.comem.models.Media;
import ch.comem.models.Membre;
import ch.comem.models.Mission;
import ch.comem.models.Statut;
import java.util.Date;
import javax.ejb.Local;

/**
 *
 * @author bastieneichenberger
 */
@Local
public interface MissionManagerLocal {

    Long createMission(String titre, String description, Date dateMission, Date duree, int nbPoints, Statut statut, String categorie, Long idMedia, Long idMembreValide, Long idMembreEffetue);

    void updateMission(Long id, String newTitre, String newDescription, String newCat);

    void deleteMission(Long id);

    Mission readMission(Long id);
    
}
