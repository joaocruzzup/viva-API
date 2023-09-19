package br.com.catalisa.viva.service;

import br.com.catalisa.viva.exception.EntidadeNaoEncontradaException;
import br.com.catalisa.viva.model.dto.DelecaoResponseDTO;
import br.com.catalisa.viva.model.dto.EnderecoDTO;
import br.com.catalisa.viva.model.Endereco;
import br.com.catalisa.viva.repository.EnderecoRepository;
import br.com.catalisa.viva.utils.mapper.EnderecoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public List<EnderecoDTO> listarTodos() {
        List<Endereco> enderecos = enderecoRepository.findAll();
        List<EnderecoDTO> enderecosDTO = new ArrayList<>();

        for (Endereco endereco : enderecos) {
            enderecosDTO.add(EnderecoMapper.INSTANCE.enderecoToEnderecoDTO(endereco));
        }

        return enderecosDTO;
    }

    public EnderecoDTO buscarPorId(Long id) throws Exception {
        Endereco enderecoEncontrado = enderecoRepository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Endereço não encontrado"));
        return EnderecoMapper.INSTANCE.enderecoToEnderecoDTO(enderecoEncontrado);
    }

    public EnderecoDTO criar(EnderecoDTO enderecoDTO){
        Endereco endereco = EnderecoMapper.INSTANCE.enderecoDTOToEndereco(enderecoDTO);
        enderecoRepository.save(endereco);
        return enderecoDTO;
    }

    public EnderecoDTO atualizar(Long id, EnderecoDTO enderecoDTO) throws Exception {
        Endereco enderecoEncontrado = enderecoRepository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Endereço não encontrado"));


        enderecoRepository.save(enderecoEncontrado);

        return EnderecoMapper.INSTANCE.enderecoToEnderecoDTO(enderecoEncontrado);
    }

    public DelecaoResponseDTO deletar(Long id) throws Exception {
        Endereco enderecoEncontrado = enderecoRepository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Endereço não encontrado"));

        enderecoRepository.delete(enderecoEncontrado);
        return new DelecaoResponseDTO("Endereço deletado com sucesso");
    }
}
