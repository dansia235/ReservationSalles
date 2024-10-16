/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.emiage.reservationsalles.util;

import org.mindrot.jbcrypt.BCrypt;


/**
 *
 * @author Dansia
 */
public class PasswordUtil {
    // Le facteur de coût déterminant la complexité du hachage
    private static final int WORKLOAD = 12;

    /**
     * Hache un mot de passe en clair.
     *
     * @param plainTextPassword Le mot de passe en clair.
     * @return Le mot de passe haché.
     */
    public static String hashPassword(String plainTextPassword) {
        String salt = BCrypt.gensalt(WORKLOAD);
        return BCrypt.hashpw(plainTextPassword, salt);
    }

    /**
     * Vérifie si un mot de passe en clair correspond à un hachage donné.
     *
     * @param plainTextPassword Le mot de passe en clair.
     * @param hashedPassword    Le mot de passe haché.
     * @return true si les mots de passe correspondent, false sinon.
     */
    public static boolean checkPassword(String plainTextPassword, String hashedPassword) {
        if (hashedPassword == null || !hashedPassword.startsWith("$2a$")) {
            throw new IllegalArgumentException("Le mot de passe haché est invalide.");
        }
        return BCrypt.checkpw(plainTextPassword, hashedPassword);
    }
}
