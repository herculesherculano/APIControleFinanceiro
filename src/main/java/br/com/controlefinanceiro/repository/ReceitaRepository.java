package br.com.controlefinanceiro.repository;

import br.com.controlefinanceiro.enums.CategoriaReceita;
import br.com.controlefinanceiro.model.Receita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Long> {

    public List<Receita> findByCategoriaReceita(CategoriaReceita categoriaReceita);
    public List<Receita> findByDataBetween(LocalDate dataInicial, LocalDate dataFinal);
    @Query("SELECT SUM(r.valor) FROM Receita r WHERE r.data BETWEEN :dataInicial AND :dataFinal")
    BigDecimal calcularTotalReceitasPorPeriodo(@Param("dataInicial") LocalDate dataInicial, @Param("dataFinal") LocalDate dataFinal);
}
