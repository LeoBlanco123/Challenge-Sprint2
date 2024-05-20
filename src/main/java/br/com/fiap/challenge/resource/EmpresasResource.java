package br.com.fiap.challenge.resource;

import br.com.fiap.challenge.dto.request.EmpresasRequest;
import br.com.fiap.challenge.dto.response.EmpresasResponse;
import br.com.fiap.challenge.entity.Empresas;
import br.com.fiap.challenge.service.EmpresasService;
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
@CrossOrigin(origins = "*")
@RequestMapping(value = "/empresas")
public class EmpresasResource implements ResourceDTO<EmpresasRequest, EmpresasResponse>{

    @Autowired
    private EmpresasService service;

    @GetMapping
    public ResponseEntity<Collection<EmpresasResponse>> findAll(
            @RequestParam(name = "nome", required = false) String nome,
            @RequestParam(name = "tamanho", required = false) String tamaho,
            @RequestParam(name = "setor", required = false) String setor,
            @RequestParam(name = "localizacaoGeografica", required = false) String localizacaoGeografica,
            @RequestParam(name = "numeroFuncionarios", required = false) Long numeroFuncionarios,
            @RequestParam(name = "tipoEmpresa", required = false) String tipoEmpresa,
            @RequestParam(name = "cliente", required = false) Boolean cliente
    ){

        Empresas empresas = Empresas.builder()
                .nome(nome)
                .tamanho(tamaho)
                .setor(setor)
                .localizacaoGeografica(localizacaoGeografica)
                .numeroFuncionarios(numeroFuncionarios)
                .tipoEmpresa(tipoEmpresa)
                .cliente(cliente)
                .build();

        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withIgnoreNullValues()
                .withIgnoreCase();

        Example<Empresas> example = Example.of( empresas, matcher );

        var encontrados = service.findAll( example );
        if (encontrados.isEmpty()) return ResponseEntity.notFound().build();
        var resposta = encontrados.stream()
                .map( service::toResponse )
                .toList();
        return ResponseEntity.ok( resposta );
    }

    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<EmpresasResponse> findById(@PathVariable Long id ) {
        var encontrado = service.findById( id );

        if (encontrado == null) return ResponseEntity.notFound().build();

        var resposta = service.toResponse( encontrado );
        return ResponseEntity.ok( resposta );
    }

    @Override
    @Transactional
    @PostMapping
    public ResponseEntity<EmpresasResponse> save(@RequestBody @Valid EmpresasRequest r) {
        var entity = service.toEntity(r);
        var saved = service.save( entity );
        var resposta = service.toResponse( saved );

        var uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path( "/{id}" )
                .buildAndExpand( saved.getId() )
                .toUri();
        return ResponseEntity.created( uri ).body( resposta );
    }
}
