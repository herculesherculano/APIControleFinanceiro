package br.com.controlefinanceiro.repository;

import br.com.controlefinanceiro.enums.CategoriaReceita;
import br.com.controlefinanceiro.model.Receita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Long> {

    public List<Receita> buscarPorCategoria(CategoriaReceita categoriaReceita);
    public List<Receita> buscarPorPeriodo(LocalDate dataInicial, LocalDate dataFinal);
    public BigDecimal calcularTotalReceitasPorPeriodo(LocalDate dataInicial, LocalDate dataFinal);
}
