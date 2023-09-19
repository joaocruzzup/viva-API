package br.com.catalisa.viva.service;

import br.com.catalisa.viva.exception.EntidadeNaoEncontradaException;
import br.com.catalisa.viva.model.dto.DelecaoResponseDTO;
import br.com.catalisa.viva.model.dto.InstituicaoDTO;
import br.com.catalisa.viva.model.Instituicao;
import br.com.catalisa.viva.repository.InstituicaoRepository;
import br.com.catalisa.viva.utils.mapper.InstituicaoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InstituicaoService {

    @Autowired
    private InstituicaoRepository instituicaoRepository;

    public List<InstituicaoDTO> listarTodos() {
        List<Instituicao> instituicoes = instituicaoRepository.findAll();
        List<InstituicaoDTO> instituicoesDTO = new ArrayList<>();

        for (Instituicao instituicao : instituicoes) {
            instituicoesDTO.add(InstituicaoMapper.INSTANCE.instituicaoToInstituicaoDTO(instituicao));
        }

        return instituicoesDTO;
    }

    public InstituicaoDTO buscarPorId(Long id) throws Exception {
        Instituicao instituicaoEncontrada = instituicaoRepository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Instituição não encontrada"));
        return InstituicaoMapper.INSTANCE.instituicaoToInstituicaoDTO(instituicaoEncontrada);
    }

    public InstituicaoDTO criar(InstituicaoDTO instituicaoDTO){
        Instituicao instituicao = InstituicaoMapper.INSTANCE.instituicaoDTOToInstituicao(instituicaoDTO);
        instituicaoRepository.save(instituicao);
        return instituicaoDTO;
    }

    public InstituicaoDTO atualizar(Long id, InstituicaoDTO instituicaoDTO) throws Exception {
        Instituicao instituicaoEncontrada = instituicaoRepository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Instituição não encontrada"));


        instituicaoRepository.save(instituicaoEncontrada);

        return InstituicaoMapper.INSTANCE.instituicaoToInstituicaoDTO(instituicaoEncontrada);
    }

    public DelecaoResponseDTO deletar(Long id) throws Exception {
        Instituicao instituicaoEncontrada = instituicaoRepository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Instituição não encontrada"));

        instituicaoRepository.delete(instituicaoEncontrada);
        return new DelecaoResponseDTO("Instituição deletada com sucesso");
    }
}
