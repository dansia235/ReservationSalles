package org.emiage.reservationsalles.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Data Transfer Object pour les requêtes utilisateur.
 */
public class UtilisateurRequestDTO {

    @NotBlank(message = "Le nom est obligatoire.")
    @Size(max = 100, message = "Le nom ne doit pas dépasser 100 caractères.")
    private String nom;

    @NotBlank(message = "L'email est obligatoire.")
    @Email(message = "L'email doit être valide.")
    @Size(max = 100, message = "L'email ne doit pas dépasser 100 caractères.")
    private String email;

    @NotBlank(message = "Le mot de passe est obligatoire.")
    @Size(min = 6, max = 255, message = "Le mot de passe doit contenir entre 6 et 255 caractères.")
    private String motDePasse;

    @NotBlank(message = "Le rôle est obligatoire.")
    private String role;

    public UtilisateurRequestDTO() {
    }

    public UtilisateurRequestDTO(String nom, String email, String motDePasse, String role) {
        this.nom = nom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.role = role;
    }

    // Getters et Setters

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

    public String getMotDePasse() {
        return motDePasse;
    }
    
    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getRole() {
        return role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
}
