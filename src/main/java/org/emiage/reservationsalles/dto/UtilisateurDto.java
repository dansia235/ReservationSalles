package org.emiage.reservationsalles.dto;

import org.emiage.reservationsalles.entity.Utilisateur;

/**
 * Data Transfer Object pour l'entité Utilisateur.
 */
public class UtilisateurDTO {
    private Integer id;
    private String nom;
    private String email;
    private String role;

    public UtilisateurDTO() {
    }

    public UtilisateurDTO(Utilisateur utilisateur) {
        this.id = utilisateur.getId();
        this.nom = utilisateur.getNom();
        this.email = utilisateur.getEmail();
        this.role = utilisateur.getRole().name();
    }

    // Getters et Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
}
