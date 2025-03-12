package br.com.controlefinanceiro.service;


import br.com.controlefinanceiro.enums.CategoriaReceita;
import br.com.controlefinanceiro.model.Receita;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface ReceitaService {

    public Receita buscarPorId(Long id);

    public List<Receita> listarReceitas();

    public List<Receita> listarReceitasPorCategoria(CategoriaReceita categoriaReceita);

    public List<Receita> listarReceitasPorPeriodo(LocalDate dataInicial, LocalDate dataFinal);

    public Receita cadastrarReceita(Receita novaReceita);

    public Receita atualizarReceita(Long id, Receita receitaAtualizada);

    public void deletarReceita(Long id);

    public BigDecimal calcularTotalReceitasPorPeriodo(LocalDate dataInicial, LocalDate dataFinal);

}
