package br.com.victor.minhastarefas.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "tarefas")
@NamedQuery(name = "Tarefa.tarefasPorCategoria", query = "select t from Tarefa t inner join t.categoria c where c.nome = ?1")
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "descricao",nullable = false,length = 150)
    private String descricao;
    @Enumerated(EnumType.STRING)
    private TarefasStatus status;
    private LocalDate dataEntrega;
    private boolean visivel;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(nullable = false)
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    
}
