package com.app.listadetarefas.recyclerview.adapter;

import com.app.listadetarefas.model.Tarefa;

public interface OnItemClickListener {

    void onItemClick(Tarefa tarefa, int position);

}
