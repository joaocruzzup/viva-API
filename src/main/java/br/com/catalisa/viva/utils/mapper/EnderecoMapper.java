package br.com.catalisa.viva.utils.mapper;

import br.com.catalisa.viva.model.dto.EnderecoDTO;
import br.com.catalisa.viva.model.Endereco;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EnderecoMapper {
    EnderecoMapper INSTANCE = Mappers.getMapper(EnderecoMapper.class);
    EnderecoDTO enderecoToEnderecoDTO(Endereco endereco);

    @Mapping(target = "id", ignore = true)
    Endereco enderecoDTOToEndereco(EnderecoDTO enderecoDTO);

}
