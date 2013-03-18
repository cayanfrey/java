/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import ch.comem.models.Groupe;
import ch.comem.models.Membre;
import ch.comem.models.Photo;
import ch.comem.models.Statut;
import ch.comem.services.MissionManagerLocal;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

/**
 *
 * @author bastieneichenberger
 */
@Stateless
@WebService
public class TestMission implements TestMissionLocal {
    @EJB
    private MissionManagerLocal missionManager;
    
    public void addTestMission(String titre, String description, Date dateMission, Date duree, 
    int nbPoints, Statut statut, String categorie, String titreMedia, String nomMembre, String nomGroupe){
        // photo
        Photo photo = new Photo();
        photo.setTitre(titreMedia);
        // membre
        Membre membre = new Membre();
        membre.setNom(nomMembre);
        // groupe
        Groupe groupe = new Groupe();
        groupe.setNom(nomGroupe);
        missionManager.createMission(titre, description, dateMission, duree, nbPoints, statut, categorie, photo, membre, groupe);
    }

    @Override
    public void updateTestMission(Long id, String newTitre, String newDescription, String newCat) {
        missionManager.updateMission(id, newTitre, newDescription, newCat);
    }

    @Override
    public void removeTestMission(Long id) {
        missionManager.deleteMission(id);
    }
    
    @Override
    public void readTestMission(Long id) {
        missionManager.readMission(id);
    }
    
    
    
    

}
