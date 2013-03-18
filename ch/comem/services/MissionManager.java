/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.services;

import ch.comem.models.Media;
import ch.comem.models.Mission;
import ch.comem.models.Photo;
import ch.comem.models.Statut;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author bastieneichenberger
 */
@Stateless
public class MissionManager implements MissionManagerLocal {
    @PersistenceContext(unitName = "challengeMeAppPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    @Override
    public Long createMission(String titre, String description, Date dateMission, Date duree, 
    int nbPoints, Statut statut, String categorie, Media media) {
        Mission mission = new Mission();
        mission.setTitre(titre);
        mission.setDescription(description);
        mission.setDateMission(dateMission);
        mission.setDuree(duree);
        mission.setNbPoints(nbPoints);
        mission.setStatut(statut);
        mission.setCategorie(categorie);
        
        // media    
        mission.setMedia(media);
        media.setMission(mission);
                
        em.persist(media);
        
        em.persist(mission);
        em.flush();
        return mission.getId();
    }

    

}
