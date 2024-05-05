package br.com.fiap.challenge.resource;

import br.com.fiap.challenge.dto.request.DesempenhoFinanceiroRequest;
import br.com.fiap.challenge.dto.response.DesempenhoFinanceiroResponse;
import br.com.fiap.challenge.entity.DesempenhoFinanceiro;
import br.com.fiap.challenge.repository.EmpresasRepository;
import br.com.fiap.challenge.service.DesempenhoFinanceiroService;
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
@RequestMapping(value = "/desempenho")
public class DesempenhoFinanceiroResource implements ResourceDTO<DesempenhoFinanceiroRequest, DesempenhoFinanceiroResponse>{

    @Autowired
    private DesempenhoFinanceiroService service;

    @Autowired
    private EmpresasRepository empresasRepository;

    @GetMapping
    public ResponseEntity<Collection<DesempenhoFinanceiroResponse>> findAll(
            @RequestParam(name = "receita", required = false) Double receita,
            @RequestParam(name = "lucro", required = false) Double lucro,
            @RequestParam(name = "crecimento", required = false) Double crecimento,
            @RequestParam(name = "empresas.id", required = false) Long idEmpresas
    ){
        var empresas = Objects.nonNull( idEmpresas ) ? empresasRepository
                .findById( idEmpresas )
                .orElse( null ) : null;

        DesempenhoFinanceiro desempenhoFinanceiro = DesempenhoFinanceiro.builder()
                .receita( receita )
                .lucro( lucro )
                .crescimento( crecimento)
                .empresa( empresas )
                .build();

        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withIgnoreNullValues()
                .withIgnoreCase();

        Example<DesempenhoFinanceiro> example = Example.of( desempenhoFinanceiro, matcher );

        var encontrados = service.findAll( example );
        if (encontrados.isEmpty()) return ResponseEntity.notFound().build();
        var resposta = encontrados.stream()
                .map( service::toResponse )
                .toList();
        return ResponseEntity.ok( resposta );
    }

    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<DesempenhoFinanceiroResponse> findById(@PathVariable Long id) {
        var encontrado = service.findById( id );

        if (encontrado == null) return ResponseEntity.notFound().build();

        var resposta = service.toResponse( encontrado );
        return ResponseEntity.ok( resposta );
    }

    @Override
    @Transactional
    @PostMapping
    public ResponseEntity<DesempenhoFinanceiroResponse> save(@RequestBody @Valid DesempenhoFinanceiroRequest r) {
        var entity = service.toEntity( r );
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
