/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.rest.service;

import ch.comem.messages.DaoException;
import ch.comem.models.Membre;
import ch.comem.services.MembreManagerLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author bastieneichenberger
 */
@Stateless
@Path("membres")
public class MembreFacadeREST extends AbstractFacade<Membre> {
    
    @EJB
    private MembreManagerLocal membreManager;
    
    public MembreFacadeREST() {
        super(Membre.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"}) //mimeType
    public void create(Membre entity) {
        
        membreManager.createMembre(entity.getNom(), entity.getPrenom(), entity.getEmail(), 0);
        // creer player dans l'engine
    }

    @PUT
    @Override
    @Consumes({"application/xml", "application/json"})
    public void edit(Membre entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        try{
            membreManager.deleteMembre(id);
        }
        catch(DaoException daoEx){
            // génère http 404
        }
        
        
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"}) // acept -> envoi depuis le client
    public Membre find(@PathParam("id") Long id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<Membre> findAll() {
        return super.findAll();
    }
    
    //test
    

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Membre> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
