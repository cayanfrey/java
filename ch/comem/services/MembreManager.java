/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.services;

import ch.comem.daoExceptions.DaoException;
import ch.comem.models.Groupe;
import ch.comem.models.Membre;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Cayan
 */
@Stateless
public class MembreManager implements MembreManagerLocal {
    @PersistenceContext(unitName = "challengeMeAppPU")
    private EntityManager em;
    
    /**
     * Méthode qui créer un membre
     * @param nom
     * @param prenom
     * @param email
     * @param point
     * @return Long, l'id du groupe
     */
    @Override
    public Long createMembre(String nom, String prenom, String email, int point) {
        Membre membre = new Membre();
        membre.setNom(nom);
        membre.setPrenom(prenom);
        membre.setEmail(email);
        membre.setPoint(point);
        em.persist(membre);
        em.flush();
        return membre.getId();
    }
    
    @Override
    public Long addGroupe(Long grpId, Long membreId){
        Groupe retourGroupe = em.find(Groupe.class, grpId);
        Membre retourMembre = em.find(Membre.class, membreId);
        if(retourGroupe != null && retourMembre != null){
            retourGroupe.addMembreGroupe(retourMembre);
            retourMembre.addGroupe(retourGroupe);
        }
        else if (retourGroupe == null){
            throw new DaoException("le groupe n'existe pas", DaoException.StatutsCode.GROUPE_NOT_FOUND);
        }
        else if (retourMembre == null){
            throw new DaoException("le membre n'existe pas", DaoException.StatutsCode.MEMBRE_NOT_FOUND);
        }
        return retourMembre.getId();
    }
    
    /**
     * Méthode qui update un membre génère un message MEMBRE_NOT_FOUND si le membre n'existe pas
     * @param id
     * @param newNom
     * @param newPrenom
     * @param newEmail
     * @param newPoints 
     */
    @Override
    public void updateMembre(Long id, String newNom, String newPrenom, String newEmail, int newPoints) {
        Membre retourMembre = em.find(Membre.class, id);
        if(retourMembre != null){
            retourMembre.setNom(newNom);
            retourMembre.setPrenom(newPrenom);
            retourMembre.setEmail(newEmail);
            retourMembre.setPoint(newPoints);
            em.persist(retourMembre);
            em.flush();
        }
        else{
            throw new DaoException("le membre n'existe pas", DaoException.StatutsCode.MEMBRE_NOT_FOUND);
        }
    }
    /**
     * Méthode qui delete un membre génère un message MEMBRE_NOT_FOUND si le membre n'existe pas
     * @param id 
     */
    @Override
    public void deleteMembre(Long id) {
        Membre retourMembre = em.find(Membre.class, id);
        if(retourMembre != null){
            em.remove(retourMembre);
        }
        else{
            throw new DaoException("le membre n'existe pas", DaoException.StatutsCode.MEMBRE_NOT_FOUND);
        }
    }
    /**
     * Méthode qui read un membre génère un message [OBJECTVALIDE ou OBJETINEXISTANT]
     * @param id
     * @return un objet Membre ou null
     */
    @Override
    public Membre readMembre(Long id) {
        Membre retourMembre = em.find(Membre.class, id);
        if(retourMembre == null){
            throw new DaoException("le membre n'existe pas", DaoException.StatutsCode.MEMBRE_NOT_FOUND);
        }
        return retourMembre;
    }
    
    /**
     * méthode qui retourne tous les membres d'un groupe, génère une exception DAO si le groupe est vide
     * @param idGroupe
     * @return une liste de membre ou null
     */
    @Override
    public List<Membre> getListMembresFromGroup(Long idGroupe){
        List<Membre> ret = null;
        Query query = em.createQuery("SELECT m FROM Membre m, Groupe g WHERE m.id= g.id AND g.id = "+idGroupe);
        ret = query.getResultList();
        if(ret.isEmpty()){
            throw new DaoException("le groupe est vide", DaoException.StatutsCode.MEMBRE_NOT_FOUND);
        }
        return ret;
    }


    

    
}
