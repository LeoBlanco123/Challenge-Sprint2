package br.com.fiap.challenge.service;

import br.com.fiap.challenge.dto.request.TendenciasGastosRequest;
import br.com.fiap.challenge.dto.response.TendenciasGastosResponse;
import br.com.fiap.challenge.entity.TendenciasGastos;
import br.com.fiap.challenge.repository.TendenciasGastosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class TendenciasGastosService implements ServiceDTO<TendenciasGastos, TendenciasGastosRequest, TendenciasGastosResponse> {

    @Autowired
    private TendenciasGastosRepository repo;

    @Autowired
    private EmpresasService empresasService;

    @Override
    public Collection<TendenciasGastos> findAll() {
        return repo.findAll();
    }

    @Override
    public Collection<TendenciasGastos> findAll(Example<TendenciasGastos> example) {
        return repo.findAll(example);
    }

    @Override
    public TendenciasGastos findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public TendenciasGastos save(TendenciasGastos e) {
        return repo.save(e);
    }

    @Override
    public TendenciasGastos toEntity(TendenciasGastosRequest dto) {

        var empresa = empresasService.findById(dto.empresa().id());

        return TendenciasGastos.builder()
                .ano(dto.ano())
                .gastoMarketing(dto.gastoMarketing())
                .gastoAutomacao(dto.gastoAutomacao())
                .empresa(empresa)
                .build();
    }

    @Override
    public TendenciasGastosResponse toResponse(TendenciasGastos e) {

        var empresa = empresasService.toResponse(e.getEmpresa());

        return TendenciasGastosResponse.builder()
                .id_TendenciaGasto(e.getId_TendenciaGasto())
                .ano(e.getAno())
                .gastoMarketing(e.getGastoMarketing())
                .gastoAutomacao(e.getGastoAutomacao())
                .empresa(empresa)
                .build();
    }
}
