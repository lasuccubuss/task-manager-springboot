package com.exemplo.tarefaapi.controller;

import com.exemplo.tarefaapi.model.Tarefa;
import com.exemplo.tarefaapi.repository.TarefaRepository;
import com.exemplo.tarefaapi.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    @Autowired
    private TarefaService service;

    @Autowired
    private TarefaRepository repository;

    @PostMapping
    public Tarefa criarTarefa(@RequestBody Tarefa tarefa){
        return service.salvar(tarefa);
    }

    @GetMapping
    public List<Tarefa> listar() {
        return service.listarTodas();
    }

    @PutMapping("/{id}/concluir")
    public ResponseEntity<Tarefa> alternarConclusao(@PathVariable Long id) {
        return repository.findById(id)
                .map(tarefa -> {
                    tarefa.setConcluida(!tarefa.isConcluida());
                    return ResponseEntity.ok(repository.save(tarefa));
                })
                .orElse(ResponseEntity.notFound().build());
    }


    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizarTarefa(@PathVariable Long id, @RequestBody Tarefa novaTarefa) {
        return service.atualizar(id, novaTarefa)
                .map(tarefaAtualizada -> ResponseEntity.ok(tarefaAtualizada))
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        repository.deleteById(id);
    }
}