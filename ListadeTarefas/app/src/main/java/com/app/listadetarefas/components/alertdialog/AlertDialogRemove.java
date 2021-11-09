package com.app.listadetarefas.components.alertdialog;

import android.app.AlertDialog;
import android.content.Context;

import com.app.listadetarefas.recyclerview.adapter.TarefaAdapter;

public class AlertDialogRemove implements AlertDialogBuilder{

    private final TarefaAdapter adapter;
    private final int position;

    public AlertDialogRemove(TarefaAdapter adapter, int position) {
        this.adapter = adapter;
        this.position = position;
    }

    @Override
    public AlertDialog.Builder build(Context context) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle("Excluir Tarefa");
        alertDialog.setIcon(android.R.drawable.ic_dialog_alert);

        alertDialog.setNegativeButton("Não", (dialog, which) -> adapter.cancelRemoval(dialog));
        alertDialog.setPositiveButton("Sim", (dialog, which) -> adapter.remove(position));

        alertDialog.setCancelable(false);
        alertDialog.create();
        alertDialog.show();

        return alertDialog;
    }






}
