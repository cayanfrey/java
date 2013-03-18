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
    int nbPoints, Statut statut, String categorie, Media media, Membre membre, Groupe groupe) {
        Mission mission = new Mission();
        mission.setTitre(titre);
        mission.setDescription(description);
        mission.setDateMission(dateMission);
        mission.setDuree(duree);
        mission.setNbPoints(nbPoints);
        mission.setStatut(statut);
        mission.setCategorie(categorie);
        
        // relation media    
        mission.setMedia(media);
        // relations membre mision
        mission.setMembreEffectueMission(membre);
        membre.addMission(mission);
        mission.setMembreValideMission(membre);
        membre.addListMissionDonne(mission);
        // relation membre groupe
        membre.addGroupe(groupe);
        groupe.addMembreGroupe(membre);
        
        em.persist(media);
        em.persist(groupe);
        em.persist(membre);
        em.persist(mission);
        em.flush();
        return mission.getId();
    }

    @Override
    public void updateMission(Long id, String newTitre, String newDescription, String newCat) {
        Mission retourMission = em.find(Mission.class, id);
        if(retourMission != null){
            retourMission.setTitre(newTitre);
            retourMission.setDescription(newDescription);
            retourMission.setCategorie(newCat);
            em.persist(retourMission);
            em.flush();
        }
    }
    
    
    

    

}
