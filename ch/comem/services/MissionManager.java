/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.services;

import ch.comem.messages.Message;
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


    /**
     * Méthode qui créer une mission génère un message [OBJETCREE]
     * @param titre, le titre de la mission
     * @param description, la description
     * @param dateMission, la date de début au format Date
     * @param duree, la duree de la mission
     * @param nbPoints, le nombre de points
     * @param statut, le statut [ENCOURS, ABANDONNE, TERMINE]
     * @param categorie, la catégorie
     * @param media, un objet Media [Video, Photo]
     * @param membre, un objet Membre
     * @param groupe, un objet Groupe
     * @return Long, l'id de la mission qui a été créée
     */
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
        // set message
        Message.setStatut(Message.TypeStatut.OBJETCREE);
        return mission.getId();
    }
    
    /**
     * Méthode qui update une mission génère un message [OBJETMODIFIE ou OBJETINEXISTANT]
     * @param id, l'id de la mission à modifier
     * @param newTitre, le nouveau titre
     * @param newDescription, la nouvelle description
     * @param newCat, la nouvelle catégorie
     */
    @Override
    public void updateMission(Long id, String newTitre, String newDescription, String newCat) {
        Mission retourMission = em.find(Mission.class, id);
        if(retourMission != null){
            retourMission.setTitre(newTitre);
            retourMission.setDescription(newDescription);
            retourMission.setCategorie(newCat);
            em.persist(retourMission);
            em.flush();
            Message.setStatut(Message.TypeStatut.OBJETMODIFIE);
        }
        else{
            Message.setStatut(Message.TypeStatut.OBJETINEXISTANT);
        }
    }
    /**
     * Méthode qui delete une mission génère un message [OBJETSUPPRIME ou OBJETINEXISTANT]
     * @param id, l'id de la mission à supprimer
     */
    @Override
    public void deleteMission(Long id) {
        Mission retourMission = em.find(Mission.class, id);
        if(retourMission != null){
            em.remove(retourMission);
            Message.setStatut(Message.TypeStatut.OBJETSUPPRIME);
        }
        else{
            Message.setStatut(Message.TypeStatut.OBJETINEXISTANT);
        }
    }
    /**
     * Méthode qui read une mission génère un message [OBJECTVALIDE ou OBJETINEXISTANT]
     * @param id, l'id de la mission à lire
     * @return un objet Mission ou null
     */
    @Override
    public Mission readMission(Long id) {
        Mission retourMission = em.find(Mission.class, id);
        if(retourMission != null){
            Message.setStatut(Message.TypeStatut.OBJECTVALIDE);
        }
        else{
            Message.setStatut(Message.TypeStatut.OBJETINEXISTANT);
        }
        return retourMission;
    }
    
    
    

}
