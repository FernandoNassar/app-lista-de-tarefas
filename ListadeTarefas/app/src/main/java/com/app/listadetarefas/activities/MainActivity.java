package com.app.listadetarefas.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.app.listadetarefas.R;
import com.app.listadetarefas.database.TarefaDatabase;
import com.app.listadetarefas.database.dao.RoomTarefaDAO;
import com.app.listadetarefas.recyclerview.adapter.TarefaAdapter;
import com.app.listadetarefas.model.Tarefa;
import com.app.listadetarefas.recyclerview.helper.TarefaItemTouchHelperCallback;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import static com.app.listadetarefas.activities.Consts.*;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton fab;
    private TarefaAdapter adapter;
    private RecyclerView recyclerView;
    private RoomTarefaDAO roomTarefaDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TarefaDatabase database = TarefaDatabase.getInstance(this);
        roomTarefaDAO = database.getRoomAlunoDAO();


        recyclerView = findViewById(R.id.main_recyclerView);
        fab = findViewById(R.id.main_floatingActionButton);

        adapter = new TarefaAdapter(this, roomTarefaDAO);

        setUpRecyclerView();

        setUpFloatingActionButton();

        setUpListItemClickListener();

        adapter.notifyDataSetChanged();
    }

    private void setUpListItemClickListener() {
        adapter.setOnItemClickListener((tarefa, position) -> onItemClickListener(tarefa));
    }

    private void setUpFloatingActionButton() {
        fab.setOnClickListener(v -> initializeFormActivity());
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.updateTarefas();
    }

    private void setUpRecyclerView() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerView.setAdapter(adapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new TarefaItemTouchHelperCallback(adapter));
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    private void initializeFormActivity() {
        Intent intent = new Intent(MainActivity.this, FormularioTarefa.class);
        startActivity(intent);
    }

    private void onItemClickListener(Tarefa tarefa) {
        Intent intent = new Intent(MainActivity.this, FormularioTarefa.class);
        intent.putExtra(KEY_TAREFA, tarefa);
        startActivity(intent);
    }
}