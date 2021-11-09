package com.app.listadetarefas.dao.action;

import com.app.listadetarefas.dao.TarefaDAO;
import com.app.listadetarefas.database.dao.RoomTarefaDAO;
import com.app.listadetarefas.model.Tarefa;

public interface Action {
    void save(RoomTarefaDAO dao, Tarefa tarefa);

    default boolean isValid(Tarefa tarefa) {
        return !tarefa.getTitulo().isEmpty();
    }
}
