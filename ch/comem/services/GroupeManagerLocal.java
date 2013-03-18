/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.services;

import ch.comem.models.Groupe;
import javax.ejb.Local;

/**
 *
 * @author Cayan
 */
@Local
public interface GroupeManagerLocal {

    Long createGroupe(String nom);
    
    void updateGroupe(Long id, String newNom);

    void deleteGroupe(Long id);

    Groupe readGroupe(Long id);
    
}
