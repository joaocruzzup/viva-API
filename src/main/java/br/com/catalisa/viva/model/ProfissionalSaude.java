package br.com.catalisa.viva.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfissionalSaude {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String especializacao;
    private String disponibilidade;
    private Double precoConsulta;
    private String contato;
    private String localizacao;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    @OneToMany(mappedBy = "profissionalSaude")
    private List<Avaliacao> avaliacoes;

    @OneToMany(mappedBy = "profissionalSaude")
    private List<Consulta> consultas;
}
