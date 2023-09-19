package br.com.catalisa.viva.utils.mapper;

import br.com.catalisa.viva.model.dto.InstituicaoDTO;
import br.com.catalisa.viva.model.Instituicao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface InstituicaoMapper {
    InstituicaoMapper INSTANCE = Mappers.getMapper(InstituicaoMapper.class);

    @Mapping(target = "endereco", source = "endereco")
    InstituicaoDTO instituicaoToInstituicaoDTO(Instituicao instituicao);

    @Mapping(target = "endereco", source = "endereco")

    Instituicao instituicaoDTOToInstituicao(InstituicaoDTO instituicaoDTO);
}
