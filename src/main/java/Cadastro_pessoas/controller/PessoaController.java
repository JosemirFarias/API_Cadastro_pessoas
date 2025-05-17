package Cadastro_pessoas.controller;

import Cadastro_pessoas.domain.pessoa.Pessoa;
import Cadastro_pessoas.domain.pessoa.PessoaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/person")
public class PessoaController {

    private final PessoaRepository pessoaRepository;

    public PessoaController(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    @GetMapping(path = "/list")
    public List<Pessoa> listar() {
        return pessoaRepository.findAll();
    }

    @PostMapping(path = "/save")
    public Pessoa salvar(@RequestBody Pessoa pessoa) {
        return pessoaRepository.save(pessoa);

    }

    @PutMapping(path = "/update/{id}")
    public String alterar(@PathVariable Long id, @RequestBody Pessoa novaPessoa) {
        Optional<Pessoa> pessoaExistente = pessoaRepository.findById(id);
        if (pessoaExistente.isPresent()) {
            Pessoa p = pessoaExistente.get();
            p.setNome(novaPessoa.getNome());
            p.setEmail(novaPessoa.getEmail());
            pessoaRepository.save(p);
            return "Pessoa alterada com sucesso!";
        } else {
            return "Pessoa não encontrada!";
        }
    }

    @DeleteMapping(path = "/delete/{id}")
    public String deletar(@PathVariable Long id) {
        Optional<Pessoa> pessoaExistente = pessoaRepository.findById(id);
        if (pessoaExistente.isPresent()) {
            pessoaRepository.deleteById(id);
            return "Pessoa deletada com sucesso!";
        } else {
            return "Pessoa não encontrada!";
        }
    }
}
