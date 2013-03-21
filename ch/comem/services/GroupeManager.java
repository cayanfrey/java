/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.services;

import ch.comem.daoExceptions.DaoException;
import ch.comem.models.Groupe;
import ch.comem.models.Membre;
import javax.ejb.EJB;
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
    
    @EJB
    private MembreManagerLocal membreManager;
    
   
    
    
    /**
     * Méthode qui créer un groupe, on connait quel membre a créé quel groupe
     * @param nom, le nom du groupe
     * @return Long, l'id du Membre qui créer le groupe, le nom du nouveau groupe
     */
    @Override
    public Long createGroupe(Long idMembre,String nom) {
        // read membre
        Membre membreCreateur = membreManager.readMembre(idMembre);
        // ajouter le nom du groupe
        Groupe groupe = new Groupe();
        groupe.setNom(nom);
        // ajouter la relation membre qui createur du groupe
        membreCreateur.addGroupeCree(groupe);
        groupe.setMembreCreerGroupe(membreCreateur);
        em.persist(membreCreateur);
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
     * cette méthode vérifie la contrainte d'intégrité: le membre qui supprime un groupe doit en être le créateur
     * @param id, l'id du groupe à supprimer
     */
    @Override
    public void deleteGroupe(Long idGroupe, Long idMembre) {
        Groupe retourGroupe = em.find(Groupe.class, idGroupe);
        if(retourGroupe != null){
            if(this.estCreateurGroupe(idGroupe, idMembre)){
                em.remove(retourGroupe);
            }
            else{
                throw new DaoException("le membre doit être le créateur "
                        + "du groupe pour pouvoir le supprimer", DaoException.StatutsCode.CI_NOT_RESPECTED);
            }
            
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
    
    // vérifications contraintes d'intégritées --------------------------------------------------------
    /**
     * méthode qui permet de savoir si un membre est bien le créateur d'un groupe
     * @param idGroupe
     * @param idMembre
     * @return true si le membre a bien créé ce groupe, false sinon
     */
    private Boolean estCreateurGroupe(Long idGroupe, Long idMembre){
        Boolean estCreateur = false;
        Membre membre = membreManager.readMembre(idMembre);
        Groupe groupeASupprimer = this.readGroupe(idGroupe);
        // le groupe que l'on souhaite supprimer a-t-il été créé par ce membre
        Long idCreateurMembre = groupeASupprimer.getMembreCreerGroupe().getId();
        if(idCreateurMembre == membre.getId()){
            estCreateur = true;
        }
        return estCreateur;
    }
    
    



    
    
}
