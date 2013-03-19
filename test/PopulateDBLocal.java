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
    
}
