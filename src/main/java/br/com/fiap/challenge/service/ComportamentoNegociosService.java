package br.com.fiap.challenge.service;

import br.com.fiap.challenge.dto.request.ComportamentoNegociosRequest;
import br.com.fiap.challenge.dto.response.ComportamentoNegociosResponse;
import br.com.fiap.challenge.entity.ComportamentoNegocios;
import br.com.fiap.challenge.repository.ComportamentoNegociosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ComportamentoNegociosService implements ServiceDTO<ComportamentoNegocios, ComportamentoNegociosRequest, ComportamentoNegociosResponse> {

    @Autowired
    private ComportamentoNegociosRepository repo;

    @Autowired
    private EmpresasService empresasService;

    @Override
    public Collection<ComportamentoNegocios> findAll() {
        return repo.findAll();
    }

    @Override
    public Collection<ComportamentoNegocios> findAll(Example<ComportamentoNegocios> example) {
        return repo.findAll(example);
    }

    @Override
    public ComportamentoNegocios findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public ComportamentoNegocios save(ComportamentoNegocios e) {
        return repo.save(e);
    }

    @Override
    public ComportamentoNegocios toEntity(ComportamentoNegociosRequest dto) {

        var empresa = empresasService.findById(dto.empresa().id());

        return ComportamentoNegocios.builder()
                .interacoesPlataforma(dto.interacoesPlataforma())
                .frequenciaUso(dto.frequenciaUso())
                .feedback(dto.feedback())
                .usoRecursosEspecificos(dto.usoRecursosEspecificos())
                .empresa(empresa)
                .build();
    }

    @Override
    public ComportamentoNegociosResponse toResponse(ComportamentoNegocios e) {

        var empresa = empresasService.toResponse(e.getEmpresa());

        return ComportamentoNegociosResponse.builder()
                .id(e.getId())
                .interacoesPlataforma(e.getInteracoesPlataforma())
                .frequenciaUso(e.getFrequenciaUso())
                .feedback(e.getFeedback())
                .usoRecursosEspecificos(e.getUsoRecursosEspecificos())
                .empresa(empresa)
                .build();
    }
}
