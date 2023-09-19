package br.com.catalisa.viva.service;

import br.com.catalisa.viva.exception.EntidadeNaoEncontradaException;
import br.com.catalisa.viva.model.dto.DelecaoResponseDTO;
import br.com.catalisa.viva.model.dto.RegistroBemEstarDTO;
import br.com.catalisa.viva.model.RegistroBemEstar;
import br.com.catalisa.viva.repository.RegistroBemEstarRepository;
import br.com.catalisa.viva.utils.mapper.RegistroBemEstarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RegistroBemEstarService {

    @Autowired
    private RegistroBemEstarRepository registroBemEstarRepository;

    public List<RegistroBemEstarDTO> listarTodos() {
        List<RegistroBemEstar> registros = registroBemEstarRepository.findAll();
        List<RegistroBemEstarDTO> registrosDTO = new ArrayList<>();

        for (RegistroBemEstar registro : registros) {
            registrosDTO.add(RegistroBemEstarMapper.INSTANCE.registroBemEstarToRegistroBemEstarDTO(registro));
        }

        return registrosDTO;
    }

    public RegistroBemEstarDTO buscarPorId(Long id) throws Exception {
        RegistroBemEstar registroEncontrado = registroBemEstarRepository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Registro de Bem-Estar não encontrado"));
        return RegistroBemEstarMapper.INSTANCE.registroBemEstarToRegistroBemEstarDTO(registroEncontrado);
    }

    public RegistroBemEstarDTO criar(RegistroBemEstarDTO registroBemEstarDTO){
        RegistroBemEstar registroBemEstar = RegistroBemEstarMapper.INSTANCE.registroBemEstarDTOToRegistroBemEstar(registroBemEstarDTO);
        registroBemEstarRepository.save(registroBemEstar);
        return registroBemEstarDTO;
    }

    public RegistroBemEstarDTO atualizar(Long id, RegistroBemEstarDTO registroDTO) throws Exception {
        RegistroBemEstar registroEncontrado = registroBemEstarRepository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Registro de Bem-Estar não encontrado"));


        registroBemEstarRepository.save(registroEncontrado);

        return RegistroBemEstarMapper.INSTANCE.registroBemEstarToRegistroBemEstarDTO(registroEncontrado);
    }

    public DelecaoResponseDTO deletar(Long id) throws Exception {
        RegistroBemEstar registroEncontrado = registroBemEstarRepository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Registro de Bem-Estar não encontrado"));

        registroBemEstarRepository.delete(registroEncontrado);
        return new DelecaoResponseDTO("Registro de Bem-Estar deletado com sucesso");
    }
}
