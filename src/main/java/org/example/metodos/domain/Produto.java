package org.example.metodos.domain;

import java.util.Objects;

public class Produto {

    private Integer id;
    private String nome;
    private String descricao;

    public Produto(){
    }

    public Produto(Integer id, String nome, String descricao){
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    public Produto(String nome, String descricao){
        super();
        this.nome = nome;
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNome(), getDescricao());
    }

    @Override
    public String toString() {
        return "ID:" + this.id + " NOME: " + this.nome + " DESCRICAO: " + this.descricao;
    }
}
