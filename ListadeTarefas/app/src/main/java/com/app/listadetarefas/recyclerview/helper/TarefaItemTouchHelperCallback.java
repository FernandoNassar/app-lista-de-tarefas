package com.app.listadetarefas.recyclerview.helper;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.app.listadetarefas.components.alertdialog.AlertDialogBuilder;
import com.app.listadetarefas.components.alertdialog.AlertDialogRemove;
import com.app.listadetarefas.recyclerview.adapter.TarefaAdapter;

public class TarefaItemTouchHelperCallback extends ItemTouchHelper.Callback {

    private final TarefaAdapter adapter;

    public TarefaItemTouchHelperCallback(TarefaAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int swipeFlags = ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT;
        int dragFlags = ItemTouchHelper.DOWN | ItemTouchHelper.UP | ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT;
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        int positionX = viewHolder.getAdapterPosition();
        int positionY = target.getAdapterPosition();
        adapter.changePosition(positionX, positionY);
        return true;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        int position = viewHolder.getAdapterPosition();

        AlertDialogBuilder alertDialogBuilder  = new AlertDialogRemove(adapter, position);

        alertDialogBuilder.build(adapter.getContext());

    }
}
