package org.emiage.reservationsalles.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Entité représentant un utilisateur dans le système.
 */
@Entity
@Table(name = "utilisateur") // Assurez-vous que le nom correspond à celui défini dans le script SQL
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Utilisateur {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = false)
    private String nom;
    
    @Column(nullable = false, unique = true)
    private String email;
    
    @Column(name = "mot_de_passe", nullable = false)
    @JsonIgnore // Exclut le mot de passe des sérialisations JSON pour des raisons de sécurité
    private String motDePasse;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
    
    /**
     * Enumération pour les rôles des utilisateurs.
     */
    public enum Role {
        USER,
        ADMIN
    }
    
    /**
     * Méthode pour définir le mot de passe avec hachage.
     * <p>
     * Utilisez cette méthode dans le service avant de persister ou de mettre à jour l'utilisateur.
     * </p>
     *
     * @param plainPassword Le mot de passe en clair.
     */
    public void setMotDePasseAvecHashage(String plainPassword) {
        this.motDePasse = PasswordUtil.hashPassword(plainPassword);
    }
}
