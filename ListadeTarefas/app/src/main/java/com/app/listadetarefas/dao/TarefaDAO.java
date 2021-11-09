package com.app.listadetarefas.dao;

import com.app.listadetarefas.model.Tarefa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TarefaDAO {

    private static final List<Tarefa> tarefas = new ArrayList<>();
    private static int id = 0;


    public List<Tarefa> all() {
        return tarefas;
    }

    public void add(Tarefa tarefa) {
        tarefa.setId(++id);
        tarefas.add(tarefa);
    }

    public void editar(int position, Tarefa tarefa) {
        tarefas.set(position, tarefa);
    }

    public void editar(Tarefa tarefa) {
       int index = tarefas.indexOf(tarefa);
       tarefas.set(index, tarefa);
    }

    public void remove(int id) {
        tarefas.remove(id);
    }

    public Tarefa findById(int id) {
        return tarefas.get(id);
    }

    public void trocarPosicao(int positionX, int positionY) {
        Collections.swap(tarefas, positionX, positionY);
    }

}
