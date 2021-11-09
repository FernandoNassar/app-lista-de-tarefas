package com.app.listadetarefas.dao.action;

import com.app.listadetarefas.dao.TarefaDAO;
import com.app.listadetarefas.dao.action.Action;
import com.app.listadetarefas.database.dao.RoomTarefaDAO;
import com.app.listadetarefas.model.Tarefa;

public class Edition implements Action {

    @Override
    public void save(RoomTarefaDAO dao, Tarefa tarefa) {
        if(isValid(tarefa)) dao.update(tarefa);
    }
}
