package Cadastro_pessoas.controller;

import Cadastro_pessoas.domain.pessoa.Pessoa;
import Cadastro_pessoas.domain.pessoa.dto.PessoaDto;
import Cadastro_pessoas.service.PessoaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/person")
public class PessoaController {

    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping(path = "/list")
    public ResponseEntity<List<Pessoa>> listar() {
        return ResponseEntity.ok(pessoaService.listar());
    }

    @GetMapping(path = "/find/{id}")
    public ResponseEntity<String> buscar(@PathVariable Long id) {
        return pessoaService.buscarPorId(id)
                .map(pessoa -> ResponseEntity.ok("Pessoa encontrada! " + pessoa.getNome()))
                .orElseGet(() -> ResponseEntity.status(404).body("Pessoa não encontrada!"));
    }

    @PostMapping("/add")
    public ResponseEntity<String> adicionar(@RequestBody @Valid PessoaDto pessoa) {
        pessoaService.adicionar(pessoa);
        return ResponseEntity.ok("Pessoa adicionada com sucesso!");

    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<String> alterar(@PathVariable Long id, @RequestBody Pessoa novaPessoa) {
        return pessoaService.alterar(id, novaPessoa)
                .map(pessoa -> ResponseEntity.ok("Pessoa alterada com sucesso!"))
                .orElseGet(() -> ResponseEntity.status(404).body("Pessoa não encontrada!"));
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id) {
        boolean deletado = pessoaService.deletar(id);
        if (deletado) {
            return ResponseEntity.ok("Pessoa deletada com sucesso!");
        } else {
            return ResponseEntity.status(404).body("Pessoa não encontrada!");
        }
    }
}
