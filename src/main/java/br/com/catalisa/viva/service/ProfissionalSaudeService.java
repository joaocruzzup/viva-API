package br.com.catalisa.viva.service;

import br.com.catalisa.viva.exception.EntidadeNaoEncontradaException;
import br.com.catalisa.viva.model.dto.DelecaoResponseDTO;
import br.com.catalisa.viva.model.dto.ProfissionalSaudeDTO;
import br.com.catalisa.viva.model.ProfissionalSaude;
import br.com.catalisa.viva.repository.ProfissionalSaudeRepository;
import br.com.catalisa.viva.utils.mapper.ProfissionalSaudeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfissionalSaudeService {

    @Autowired
    private ProfissionalSaudeRepository profissionalSaudeRepository;

    public List<ProfissionalSaudeDTO> listarTodos() {
        List<ProfissionalSaude> profissionais = profissionalSaudeRepository.findAll();
        List<ProfissionalSaudeDTO> profissionaisDTO = new ArrayList<>();

        for (ProfissionalSaude profissional : profissionais) {
            profissionaisDTO.add(ProfissionalSaudeMapper.INSTANCE.profissionalSaudeToProfissionalSaudeDTO(profissional));
        }

        return profissionaisDTO;
    }

    public ProfissionalSaudeDTO buscarPorId(Long id) throws Exception {
        ProfissionalSaude profissionalEncontrado = profissionalSaudeRepository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Profissional de Saúde não encontrado"));
        return ProfissionalSaudeMapper.INSTANCE.profissionalSaudeToProfissionalSaudeDTO(profissionalEncontrado);
    }

    public ProfissionalSaudeDTO criar(ProfissionalSaudeDTO instituicaoDTO){
        ProfissionalSaude profissionalSaude = ProfissionalSaudeMapper.INSTANCE.profissionalSaudeDTOToProfissionalSaude(instituicaoDTO);
        profissionalSaudeRepository.save(profissionalSaude);
        return instituicaoDTO;
    }

    public ProfissionalSaudeDTO atualizar(Long id, ProfissionalSaudeDTO profissionalDTO) throws Exception {
        ProfissionalSaude profissionalEncontrado = profissionalSaudeRepository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Profissional de Saúde não encontrado"));


        profissionalSaudeRepository.save(profissionalEncontrado);

        return ProfissionalSaudeMapper.INSTANCE.profissionalSaudeToProfissionalSaudeDTO(profissionalEncontrado);
    }

    public DelecaoResponseDTO deletar(Long id) throws Exception {
        ProfissionalSaude profissionalEncontrado = profissionalSaudeRepository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Profissional de Saúde não encontrado"));

        profissionalSaudeRepository.delete(profissionalEncontrado);
        return new DelecaoResponseDTO("Profissional de Saúde deletado com sucesso");
    }
}
