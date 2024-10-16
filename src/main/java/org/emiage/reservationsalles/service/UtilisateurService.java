package org.emiage.reservationsalles.service;

import org.emiage.reservationsalles.dto.UtilisateurDTO;
import org.emiage.reservationsalles.dto.UtilisateurRequestDTO;
import org.emiage.reservationsalles.entity.Utilisateur;
import org.emiage.reservationsalles.exception.DuplicateResourceException;
import org.emiage.reservationsalles.exception.ResourceNotFoundException;
import org.emiage.reservationsalles.exception.UnauthorizedException;
import org.emiage.reservationsalles.repository.UtilisateurRepository;
import org.emiage.reservationsalles.util.PasswordUtil;
import org.emiage.reservationsalles.util.mapper.UtilisateurMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UtilisateurServiceTest {

    @InjectMocks
    private UtilisateurService utilisateurService;

    @Mock
    private UtilisateurRepository utilisateurRepository;

    @Mock
    private UtilisateurMapper utilisateurMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void creerUtilisateur_Success() {
        UtilisateurRequestDTO requestDTO = new UtilisateurRequestDTO("Alice", "alice@example.com", "password123", "USER");

        when(utilisateurRepository.findByEmail("alice@example.com")).thenReturn(Optional.empty());

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(1);
        utilisateur.setNom("Alice");
        utilisateur.setEmail("alice@example.com");
        utilisateur.setMotDePasse(PasswordUtil.hashPassword("password123"));
        utilisateur.setRole(Utilisateur.Role.USER);

        when(utilisateurRepository.save(any(Utilisateur.class))).thenReturn(utilisateur);
        when(utilisateurMapper.toDTO(utilisateur)).thenReturn(new UtilisateurDTO(utilisateur));

        UtilisateurDTO utilisateurDTO = utilisateurService.creerUtilisateur(requestDTO);

        assertNotNull(utilisateurDTO);
        assertEquals("Alice", utilisateurDTO.getNom());
        assertEquals("alice@example.com", utilisateurDTO.getEmail());
        assertEquals("USER", utilisateurDTO.getRole());

        verify(utilisateurRepository, times(1)).findByEmail("alice@example.com");
        verify(utilisateurRepository, times(1)).save(any(Utilisateur.class));
        verify(utilisateurMapper, times(1)).toDTO(utilisateur);
    }

    @Test
    void creerUtilisateur_DuplicateEmail() {
        UtilisateurRequestDTO requestDTO = new UtilisateurRequestDTO("Alice", "alice@example.com", "password123", "USER");

        Utilisateur existingUtilisateur = new Utilisateur();
        existingUtilisateur.setId(1);
        existingUtilisateur.setEmail("alice@example.com");

        when(utilisateurRepository.findByEmail("alice@example.com")).thenReturn(Optional.of(existingUtilisateur));

        assertThrows(DuplicateResourceException.class, () -> {
            utilisateurService.creerUtilisateur(requestDTO);
        });

        verify(utilisateurRepository, times(1)).findByEmail("alice@example.com");
        verify(utilisateurRepository, never()).save(any(Utilisateur.class));
        verify(utilisateurMapper, never()).toDTO(any(Utilisateur.class));
    }

    @Test
    void authentifierUtilisateur_Success() {
        String email = "alice@example.com";
        String password = "password123";
        String hashedPassword = PasswordUtil.hashPassword(password);

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(1);
        utilisateur.setNom("Alice");
        utilisateur.setEmail(email);
        utilisateur.setMotDePasse(hashedPassword);
        utilisateur.setRole(Utilisateur.Role.USER);

        when(utilisateurRepository.findByEmail(email)).thenReturn(Optional.of(utilisateur));
        when(utilisateurMapper.toDTO(utilisateur)).thenReturn(new UtilisateurDTO(utilisateur));

        UtilisateurDTO utilisateurDTO = utilisateurService.authentifierUtilisateur(email, password);

        assertNotNull(utilisateurDTO);
        assertEquals("Alice", utilisateurDTO.getNom());
        assertEquals(email, utilisateurDTO.getEmail());
        assertEquals("USER", utilisateurDTO.getRole());

        verify(utilisateurRepository, times(1)).findByEmail(email);
        verify(utilisateurMapper, times(1)).toDTO(utilisateur);
    }

    @Test
    void authentifierUtilisateur_InvalidPassword() {
        String email = "alice@example.com";
        String password = "password123";
        String wrongPassword = "wrongpassword";
        String hashedPassword = PasswordUtil.hashPassword(password);

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(1);
        utilisateur.setNom("Alice");
        utilisateur.setEmail(email);
        utilisateur.setMotDePasse(hashedPassword);
        utilisateur.setRole(Utilisateur.Role.USER);

        when(utilisateurRepository.findByEmail(email)).thenReturn(Optional.of(utilisateur));

        assertThrows(UnauthorizedException.class, () -> {
            utilisateurService.authentifierUtilisateur(email, wrongPassword);
        });

        verify(utilisateurRepository, times(1)).findByEmail(email);
        verify(utilisateurMapper, never()).toDTO(any(Utilisateur.class));
    }

    @Test
    void authentifierUtilisateur_UserNotFound() {
        String email = "nonexistent@example.com";
        String password = "password123";

        when(utilisateurRepository.findByEmail(email)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            utilisateurService.authentifierUtilisateur(email, password);
        });

        verify(utilisateurRepository, times(1)).findByEmail(email);
        verify(utilisateurMapper, never()).toDTO(any(Utilisateur.class));
    }

    // Ajoutez d'autres tests pour les méthodes restantes si nécessaire
}
