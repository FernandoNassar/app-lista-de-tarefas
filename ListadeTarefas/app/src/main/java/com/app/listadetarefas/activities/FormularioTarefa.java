package com.app.listadetarefas.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.app.listadetarefas.R;
import com.app.listadetarefas.dao.action.Action;
import com.app.listadetarefas.dao.action.Creation;
import com.app.listadetarefas.dao.action.Edition;
import com.app.listadetarefas.database.TarefaDatabase;
import com.app.listadetarefas.database.dao.RoomTarefaDAO;
import com.app.listadetarefas.model.Tarefa;

import static com.app.listadetarefas.activities.Consts.KEY_TAREFA;

public class FormularioTarefa extends AppCompatActivity {

    private TextView inputTitulo, inputDescricao;
    private Tarefa tarefa = new Tarefa();
    private Action action = new Creation();
    private RoomTarefaDAO roomTarefaDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.title_form);
        setContentView(R.layout.activity_formulario_tarefa);

        inputTitulo = findViewById(R.id.formulario_tarefa_input_titulo);
        inputDescricao = findViewById(R.id.formulario_tarefa_input_descricao);

        TarefaDatabase database = TarefaDatabase.getInstance(this);
        roomTarefaDAO = database.getRoomAlunoDAO();


        if(isEdition()) setUpFormForEdition();

    }

    private void setUpFormForEdition() {
        setTitle(R.string.title_form_edition);
        tarefa = (Tarefa) getIntent().getSerializableExtra(KEY_TAREFA);
        setValuesOnInputFields(tarefa);
        action = new Edition();
    }

    private boolean isEdition() {
        return getIntent().hasExtra(KEY_TAREFA);
    }

    private void setValuesOnInputFields(Tarefa tarefa) {
        inputTitulo.setText(tarefa.getTitulo());
        inputDescricao.setText(tarefa.getDescricao());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_formulario_tarefa_salvar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        setTarefaFields();
        action.save(roomTarefaDAO, tarefa);
        finish();
        return super.onOptionsItemSelected(item);
    }


    private void setTarefaFields() {
        tarefa.setTitulo(inputTitulo.getText().toString());
        tarefa.setDescricao(inputDescricao.getText().toString());
    }


}