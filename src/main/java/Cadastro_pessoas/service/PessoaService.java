package Cadastro_pessoas.service;

import Cadastro_pessoas.domain.pessoa.Pessoa;
import Cadastro_pessoas.domain.pessoa.PessoaRepository;
import Cadastro_pessoas.domain.pessoa.dto.PessoaDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public List<Pessoa> listar() {
        return pessoaRepository.findAll();
    }

    public Optional<Pessoa> buscarPorId(Long id) {
        return pessoaRepository.findById(id);
    }

    public Pessoa adicionar(PessoaDto dto) {
        Pessoa pessoa = new Pessoa(dto);
        return pessoaRepository.save(pessoa);
    }

    public Optional<Pessoa> alterar(Long id, Pessoa novaPessoa) {
        return pessoaRepository.findById(id).map(pessoa -> {
            pessoa.setNome(novaPessoa.getNome());
            pessoa.setEmail(novaPessoa.getEmail());
            return pessoaRepository.save(pessoa);
        });
    }

    public boolean deletar(Long id) {
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);
        if (pessoa.isPresent()) {
            pessoaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
