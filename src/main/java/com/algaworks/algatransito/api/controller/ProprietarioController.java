package com.algaworks.algatransito.api.controller;

import com.algaworks.algatransito.domain.model.Proprietario;
import com.algaworks.algatransito.domain.repository.ProprietarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/proprietarios")
public class ProprietarioController {


    // @Autowired

    private final ProprietarioRepository proprietarioRepository;

   /* public ProprietarioController(ProprietarioRepository proprietarioRepository) {
        this.proprietarioRepository = proprietarioRepository;
    }*/

    //  @PersistenceContext
   // private EntityManager manager;

    @GetMapping
    public List<Proprietario> lista(){
    /*TypedQuery<Proprietario> query = manager.createQuery("from Proprietario", Proprietario.class);
    return query.getResultList();*/

    return proprietarioRepository.findByNome("Maria");
            // proprietarioRepository.findAll();
           }

    @GetMapping("/{proprietarioId}")
    public ResponseEntity<Proprietario> buscar(@PathVariable Long proprietarioId){
        return proprietarioRepository.findById(proprietarioId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());





       /* if(proprietario.isPresent()) {
            return ResponseEntity.ok(proprietario.get());
        }

        return ResponseEntity.notFound().build();*/
    }




}
