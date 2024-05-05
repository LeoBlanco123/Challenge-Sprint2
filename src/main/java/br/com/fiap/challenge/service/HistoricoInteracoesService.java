package br.com.fiap.challenge.service;

import br.com.fiap.challenge.dto.request.HistoricoInteracoesRequest;
import br.com.fiap.challenge.dto.response.HistoricoInteracoesResponse;
import br.com.fiap.challenge.entity.HistoricoInteracoes;
import br.com.fiap.challenge.repository.HistoricoInteracoesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;


@Service
public class HistoricoInteracoesService implements ServiceDTO<HistoricoInteracoes, HistoricoInteracoesRequest, HistoricoInteracoesResponse> {

    @Autowired
    private HistoricoInteracoesRepository repo;

    @Autowired
    private EmpresasService empresasService;

    @Override
    public Collection<HistoricoInteracoes> findAll() {
        return repo.findAll();
    }

    @Override
    public Collection<HistoricoInteracoes> findAll(Example<HistoricoInteracoes> example) {
        return repo.findAll(example);
    }

    @Override
    public HistoricoInteracoes findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public HistoricoInteracoes save(HistoricoInteracoes e) {
        return repo.save(e);
    }

    @Override
    public HistoricoInteracoes toEntity(HistoricoInteracoesRequest dto) {

        var empresa = empresasService.findById(dto.empresa().id());

        return HistoricoInteracoes.builder()
                .propostaNegocio(dto.propostaNegocio())
                .contratoAssinado(dto.contratoAssinado())
                .feedbackServicosProdutos(dto.feedbackServicosProdutos())
                .empresa(empresa)
                .build();
    }

    @Override
    public HistoricoInteracoesResponse toResponse(HistoricoInteracoes e) {

        var empresa = empresasService.toResponse(e.getEmpresa());

        return HistoricoInteracoesResponse.builder()
                .id(e.getId())
                .propostaNegocio(e.getPropostaNegocio())
                .contratoAssinado(e.getContratoAssinado())
                .feedbackServicosProdutos(e.getFeedbackServicosProdutos())
                .empresa(empresa)
                .build();
    }
}
