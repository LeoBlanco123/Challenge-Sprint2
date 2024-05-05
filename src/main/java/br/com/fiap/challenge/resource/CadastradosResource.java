package br.com.fiap.challenge.resource;

import br.com.fiap.challenge.dto.request.CadastradosRequest;
import br.com.fiap.challenge.dto.response.CadastradosResponse;
import br.com.fiap.challenge.entity.Cadastrados;
import br.com.fiap.challenge.service.CadastradosService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;

@RestController
@RequestMapping(value = "/cadastrados")
public class CadastradosResource implements ResourceDTO<CadastradosRequest, CadastradosResponse>{

    @Autowired
    private CadastradosService service;

    @GetMapping
    public ResponseEntity<Collection<CadastradosResponse>> findAll(
            @RequestParam(name = "cnpj", required = false) String cnpj,
            @RequestParam(name = "nome", required = false) String nome
    ){

        Cadastrados cadastrados = Cadastrados.builder()
                .cnpj(cnpj)
                .nome(nome)
                .build();

        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withIgnoreNullValues()
                .withIgnoreCase();

        Example<Cadastrados> example = Example.of( cadastrados, matcher );

        var encontrados = service.findAll( example );
        if (encontrados.isEmpty()) return ResponseEntity.notFound().build();
        var resposta = encontrados.stream()
                .map( service::toResponse )
                .toList();
        return ResponseEntity.ok( resposta );
    }

    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<CadastradosResponse> findById(@PathVariable Long id) {
        var encontrado = service.findById( id );

        if (encontrado == null) return ResponseEntity.notFound().build();

        var resposta = service.toResponse( encontrado );
        return ResponseEntity.ok( resposta );
    }

    @Override
    @Transactional
    @PostMapping
    public ResponseEntity<CadastradosResponse> save(@RequestBody @Valid CadastradosRequest r) {
        var entity = service.toEntity( r );
        var saved = service.save( entity );
        var resposta = service.toResponse( saved );

        var uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path( "/{id}" )
                .buildAndExpand( saved.getCnpj() )
                .toUri();
        return ResponseEntity.created( uri ).body( resposta );
    }
}
