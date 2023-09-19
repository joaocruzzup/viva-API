package br.com.catalisa.viva.utils.mapper;

import br.com.catalisa.viva.model.dto.AvaliacaoRequestDTO;
import br.com.catalisa.viva.model.dto.AvaliacaoResponseDTO;
import br.com.catalisa.viva.model.Avaliacao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AvaliacaoMapper {
    AvaliacaoMapper INSTANCE = Mappers.getMapper(AvaliacaoMapper.class);

    @Mapping(target = "profissionalSaude", source = "profissionalSaude")
    @Mapping(target = "usuario", source = "usuario")
    AvaliacaoResponseDTO avaliacaoToAvaliacaoResponseDTO(Avaliacao avaliacao);

    @Mapping(target = "profissionalSaude", source = "profissionalSaude")
    @Mapping(target = "usuario", source = "usuario")
    Avaliacao avaliacaoResponseDTOToAvaliacao(AvaliacaoResponseDTO avaliacaoResponseDTO);

    @Mapping(target = "profissionalSaude.id", source = "profissionalSaudeId")
    @Mapping(target = "usuario.id_usuario", source = "usuarioId")
    Avaliacao avaliacaoRequestDTOToAvaliacao(AvaliacaoRequestDTO avaliacaoRequestDTO);
}
