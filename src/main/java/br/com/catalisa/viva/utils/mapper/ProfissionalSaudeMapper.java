package br.com.catalisa.viva.utils.mapper;

import br.com.catalisa.viva.model.dto.ProfissionalSaudeDTO;
import br.com.catalisa.viva.model.ProfissionalSaude;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProfissionalSaudeMapper {
    ProfissionalSaudeMapper INSTANCE = Mappers.getMapper(ProfissionalSaudeMapper.class);

    @Mapping(target = "endereco", source = "endereco")
    ProfissionalSaudeDTO profissionalSaudeToProfissionalSaudeDTO(ProfissionalSaude profissionalSaude);

    @Mapping(target = "endereco", source = "endereco")
    ProfissionalSaude profissionalSaudeDTOToProfissionalSaude(ProfissionalSaudeDTO profissionalSaudeDTO);
}
