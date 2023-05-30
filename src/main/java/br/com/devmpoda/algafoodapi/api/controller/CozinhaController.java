package br.com.devmpoda.algafoodapi.api.controller;

import br.com.devmpoda.algafoodapi.api.model.CozinhasXmlWrapper;
import br.com.devmpoda.algafoodapi.domain.model.Cozinha;
import br.com.devmpoda.algafoodapi.domain.repository.CozinhaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping(value = "/cozinhas", produces = MediaType.APPLICATION_JSON_VALUE)
public class CozinhaController {
    @Autowired
    private CozinhaRepository cozinhaRepository;

    @GetMapping
    public List<Cozinha> listar() {
        return cozinhaRepository.todas();
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public CozinhasXmlWrapper listarXml() {
        return new CozinhasXmlWrapper(cozinhaRepository.todas());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Cozinha> buscar(@PathVariable Long id) {
        Cozinha cozinha = cozinhaRepository.porId(id);
        if (cozinha != null) {
            return ResponseEntity.ok(cozinhaRepository.porId(id));
        }
        return ResponseEntity.notFound().build();
        //return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cozinha adicionar(@RequestBody Cozinha cozinha){
        return cozinhaRepository.adicionar(cozinha);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cozinha> atualizar(@PathVariable Long id, @RequestBody Cozinha cozinhaBody){
        Cozinha cozinhaRecuperada = cozinhaRepository.porId(id);

        //cozinhaRecuperada.setNome(cozinha.getNome());
        if (cozinhaRecuperada != null){
            BeanUtils.copyProperties(cozinhaBody, cozinhaRecuperada, "id");
            cozinhaRepository.adicionar(cozinhaRecuperada);

            return ResponseEntity.ok(cozinhaRecuperada);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Cozinha> excluir(@PathVariable Long id){
        try {
            Cozinha cozinhaRecuperada = cozinhaRepository.porId(id);

            if (cozinhaRecuperada != null){
                cozinhaRepository.remover(cozinhaRecuperada);
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.notFound().build();
        }catch(DataIntegrityViolationException ex){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

}
