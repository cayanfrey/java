/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.models;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author Cayan
 */
@Entity
@XmlRootElement
public class Membre implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private int point;
    
    // associationss
    @OneToMany(mappedBy = "membreEffectueMission")
    private List<Mission> listMission = new LinkedList<Mission>();
    
    @OneToMany(mappedBy = "membreValideMission")
    private List<Mission> listMissionDonne = new LinkedList<Mission>();
    
    @ManyToMany
    private List<Groupe> listGroupe = new LinkedList<Groupe>();

    @XmlTransient
    @JsonIgnore
    public List<Mission> getListMissionDonne() {
        return listMissionDonne ;
    }
    
    public void setListMissionDonne(List<Mission> listMissionDonne) {
        this.listMissionDonne = listMissionDonne;
    }

    @XmlTransient
    @JsonIgnore
    public List<Mission> getListMission() {
        return listMission;
    }

    public void setListMission(List<Mission> listMission) {
        this.listMission = listMission;
    }
    
    public void addMission(Mission mission){
        if(!this.listMission.contains(mission)){
            this.listMission.add(mission);
        }
    }
    
    public void addListMissionDonne(Mission mission){
        if(!this.listMissionDonne.contains(mission)){
            this.listMissionDonne.add(mission);
        }
    }
    
    public void addGroupe(Groupe groupe){
        if(!this.listGroupe.contains(groupe)){
            this.listGroupe.add(groupe);
        }
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Membre)) {
            return false;
        }
        Membre other = (Membre) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ch.comem.services.Membre[ id=" + id + " ]";
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    
    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
}
