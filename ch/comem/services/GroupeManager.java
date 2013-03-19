/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.services;

import ch.comem.messages.DaoException;
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
     * Méthode qui créer un groupe
     * @param nom, le nom du groupe
     * @return Long, l'id du groupe
     */
    @Override
    public Long createGroupe(String nom) {
        Groupe groupe = new Groupe();
        groupe.setNom(nom);
        em.persist(groupe);
        em.flush();
        return groupe.getId();
    }
    /**
     * Méthode qui update un groupe génère un message GROUPE_NOT_FOUND si le groupe n'existe pas
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
        }
        else{
            throw new DaoException("le groupe n'existe pas", DaoException.StatutsCode.GROUPE_NOT_FOUND);
        }
    }
    /**
     * Méthode qui delete un groupe génère un message GROUP_NOT_FOUND si le groupe n'existe pas
     * @param id, l'id du groupe à supprimer
     */
    @Override
    public void deleteGroupe(Long id) {
        Groupe retourGroupe = em.find(Groupe.class, id);
        if(retourGroupe != null){
            em.remove(retourGroupe);
        }
        else{
            throw new DaoException("le groupe n'existe pas", DaoException.StatutsCode.GROUPE_NOT_FOUND);
        }
    }
    /**
     * Méthode qui read un groupe génère un message GROUPD_NOT_FOUND si le groupe n'existe pas
     * @param id
     * @return un objet Groupe ou null
     */
    @Override
    public Groupe readGroupe(Long id) {
        Groupe retourGroupe = em.find(Groupe.class, id);
        if(retourGroupe == null){
            throw new DaoException("le groupe n'existe pas", DaoException.StatutsCode.GROUPE_NOT_FOUND);
        }
        return retourGroupe;
    }
    
    
    



    
    
}
