package br.com.fiap.challenge.service;

import br.com.fiap.challenge.dto.request.DesempenhoFinanceiroRequest;
import br.com.fiap.challenge.dto.response.DesempenhoFinanceiroResponse;
import br.com.fiap.challenge.entity.DesempenhoFinanceiro;
import br.com.fiap.challenge.repository.DesempenhoFinanceiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class DesempenhoFinanceiroService implements ServiceDTO<DesempenhoFinanceiro, DesempenhoFinanceiroRequest, DesempenhoFinanceiroResponse> {

    @Autowired
    private DesempenhoFinanceiroRepository repo;

    @Autowired
    private EmpresasService empresasService;

    @Override
    public Collection<DesempenhoFinanceiro> findAll() {
        return repo.findAll();
    }

    @Override
    public Collection<DesempenhoFinanceiro> findAll(Example<DesempenhoFinanceiro> example) {
        return repo.findAll(example);
    }

    @Override
    public DesempenhoFinanceiro findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public DesempenhoFinanceiro save(DesempenhoFinanceiro e) {
        return repo.save(e);
    }

    @Override
    public DesempenhoFinanceiro toEntity(DesempenhoFinanceiroRequest dto) {

        var empresa = empresasService.findById(dto.empresa().id());

        return DesempenhoFinanceiro.builder()
                .receita(dto.receita())
                .lucro(dto.lucro())
                .crescimento(dto.crescimento())
                .empresa(empresa)
                .build();
    }

    @Override
    public DesempenhoFinanceiroResponse toResponse(DesempenhoFinanceiro e) {

        var empresa = empresasService.toResponse(e.getEmpresa());

        return DesempenhoFinanceiroResponse.builder()
                .id(e.getId())
                .receita(e.getReceita())
                .lucro(e.getLucro())
                .crescimento(e.getCrescimento())
                .empresas(empresa)
                .build();
    }
}
