package com.exemplo.tarefaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.exemplo.tarefaapi.model.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
}
