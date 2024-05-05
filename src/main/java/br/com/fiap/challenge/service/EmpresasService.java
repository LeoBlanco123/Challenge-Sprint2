package br.com.fiap.challenge.service;


import br.com.fiap.challenge.dto.request.EmpresasRequest;
import br.com.fiap.challenge.dto.response.EmpresasResponse;
import br.com.fiap.challenge.entity.Empresas;
import br.com.fiap.challenge.repository.EmpresasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class EmpresasService implements ServiceDTO<Empresas, EmpresasRequest, EmpresasResponse> {

    @Autowired
    private EmpresasRepository repo;

    @Override
    public Collection<Empresas> findAll() {
        return repo.findAll();
    }

    @Override
    public Collection<Empresas> findAll(Example<Empresas> example) {
        return repo.findAll(example);
    }

    @Override
    public Empresas findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Empresas save(Empresas e) {
        return repo.save(e);
    }

    @Override
    public Empresas toEntity(EmpresasRequest dto) {
        return Empresas.builder()
                .nome(dto.nome())
                .tamanho(dto.tamanho())
                .setor(dto.setor())
                .localizacaoGeografica(dto.localizacaoGeografica())
                .numeroFuncionarios(dto.numeroFuncionarios())
                .tipoEmpresa(dto.tipoEmpresa())
                .cliente(dto.cliente())
                .build();
    }

    @Override
    public EmpresasResponse toResponse(Empresas e) {
        return EmpresasResponse.builder()
                .id(e.getId())
                .nome(e.getNome())
                .tamanho(e.getTamanho())
                .setor(e.getSetor())
                .localizacaoGeografica(e.getLocalizacaoGeografica())
                .numeroFuncionarios(e.getNumeroFuncionarios())
                .tipoEmpresa(e.getTipoEmpresa())
                .build();
    }
}
