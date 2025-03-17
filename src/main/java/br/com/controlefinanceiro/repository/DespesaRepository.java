package br.com.controlefinanceiro.repository;

import br.com.controlefinanceiro.enums.CategoriaDespesa;
import br.com.controlefinanceiro.model.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Long> {

    public List<Despesa> findByCategoriaDespesa(CategoriaDespesa categoriaDespesa);
    public List<Despesa> findByDataBetween(LocalDate dataInicial, LocalDate dataFinal);
    @Query("SELECT SUM(d.valor) FROM Despesa d WHERE d.data BETWEEN :dataInicial AND :dataFinal")
    public BigDecimal calcularTotalDespesasPorPeriodo(@Param("dataInicial") LocalDate dataInicial, @Param("dataFinal") LocalDate dataFinal);
}
