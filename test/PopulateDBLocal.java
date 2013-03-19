/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import javax.ejb.Local;

/**
 *
 * @author bastieneichenberger
 */
@Local
public interface PopulateDBLocal {

    void populateDB();

    void deleteMembre(Long id);

    void deleteMission(Long id);

    void deleteGroupe(Long id);
    
}
