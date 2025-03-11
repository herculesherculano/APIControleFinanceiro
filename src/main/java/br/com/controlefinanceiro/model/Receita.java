package br.com.controlefinanceiro.model;

import br.com.controlefinanceiro.enums.CategoriaReceita;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Receita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String descricao;

    private double valor;

    private LocalDateTime data;

    private CategoriaReceita categoriaReceita;

    public Receita(Long id, String descricao, double valor, LocalDateTime data, CategoriaReceita categoriaReceita) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
        this.categoriaReceita = categoriaReceita;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public CategoriaReceita getCategoriaReceita() {
        return categoriaReceita;
    }

    public void setCategoriaReceita(CategoriaReceita categoriaReceita) {
        this.categoriaReceita = categoriaReceita;
    }
}
