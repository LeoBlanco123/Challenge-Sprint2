package br.com.fiap.challenge.service;

import br.com.fiap.challenge.dto.request.CadastroFuncionarioRequest;
import br.com.fiap.challenge.dto.response.CadastroFuncionarioResponse;
import br.com.fiap.challenge.entity.CadastroFuncionario;
import br.com.fiap.challenge.repository.CadastroFuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CadastroFuncionarioService implements ServiceDTO<CadastroFuncionario, CadastroFuncionarioRequest, CadastroFuncionarioResponse> {

    @Autowired
    private CadastroFuncionarioRepository repo;

    @Autowired
    private CadastradosService cadastradosService;

    @Override
    public Collection<CadastroFuncionario> findAll() {
        return repo.findAll();
    }

    @Override
    public Collection<CadastroFuncionario> findAll(Example<CadastroFuncionario> example) {
        return repo.findAll(example);
    }

    @Override
    public CadastroFuncionario findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public CadastroFuncionario save(CadastroFuncionario e) {
        return repo.save(e);
    }

    @Override
    public CadastroFuncionario toEntity(CadastroFuncionarioRequest dto) {

        var cadastrados = cadastradosService.findById(dto.cadastrado().cnpj());

        return CadastroFuncionario.builder()
                .senhaFuncionario(dto.senhaFuncionario())
                .cargoFuncionario(dto.cargoFuncionario())
                .cadastrado(cadastrados)
                .build();
    }

    @Override
    public CadastroFuncionarioResponse toResponse(CadastroFuncionario e) {

        var cadastrados = cadastradosService.toResponse(e.getCadastrado());

        return CadastroFuncionarioResponse.builder()
                .codFuncionario(e.getCodFuncionario())
                .cargoFuncionario(e.getCargoFuncionario())
                .cadastrado(cadastrados)
                .build();
    }
}
