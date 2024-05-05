package br.com.fiap.challenge.resource;

import br.com.fiap.challenge.dto.request.CadastroFuncionarioRequest;
import br.com.fiap.challenge.dto.response.CadastroFuncionarioResponse;
import br.com.fiap.challenge.entity.CadastroFuncionario;
import br.com.fiap.challenge.repository.CadastradosRepository;
import br.com.fiap.challenge.service.CadastroFuncionarioService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;
import java.util.Objects;

@RestController
@RequestMapping(value = "/cadastroFuncionario")
public class CadastroFuncionarioResource implements ResourceDTO<CadastroFuncionarioRequest, CadastroFuncionarioResponse>{

    @Autowired
    private CadastroFuncionarioService service;

    @Autowired
    private CadastradosRepository cadastradosRepository;

    @GetMapping
    public ResponseEntity<Collection<CadastroFuncionarioResponse>> findAll(
            @RequestParam(name = "cargoFuncionario", required = false) String cargoFuncionario,
            @RequestParam(name = "cadastrados.cnpj", required = false) Long cnpjCadastrados
    ){
        var cadastrados = Objects.nonNull( cnpjCadastrados ) ? cadastradosRepository
                .findById( cnpjCadastrados )
                .orElse( null ) : null;

        CadastroFuncionario cadastroFuncionario = CadastroFuncionario.builder()
                .cargoFuncionario(cargoFuncionario)
                .cadastrado(cadastrados)
                .build();

        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withIgnoreNullValues()
                .withIgnoreCase();

        Example<CadastroFuncionario> example = Example.of( cadastroFuncionario, matcher );

        var encontrados = service.findAll( example );

        if (encontrados.isEmpty()) return ResponseEntity.notFound().build();

        var resposta = encontrados.stream()
                .map( service::toResponse )
                .toList();
        return ResponseEntity.ok( resposta );
    }

    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<CadastroFuncionarioResponse> findById(@PathVariable Long id) {
        var encontrado = service.findById( id );

        if (encontrado == null) return ResponseEntity.notFound().build();

        var resposta = service.toResponse( encontrado );
        return ResponseEntity.ok( resposta );
    }

    @Override
    @Transactional
    @PostMapping
    public ResponseEntity<CadastroFuncionarioResponse> save(@RequestBody @Valid CadastroFuncionarioRequest r) {
        var entity = service.toEntity( r );
        var saved = service.save( entity );
        var resposta = service.toResponse( saved );

        var uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path( "/{id}" )
                .buildAndExpand( saved.getCodFuncionario() )
                .toUri();
        return ResponseEntity.created( uri ).body( resposta );
    }
}
