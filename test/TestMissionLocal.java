/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

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
public interface TestMissionLocal {
    
    public void addTestMission(String titre, String description, Date dateMission, Date duree, 
    int nbPoints, Statut statut, String categorie, String titreMedia, String nomMembre, String nomGroupe);

    void updateTestMission(Long id, String newTitre, String newDescription, String newCat);
}
