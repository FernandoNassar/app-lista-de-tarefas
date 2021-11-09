package com.app.listadetarefas.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.app.listadetarefas.model.Tarefa;

import java.util.List;

@Dao
public interface RoomTarefaDAO {

    @Insert
    void save(Tarefa tarefa);

    @Query("SELECT * FROM Tarefa")
    List<Tarefa> findall();

    @Delete
    void delete(Tarefa tarefa);

    @Update
    void update(Tarefa tarefa);


}






















