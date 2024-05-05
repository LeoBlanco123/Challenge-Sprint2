package br.com.fiap.challenge.resource;

import br.com.fiap.challenge.dto.request.HistoricoInteracoesRequest;
import br.com.fiap.challenge.dto.response.HistoricoInteracoesResponse;
import br.com.fiap.challenge.entity.HistoricoInteracoes;
import br.com.fiap.challenge.repository.EmpresasRepository;
import br.com.fiap.challenge.service.HistoricoInteracoesService;
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
@RequestMapping(value = "/historico")
public class HistoricoInteracoesResource implements ResourceDTO<HistoricoInteracoesRequest, HistoricoInteracoesResponse>{

    @Autowired
    private HistoricoInteracoesService service;

    @Autowired
    private EmpresasRepository empresasRepository;

    @GetMapping
    public ResponseEntity<Collection<HistoricoInteracoesResponse>> findAll(
            @RequestParam(name = "propostaNegocios", required = false) String propostaNegocios,
            @RequestParam(name = "contratoAssinado", required = false) String contratoAssinado,
            @RequestParam(name = "feedbackServicosProdutos", required = false) String feedbackServicosProdutos,
            @RequestParam(name = "empresas.id", required = false) Long idEmpresas
    ) {
        var empresas = Objects.nonNull(idEmpresas) ? empresasRepository
                .findById(idEmpresas)
                .orElse(null) : null;

        HistoricoInteracoes historicoInteracoes = HistoricoInteracoes.builder()
                .propostaNegocio(propostaNegocios)
                .contratoAssinado(contratoAssinado)
                .feedbackServicosProdutos(feedbackServicosProdutos)
                .empresa(empresas)
                .build();

        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withIgnoreNullValues()
                .withIgnoreCase();

        Example<HistoricoInteracoes> example = Example.of(historicoInteracoes, matcher);

        var encontrados = service.findAll(example);
        if (encontrados.isEmpty()) return ResponseEntity.notFound().build();
        var resposta = encontrados.stream()
                .map(service::toResponse)
                .toList();
        return ResponseEntity.ok(resposta);
    }


    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<HistoricoInteracoesResponse> findById(@PathVariable Long id) {
        var encontrado = service.findById( id );

        if (encontrado == null) return ResponseEntity.notFound().build();

        var resposta = service.toResponse( encontrado );
        return ResponseEntity.ok( resposta );
    }

    @Override
    @Transactional
    @PostMapping
    public ResponseEntity<HistoricoInteracoesResponse> save(@RequestBody @Valid HistoricoInteracoesRequest r) {
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
