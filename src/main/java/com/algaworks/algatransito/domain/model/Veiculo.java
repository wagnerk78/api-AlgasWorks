package com.algaworks.algatransito.domain.model;

import com.algaworks.algatransito.domain.exception.NegocioException;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Veiculo {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    // @Valid
    // @ConvertGroup(from = Default.class, to = ValidationGroups.ProprietarioId.class)
    @ManyToOne
    // @JoinColumn(name = "proprietario_id")
    // @NotNull
    private Proprietario proprietario;
    // @NotBlank
    private String marca;
    // @NotBlank
    // PLACA PODE SER (XXX0000 OU XXX0X00)
    // @Pattern(regexp = "[A-Z]{3}[0-9][0-9A-Z][0-9]{2}")
    private String placa;
    // @NotBlank
    private String modelo;


    // @JsonProperty(access = Access.READ_ONLY)
    @Enumerated(EnumType.STRING)
    private StatusVeiculo status;
    // @JsonProperty(access = Access.READ_ONLY)
    private OffsetDateTime dataCadastro;
    // @JsonProperty(access = Access.READ_ONLY)
    private OffsetDateTime dataApreensao;

    @OneToMany(mappedBy = "veiculo", cascade = CascadeType.ALL)
    private List<Autuacao> autuacoes = new ArrayList<>();


    public Autuacao adicionarAutuacao(Autuacao autuacao) {
        autuacao.setDataOcorrencia(OffsetDateTime.now());
        autuacao.setVeiculo(this);
        getAutuacoes().add(autuacao);

        return autuacao;
    }

    public void apreender() {

        if(estaApreendido()) {
            throw new NegocioException("Veículo já está apreendido");
        }

        setStatus(StatusVeiculo.APREENDIDO);
        setDataApreensao(OffsetDateTime.now());
    }


    public void removerApreensao() {

        if(naoEstaApreendido()) {
            throw new NegocioException("Veículo está regular! ");
        }

        setStatus(StatusVeiculo.REGULAR);
        setDataApreensao(null);
    }




    public boolean estaApreendido() {
        return StatusVeiculo.APREENDIDO.equals(getStatus());
    }

    public boolean naoEstaApreendido() {
        return (!estaApreendido());
    }



}
