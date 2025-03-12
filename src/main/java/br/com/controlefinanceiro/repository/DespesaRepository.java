package br.com.controlefinanceiro.repository;

import br.com.controlefinanceiro.enums.CategoriaDespesa;
import br.com.controlefinanceiro.model.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Long> {

    public List<Despesa> buscarDespesaPorCategoria(CategoriaDespesa categoriaDespesa);
    public List<Despesa> buscarDespesaPorPeriodo(LocalDate dataInicial, LocalDate dataFinal);
    public BigDecimal calcularTotalDespesasPorPeriodo(LocalDate dataInicial, LocalDate dataFinal);
}
