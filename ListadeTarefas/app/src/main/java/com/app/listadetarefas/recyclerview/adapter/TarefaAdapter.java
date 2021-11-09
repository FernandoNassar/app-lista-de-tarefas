package com.app.listadetarefas.recyclerview.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.listadetarefas.R;
import com.app.listadetarefas.database.dao.RoomTarefaDAO;
import com.app.listadetarefas.model.Tarefa;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class TarefaAdapter extends RecyclerView.Adapter<TarefaAdapter.TarefaViewHolder> {

    private List<Tarefa> tarefas;
    private final Context context;
    private OnItemClickListener onItemClickListener;
    private final RoomTarefaDAO roomTarefaDAO;


    public TarefaAdapter(Context context, RoomTarefaDAO roomTarefaDAO) {
        this.context = context;
        this.roomTarefaDAO = roomTarefaDAO;
    }

    public void updateTarefas() {
        tarefas = roomTarefaDAO.findall();
        tarefas.sort(Comparator.comparing(Tarefa::getPosition));
        notifyDataSetChanged();
    }

    public Context getContext() {
        return context;
    }

    public void cancelRemoval(DialogInterface dialog) {
        dialog.cancel();
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void changePosition(int positionX, int positionY) {
        Collections.swap(tarefas, positionX, positionY);
        AtomicInteger p = new AtomicInteger(0);
        tarefas.forEach(t -> {
            t.setPosition(p.incrementAndGet());
            roomTarefaDAO.update(t);
        });
        notifyItemMoved(positionX, positionY);
    }

    public void remove(int position) {
        Tarefa tarefa = tarefas.get(position);
        roomTarefaDAO.delete(tarefa);
        notifyItemRemoved(position);
        updateTarefas();
    }


    @Override @NonNull
    public TarefaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemListaView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_lista, parent, false);
        return new TarefaViewHolder(itemListaView);
    }

    @Override
    public void onBindViewHolder(@NonNull TarefaAdapter.TarefaViewHolder holder, int position) {
        holder.criarItemTarefa(tarefas.get(position));
    }

    @Override
    public int getItemCount() {
        return tarefas.size();
    }



    public class TarefaViewHolder extends RecyclerView.ViewHolder {

        private final TextView textTitulo, textDescricao;
        private Tarefa tarefa;

        public TarefaViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitulo = itemView.findViewById(R.id.item_lista_textView_titulo);
            textDescricao = itemView.findViewById(R.id.item_lista_textView_descricao);
            itemView.setOnClickListener(v -> onItemClickListener.onItemClick(tarefa, getAdapterPosition()));
        }

        private void criarItemTarefa(Tarefa tarefa) {
            this.tarefa = tarefa;
            textTitulo.setText(tarefa.getTitulo());
            textDescricao.setText(tarefa.getDescricao());
        }
    }

}
