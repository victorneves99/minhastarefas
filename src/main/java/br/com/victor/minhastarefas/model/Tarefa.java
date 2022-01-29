package br.com.victor.minhastarefas.model;

import java.time.LocalDate;


public class Tarefa {

    private String descricao;
    private TarefasStatus status;
    private LocalDate dataEntrega;
    private boolean visivel;
    private Usuario usuario;

    private TarefaCategoria categoria;

    public String getDescricao() {
        return descricao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public TarefaCategoria getCategoria() {
        return categoria;
    }

    public void setCategoria(TarefaCategoria categoria) {
        this.categoria = categoria;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public TarefasStatus getStatus() {
        return status;
    }

    public void setStatus(TarefasStatus status) {
        this.status = status;
    }

    public LocalDate getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(LocalDate dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public boolean isVisivel() {
        return visivel;
    }

    public void setVisivel(boolean visivel) {
        this.visivel = visivel;
    }

}
