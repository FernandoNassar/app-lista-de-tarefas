package com.app.listadetarefas.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.app.listadetarefas.database.dao.RoomTarefaDAO;
import com.app.listadetarefas.model.Tarefa;
import static com.app.listadetarefas.activities.Consts.*;

@Database(entities = {Tarefa.class}, version = 1, exportSchema = false)
public abstract class TarefaDatabase extends RoomDatabase {

    public abstract RoomTarefaDAO getRoomAlunoDAO();

    public static TarefaDatabase getInstance(Context context) {
        return Room
                .databaseBuilder(context, TarefaDatabase.class, NAME_DB)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }
}
