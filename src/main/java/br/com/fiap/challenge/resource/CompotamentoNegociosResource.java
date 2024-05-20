package br.com.fiap.challenge.resource;

import br.com.fiap.challenge.dto.request.ComportamentoNegociosRequest;
import br.com.fiap.challenge.dto.response.ComportamentoNegociosResponse;
import br.com.fiap.challenge.entity.ComportamentoNegocios;
import br.com.fiap.challenge.repository.EmpresasRepository;
import br.com.fiap.challenge.service.ComportamentoNegociosService;
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
@CrossOrigin(origins = "*")
@RequestMapping(value = "/comportamento")
public class CompotamentoNegociosResource implements ResourceDTO<ComportamentoNegociosRequest, ComportamentoNegociosResponse>{

    @Autowired
    private ComportamentoNegociosService service;

    @Autowired
    private EmpresasRepository empresasRepository;

    @GetMapping
    public ResponseEntity<Collection<ComportamentoNegociosResponse>> findAll(
            @RequestParam(name = "interacoesPlataforma", required = false) Long interacoesPlataforma,
            @RequestParam(name = "frequenciaUso", required = false) Long frequenciaUso,
            @RequestParam(name = "feedback", required = false) String feedback,
            @RequestParam(name = "usoRecursosEspecificos", required = false) String usoRecursosEspecificos,
            @RequestParam(name = "empresas.id", required = false) Long IdEmpresas
    ){
        var empresas = Objects.nonNull( IdEmpresas ) ? empresasRepository
                .findById( IdEmpresas )
                .orElse( null ) : null;

        ComportamentoNegocios comportamentoNegocios = ComportamentoNegocios.builder()
                .interacoesPlataforma( interacoesPlataforma )
                .frequenciaUso( frequenciaUso )
                .feedback( feedback )
                .usoRecursosEspecificos( usoRecursosEspecificos )
                .empresa( empresas )
                .build();

        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withIgnoreNullValues()
                .withIgnoreCase();

        Example<ComportamentoNegocios> example = Example.of( comportamentoNegocios, matcher );

        var encontrados = service.findAll( example );
        if (encontrados.isEmpty()) return ResponseEntity.notFound().build();
        var resposta = encontrados.stream()
                .map( service::toResponse )
                .toList();
        return ResponseEntity.ok( resposta );
    }

    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<ComportamentoNegociosResponse> findById(@PathVariable Long id) {
        var encontrado = service.findById( id );

        if (encontrado == null) return ResponseEntity.notFound().build();

        var resposta = service.toResponse( encontrado );
        return ResponseEntity.ok( resposta );
    }

    @Override
    @Transactional
    @PostMapping
    public ResponseEntity<ComportamentoNegociosResponse> save(@RequestBody @Valid ComportamentoNegociosRequest r) {
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
