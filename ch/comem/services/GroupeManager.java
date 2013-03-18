/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.services;

import ch.comem.messages.Message;
import ch.comem.models.Groupe;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Cayan
 */
@Stateless
public class GroupeManager implements GroupeManagerLocal {
    @PersistenceContext(unitName = "challengeMeAppPU")
    private EntityManager em;
    
    
    /**
     * Méthode qui créer un groupe génère un message [OBJETCREE]
     * @param nom, le nom du groupe
     * @return Long, l'id du groupe
     */
    @Override
    public Long createGroupe(String nom) {
        Groupe groupe = new Groupe();
        groupe.setNom(nom);
        em.persist(groupe);
        em.flush();
        Message.setStatut(Message.TypeStatut.OBJETCREE);
        return groupe.getId();
    }
    /**
     * Méthode qui update un groupe génère un message [OBJETMODIFIE ou OBJETINEXISTANT]
     * @param id, l'id du groupe à modifier
     * @param newNom, le nom du groupe à modifier
     */
    @Override
    public void updateGroupe(Long id, String newNom) {
        Groupe retourGroupe = em.find(Groupe.class, id);
        if(retourGroupe != null){
            retourGroupe.setNom(newNom);
            em.persist(retourGroupe);
            em.flush();
            Message.setStatut(Message.TypeStatut.OBJETMODIFIE);
        }
        else{
            Message.setStatut(Message.TypeStatut.OBJETINEXISTANT);
        }
    }
    /**
     * Méthode qui delete un groupe génère un message [OBJETSUPPRIME ou OBJETINEXISTANT]
     * @param id, l'id du groupe à supprimer
     */
    @Override
    public void deleteGroupe(Long id) {
        Groupe retourGroupe = em.find(Groupe.class, id);
        if(retourGroupe != null){
            em.remove(retourGroupe);
            Message.setStatut(Message.TypeStatut.OBJETSUPPRIME);
        }
        else{
            Message.setStatut(Message.TypeStatut.OBJETINEXISTANT);
        }
    }
    /**
     * Méthode qui read un groupe génère un message [OBJECTVALIDE ou OBJETINEXISTANT]
     * @param id
     * @return un objet Groupe ou null
     */
    @Override
    public Groupe readGroupe(Long id) {
        Groupe retourGroupe = em.find(Groupe.class, id);
        if(retourGroupe != null){
            Message.setStatut(Message.TypeStatut.OBJECTVALIDE);
        }
        else{
            Message.setStatut(Message.TypeStatut.OBJETINEXISTANT);
        }
        return retourGroupe;
    }
    
    
    



    
    
}
