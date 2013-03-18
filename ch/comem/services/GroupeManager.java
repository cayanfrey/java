/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.services;

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
    
        public void persist(Object object) {
        em.persist(object);
    }

    @Override
    public Long createGroupe(String nom) {
        Groupe groupe = new Groupe();
        groupe.setNom(nom);
        em.persist(groupe);
        em.flush();
        return groupe.getId();
    }



    
    
}
