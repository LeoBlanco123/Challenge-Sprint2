package br.com.fiap.challenge.resource;

import br.com.fiap.challenge.dto.request.TendenciasGastosRequest;
import br.com.fiap.challenge.dto.response.TendenciasGastosResponse;
import br.com.fiap.challenge.entity.TendenciasGastos;
import br.com.fiap.challenge.repository.EmpresasRepository;
import br.com.fiap.challenge.service.TendenciasGastosService;
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
@RequestMapping(value = "/tendencias")
public class TendenciasGastosResource implements ResourceDTO<TendenciasGastosRequest, TendenciasGastosResponse>{

    @Autowired
    private TendenciasGastosService service;

    @Autowired
    private EmpresasRepository empresasRepository;

    @GetMapping
    public ResponseEntity<Collection<TendenciasGastosResponse>> findAll(
            @RequestParam(name = "ano", required = false) Long ano,
            @RequestParam(name = "gastoMarketing", required = false) Double gastoMarketing,
            @RequestParam(name = "gastoAutomacao", required = false) Double gastoAutomacao,
            @RequestParam(name = "empresas.id", required = false) Long idEmpresas
    ) {
        var empresas = Objects.nonNull(idEmpresas) ? empresasRepository
                .findById(idEmpresas)
                .orElse(null) : null;

        TendenciasGastos tendenciasGastos = TendenciasGastos.builder()
                .ano(ano)
                .gastoMarketing(gastoMarketing)
                .gastoAutomacao(gastoAutomacao)
                .empresa(empresas)
                .build();

        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withIgnoreNullValues()
                .withIgnoreCase();

        Example<TendenciasGastos> example = Example.of(tendenciasGastos, matcher);

        var encontrados = service.findAll(example);
        if (encontrados.isEmpty()) return ResponseEntity.notFound().build();
        var resposta = encontrados.stream()
                .map(service::toResponse)
                .toList();
        return ResponseEntity.ok(resposta);
    }


    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<TendenciasGastosResponse> findById(@PathVariable Long id) {
        var encontrado = service.findById( id );

        if (encontrado == null) return ResponseEntity.notFound().build();

        var resposta = service.toResponse( encontrado );
        return ResponseEntity.ok( resposta );
    }

    @Override
    @Transactional
    @PostMapping
    public ResponseEntity<TendenciasGastosResponse> save(@RequestBody @Valid TendenciasGastosRequest r) {
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
