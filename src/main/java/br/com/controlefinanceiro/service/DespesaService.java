package br.com.controlefinanceiro.service;

import br.com.controlefinanceiro.enums.CategoriaDespesa;
import br.com.controlefinanceiro.model.Despesa;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface DespesaService {

    public Despesa buscarPorId(Long id);

    public List<Despesa> listarDespesas();

    public List<Despesa> listarDespesasPorCategoria(CategoriaDespesa categoriaDespesa);

    public List<Despesa> listarDespesasPorPeriodo(LocalDate dataInicial, LocalDate dataFinal);

    public Despesa cadastrarDespesa(Despesa novaDespesa);

    public Despesa atualizarDespesa(Long id, Despesa despesaAtualizada);

    public void deletarDespesa(Long id);

    public BigDecimal calcularTotalDespesasPorPeriodo(LocalDate dataInicial, LocalDate dataFinal);
}
