package br.com.catalisa.viva.utils.mapper;

import br.com.catalisa.viva.model.dto.ConsultaDTO;
import br.com.catalisa.viva.model.Consulta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ConsultaMapper {
    ConsultaMapper INSTANCE = Mappers.getMapper(ConsultaMapper.class);

    @Mapping(target = "profissionalSaude", source = "profissionalSaude")
    @Mapping(target = "usuario", source = "usuario")
    ConsultaDTO consultaToConsultaDTO(Consulta consulta);

    @Mapping(target = "profissionalSaude", source = "profissionalSaude")
    @Mapping(target = "usuario", source = "usuario")
    Consulta consultaDTOToConsulta(ConsultaDTO consultaDTO);
}
