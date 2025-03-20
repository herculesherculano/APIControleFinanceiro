package br.com.controlefinanceiro.model;

import br.com.controlefinanceiro.enums.CategoriaDespesa;
import br.com.controlefinanceiro.enums.CategoriaReceita;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Despesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String descricao;

    private BigDecimal valor;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate data;

    @Column
    @Enumerated(EnumType.STRING)
    private CategoriaDespesa categoriaDespesa;

    public Despesa() {
    }

    public Despesa(String descricao, BigDecimal valor, LocalDate data, CategoriaDespesa categoriaDespesa) {
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
        this.categoriaDespesa = categoriaDespesa;
    }

    public Despesa(Long id, String descricao, BigDecimal valor, LocalDate data, CategoriaDespesa categoriaDespesa) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
        this.categoriaDespesa = categoriaDespesa;
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

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public CategoriaDespesa getCategoriaDespesa() {
        return categoriaDespesa;
    }

    public void setCategoriaDespesa(CategoriaDespesa categoriaDespesa) {
        this.categoriaDespesa = categoriaDespesa;
    }
}
