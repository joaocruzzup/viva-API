package br.com.catalisa.viva.service;

import br.com.catalisa.viva.exception.EntidadeNaoEncontradaException;
import br.com.catalisa.viva.model.dto.ConsultaDTO;
import br.com.catalisa.viva.model.dto.DelecaoResponseDTO;
import br.com.catalisa.viva.model.Consulta;
import br.com.catalisa.viva.repository.ConsultaRepository;
import br.com.catalisa.viva.utils.mapper.ConsultaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    public List<ConsultaDTO> listarTodos() {
        List<Consulta> consultas = consultaRepository.findAll();
        List<ConsultaDTO> consultasDTO = new ArrayList<>();

        for (Consulta consulta : consultas) {
            consultasDTO.add(ConsultaMapper.INSTANCE.consultaToConsultaDTO(consulta));
        }

        return consultasDTO;
    }

    public ConsultaDTO buscarPorId(Long id) throws EntidadeNaoEncontradaException {
        Consulta consultaEncontrada = consultaRepository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Consulta não encontrada"));
        return ConsultaMapper.INSTANCE.consultaToConsultaDTO(consultaEncontrada);
    }

    public ConsultaDTO criar(ConsultaDTO consultaDTO){
        Consulta consulta = ConsultaMapper.INSTANCE.consultaDTOToConsulta(consultaDTO);
        consultaRepository.save(consulta);
        return consultaDTO;
    }

    public ConsultaDTO atualizar(Long id, ConsultaDTO consultaDTO) throws EntidadeNaoEncontradaException {
        Consulta consultaEncontrada = consultaRepository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Consulta não encontrada"));

        consultaRepository.save(consultaEncontrada);

        return ConsultaMapper.INSTANCE.consultaToConsultaDTO(consultaEncontrada);
    }

    public DelecaoResponseDTO deletar(Long id) throws EntidadeNaoEncontradaException {
        Consulta consultaEncontrada = consultaRepository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Consulta não encontrada"));

        consultaRepository.delete(consultaEncontrada);
        return new DelecaoResponseDTO("Consulta deletada com sucesso");
    }
}
