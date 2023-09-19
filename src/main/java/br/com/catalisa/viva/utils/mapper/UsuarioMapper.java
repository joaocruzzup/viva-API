package br.com.catalisa.viva.utils.mapper;

import br.com.catalisa.viva.model.dto.UsuarioDTO;
import br.com.catalisa.viva.model.usuario.UsuarioModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UsuarioMapper {
    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    UsuarioDTO usuarioToUsuarioDTO(UsuarioModel usuario);

    UsuarioModel usuarioDTOToUsuario(UsuarioDTO usuarioDTO);
}
