package org.emiage.reservationsalles.util.mapper;

import org.emiage.reservationsalles.dto.UtilisateurDTO;
import org.emiage.reservationsalles.entity.Utilisateur;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mapper pour convertir entre Utilisateur et UtilisateurDTO.
 */
@Mapper(componentModel = "cdi")
public interface UtilisateurMapper {
    
    UtilisateurMapper INSTANCE = Mappers.getMapper(UtilisateurMapper.class);
    
    UtilisateurDTO toDTO(Utilisateur utilisateur);
    
    Utilisateur toEntity(UtilisateurDTO utilisateurDTO);
}
