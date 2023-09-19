package br.com.catalisa.viva.service;

import br.com.catalisa.viva.exception.EntidadeNaoEncontradaException;
import br.com.catalisa.viva.model.dto.AvaliacaoRequestDTO;
import br.com.catalisa.viva.model.dto.AvaliacaoResponseDTO;
import br.com.catalisa.viva.model.dto.DelecaoResponseDTO;
import br.com.catalisa.viva.model.Avaliacao;
import br.com.catalisa.viva.repository.AvaliacaoRepository;
import br.com.catalisa.viva.utils.mapper.AvaliacaoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    public List<AvaliacaoResponseDTO> listarTodos() {
        List<Avaliacao> avaliacoes = avaliacaoRepository.findAll();
        List<AvaliacaoResponseDTO> avaliacoesDTO = new ArrayList<>();

        for (Avaliacao avaliacao: avaliacoes) {
            avaliacoesDTO.add(AvaliacaoMapper.INSTANCE.avaliacaoToAvaliacaoResponseDTO(avaliacao));
        }

        return avaliacoesDTO;
    }

    public AvaliacaoResponseDTO buscarPorId(Long id) throws Exception {
        Avaliacao avaliacaoEncontrada = avaliacaoRepository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Avaliação não encontrada"));
        return AvaliacaoMapper.INSTANCE.avaliacaoToAvaliacaoResponseDTO(avaliacaoEncontrada);
    }

    public AvaliacaoResponseDTO criar(AvaliacaoRequestDTO avaliacaoDTO) throws Exception {
        Avaliacao avaliacao = AvaliacaoMapper.INSTANCE.avaliacaoRequestDTOToAvaliacao(avaliacaoDTO);
        avaliacaoRepository.save(avaliacao);
        return AvaliacaoMapper.INSTANCE.avaliacaoToAvaliacaoResponseDTO(avaliacao);
    }

    public AvaliacaoResponseDTO atualizar(Long id, AvaliacaoResponseDTO avaliacaoDTO) throws Exception {
        Avaliacao avaliacaoEncontrada = avaliacaoRepository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Avaliação não encontrada"));

        if (avaliacaoDTO.getPontuacao() >= 0){
            avaliacaoEncontrada.setPontuacao(avaliacaoDTO.getPontuacao());
        }
        if (avaliacaoDTO.getComentario() != null){
            avaliacaoEncontrada.setComentario(avaliacaoDTO.getComentario());
        }

        avaliacaoRepository.save(avaliacaoEncontrada);

        return AvaliacaoMapper.INSTANCE.avaliacaoToAvaliacaoResponseDTO(avaliacaoEncontrada);
    }

    public DelecaoResponseDTO deletar(Long id) throws Exception {
        Avaliacao avaliacaoEncontrada = avaliacaoRepository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Avaliação não encontrada"));

        avaliacaoRepository.delete(avaliacaoEncontrada);
        return new DelecaoResponseDTO("Avaliação deletada com sucesso");
    }


}
