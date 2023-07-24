package com.algaworks.algatransito.domain.service;

import com.algaworks.algatransito.domain.exception.EntidadeNaoEncontrada;
import com.algaworks.algatransito.domain.exception.NegocioException;
import com.algaworks.algatransito.domain.model.Proprietario;
import com.algaworks.algatransito.domain.model.StatusVeiculo;
import com.algaworks.algatransito.domain.model.Veiculo;
import com.algaworks.algatransito.domain.repository.VeiculoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@AllArgsConstructor
@Service
public class RegistroVeiculoService {

    private  final VeiculoRepository veiculoRepository;
    private final RegistroProprietarioService registroProprietarioService;


    public Veiculo buscar(Long veiculoId){
        return veiculoRepository.findById(veiculoId)
                .orElseThrow(()-> new EntidadeNaoEncontrada("Veículo não encontrado!"));
    }






    @Transactional
    public Veiculo cadastrar(Veiculo newvehicle){

        if(newvehicle.getId() != null) {
          throw new  NegocioException("Veiculo a ser cadastrado NÃO pode possuir código!");
        }


        boolean placaEmUso = veiculoRepository.findByPlaca(newvehicle.getPlaca())
                .filter(veiculo -> !veiculo.equals(newvehicle))
                .isPresent();

        if(placaEmUso) {
            throw new NegocioException("Já existe um veículo com essa placa!");
        }

        Proprietario proprietario = registroProprietarioService
                .buscar(newvehicle.getProprietario().getId());

        newvehicle.setProprietario(proprietario);
        newvehicle.setStatus(StatusVeiculo.REGULAR);
        newvehicle.setDataCadastro(OffsetDateTime.now());

        return veiculoRepository.save(newvehicle);

    }


}
