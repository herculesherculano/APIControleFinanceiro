package br.com.controlefinanceiro.service.impl;

import br.com.controlefinanceiro.enums.CategoriaDespesa;
import br.com.controlefinanceiro.exception.ResouceNotFoundException;
import br.com.controlefinanceiro.model.Despesa;
import br.com.controlefinanceiro.repository.DespesaRepository;
import br.com.controlefinanceiro.service.DespesaService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class DespesaServiceImpl implements DespesaService {

    private final DespesaRepository despesaRepository;

    public DespesaServiceImpl(DespesaRepository despesaRepository){
        this.despesaRepository=despesaRepository;
    }

    @Override
    public Despesa buscarPorId(Long id) {
        return despesaRepository.findById(id).orElseThrow(()-> new ResouceNotFoundException("Despesa não encontrada"));
    }

    @Override
    public List<Despesa> listarDespesas() {
        return despesaRepository.findAll();
    }

    @Override
    public List<Despesa> listarDespesasPorCategoria(CategoriaDespesa categoriaDespesa) {
        return despesaRepository.buscarDespesaPorCategoria(categoriaDespesa);
    }

    @Override
    public List<Despesa> listarDespesasPorPeriodo(LocalDate dataInicial, LocalDate dataFinal) {
        return despesaRepository.buscarDespesaPorPeriodo(dataInicial, dataFinal);
    }

    @Override
    public Despesa cadastrarDespesa(Despesa novaDespesa) {
        return despesaRepository.save(novaDespesa);
    }

    @Override
    public Despesa atualizarDespesa(Long id, Despesa despesaAtualizada) {
        Despesa despesaExistente = despesaRepository.findById(id).orElseThrow(() -> new ResouceNotFoundException("Despesa não encontrada"));
        despesaExistente.setCategoriaDespesa(despesaAtualizada.getCategoriaDespesa());
        despesaExistente.setData(despesaAtualizada.getData());
        despesaExistente.setDescricao(despesaAtualizada.getDescricao());
        despesaExistente.setValor(despesaAtualizada.getValor());
        return despesaRepository.save(despesaExistente);
    }

    @Override
    public void deletarDespesa(Long id) {
        if (!despesaRepository.existsById(id)) {
            throw new ResouceNotFoundException("Despesa não encontrada");
        }
        despesaRepository.deleteById(id);
    }

    @Override
    public BigDecimal calcularTotalDespesasPorPeriodo(LocalDate dataInicial, LocalDate dataFinal) {
        return despesaRepository.calcularTotalDespesasPorPeriodo(dataInicial, dataFinal);
    }
}
