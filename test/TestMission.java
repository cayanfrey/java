/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import ch.comem.models.Mission;
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
    int nbPoints, Statut statut, String categorie, String titreMedia){
        Photo photo = new Photo();
        photo.setTitre(titreMedia);
        missionManager.createMission(titre, description, dateMission, duree, nbPoints, statut, categorie, photo);
    }

    @Override
    public void updateTestMission(Mission missionModifie) {
        missionManager.updateMission(missionModifie);
    }
    
    

}
