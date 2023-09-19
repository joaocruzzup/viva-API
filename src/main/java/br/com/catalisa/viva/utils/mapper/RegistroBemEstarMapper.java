package br.com.catalisa.viva.utils.mapper;

import br.com.catalisa.viva.model.dto.RegistroBemEstarDTO;
import br.com.catalisa.viva.model.RegistroBemEstar;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RegistroBemEstarMapper {
    RegistroBemEstarMapper INSTANCE = Mappers.getMapper(RegistroBemEstarMapper.class);

    @Mapping(target = "usuario", source = "usuario")
    RegistroBemEstarDTO registroBemEstarToRegistroBemEstarDTO(RegistroBemEstar registroBemEstar);

    @Mapping(target = "usuario", source = "usuario")
    RegistroBemEstar registroBemEstarDTOToRegistroBemEstar(RegistroBemEstarDTO registroBemEstarDTO);
}
