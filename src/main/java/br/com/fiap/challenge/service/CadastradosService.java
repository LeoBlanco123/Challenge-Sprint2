package br.com.fiap.challenge.service;

import br.com.fiap.challenge.dto.request.CadastradosRequest;
import br.com.fiap.challenge.dto.response.CadastradosResponse;
import br.com.fiap.challenge.entity.Cadastrados;
import br.com.fiap.challenge.repository.CadastradosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CadastradosService implements ServiceDTO<Cadastrados, CadastradosRequest, CadastradosResponse> {

    @Autowired
    private CadastradosRepository repo;

    @Override
    public Collection<Cadastrados> findAll() {
        return repo.findAll();
    }

    @Override
    public Collection<Cadastrados> findAll(Example<Cadastrados> example) {
        return repo.findAll(example);
    }

    @Override
    public Cadastrados findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Cadastrados save(Cadastrados e) {
        return repo.save(e);
    }

    @Override
    public Cadastrados toEntity(CadastradosRequest dto) {
        return Cadastrados.builder()
                .senha(dto.senha())
                .nome(dto.nome())
                .build();
    }

    @Override
    public CadastradosResponse toResponse(Cadastrados e) {
        return CadastradosResponse.builder()
                .cnpj(e.getCnpj())
                .nome(e.getNome())
                .build();
    }
}
