package org.emiage.reservationsalles.resource;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.emiage.reservationsalles.dto.UtilisateurDTO;
import org.emiage.reservationsalles.dto.UtilisateurRequestDTO;
import org.emiage.reservationsalles.security.annotations.Secured;
import org.emiage.reservationsalles.service.UtilisateurService;

import java.util.List;

@Path("/utilisateurs")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UtilisateurResource {

    @Inject
    private UtilisateurService utilisateurService;

    /**
     * Crée un nouvel utilisateur.
     *
     * @param requestDTO Les données de l'utilisateur.
     * @return La réponse HTTP avec l'utilisateur créé.
     */
    @POST
    @Secured(roles = {"ADMIN"})
    public Response creerUtilisateur(@Valid UtilisateurRequestDTO requestDTO) {
        UtilisateurDTO utilisateurDTO = utilisateurService.creerUtilisateur(requestDTO);
        return Response.status(Response.Status.CREATED).entity(utilisateurDTO).build();
    }

    /**
     * Récupère tous les utilisateurs.
     *
     * @return La réponse HTTP avec la liste des utilisateurs.
     */
    @GET
    @Secured(roles = {"ADMIN"})
    public Response getAllUtilisateurs() {
        List<UtilisateurDTO> utilisateurs = utilisateurService.getAllUtilisateurs();
        return Response.ok(utilisateurs).build();
    }

    /**
     * Récupère un utilisateur par son ID.
     *
     * @param id L'ID de l'utilisateur.
     * @return La réponse HTTP avec l'utilisateur trouvé.
     */
    @GET
    @Path("/{id}")
    @Secured(roles = {"ADMIN", "USER"})
    public Response getUtilisateurById(@PathParam("id") Integer id) {
        UtilisateurDTO utilisateurDTO = utilisateurService.getUtilisateurById(id);
        return Response.ok(utilisateurDTO).build();
    }

    /**
     * Met à jour un utilisateur existant.
     *
     * @param id         L'ID de l'utilisateur à mettre à jour.
     * @param requestDTO Les nouvelles données de l'utilisateur.
     * @return La réponse HTTP avec l'utilisateur mis à jour.
     */
    @PUT
    @Path("/{id}")
    @Secured(roles = {"ADMIN"})
    public Response mettreAJourUtilisateur(@PathParam("id") Integer id, @Valid UtilisateurRequestDTO requestDTO) {
        UtilisateurDTO utilisateurDTO = utilisateurService.mettreAJourUtilisateur(id, requestDTO);
        return Response.ok(utilisateurDTO).build();
    }

    /**
     * Supprime un utilisateur par son ID.
     *
     * @param id L'ID de l'utilisateur à supprimer.
     * @return La réponse HTTP indiquant le succès de l'opération.
     */
    @DELETE
    @Path("/{id}")
    @Secured(roles = {"ADMIN"})
    public Response supprimerUtilisateur(@PathParam("id") Integer id) {
        utilisateurService.supprimerUtilisateur(id);
        return Response.noContent().build();
    }

    /**
     * Authentifie un utilisateur en vérifiant son email et son mot de passe.
     *
     * @param loginRequest Les données de connexion de l'utilisateur.
     * @return La réponse HTTP avec l'utilisateur authentifié.
     */
    @POST
    @Path("/auth")
    public Response authentifierUtilisateur(@Valid LoginRequestDTO loginRequest) {
        UtilisateurDTO utilisateurDTO = utilisateurService.authentifierUtilisateur(loginRequest.getEmail(), loginRequest.getPassword());
        return Response.ok(utilisateurDTO).build();
    }
}
