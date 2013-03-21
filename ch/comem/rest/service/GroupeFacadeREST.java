/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.rest.service;

import ch.comem.models.Groupe;
import ch.comem.models.Membre;
import ch.comem.services.GroupeManagerLocal;
import ch.comem.services.MembreManagerLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 *
 * @author bastieneichenberger
 */
@Stateless
@Path("groupes")
public class GroupeFacadeREST {
    @EJB
    private MembreManagerLocal membreManager;
    @EJB
    private GroupeManagerLocal groupeManager;
    
    /**
     * méthode qui permet de récupérer la list des membre appartenant à un groupe
     * @param idGroupe
     * @return une List de membre
     */
    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public List<Membre> getMembresFromGroup(@PathParam("id") Long idGroupe) {
        Groupe groupe = groupeManager.readGroupe(idGroupe);
        List<Membre> listMembre = membreManager.getListMembresFromGroup(idGroupe);
        return listMembre;
    }
    /**
     * méthode qui permet d'ajouter un groupe
     * @param membreCreateur
     * @param groupe
     * @param membre, le membre qui a créé ce groupe
     * @return le groupe ajouté
     */
    @POST
    @Consumes({"application/xml", "application/json"}) //mimeType
    @Produces({"application/xml", "application/json"})
    public Groupe addGroupe(Membre membreCreateur, Groupe groupe) {
        Long id = groupeManager.createGroupe(membreCreateur.getId(), groupe.getNom());
        Groupe groupeAjoute = groupeManager.readGroupe(id);
        return groupeAjoute;
    }
    
    @DELETE
    @Path("{id}")
    public Response delGroupe(@PathParam("id") Long idGroupe, @PathParam("id") Long idMembre){
        groupeManager.deleteGroupe(idGroupe, idMembre);
        return Response.status(200).entity("le groupe a bien été supprimé").build();
    }
    
}
