package com.app.listadetarefas.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Objects;

@Entity
public class Tarefa implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String titulo;
    private String descricao;
    private int position;

    public Tarefa(){}

    @Ignore
    public Tarefa(String titulo, String descricao) {
        this.titulo = titulo; this.descricao = descricao;
    }

    @Ignore
    public Tarefa(int id, String titulo, String descricao) {
        this.id = id; this.titulo = titulo; this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tarefa tarefa = (Tarefa) o;
        return id == tarefa.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
