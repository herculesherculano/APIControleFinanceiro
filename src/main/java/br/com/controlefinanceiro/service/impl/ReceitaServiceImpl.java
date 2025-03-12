package br.com.controlefinanceiro.service.impl;

import br.com.controlefinanceiro.enums.CategoriaReceita;
import br.com.controlefinanceiro.exception.ResouceNotFoundException;
import br.com.controlefinanceiro.model.Receita;
import br.com.controlefinanceiro.repository.ReceitaRepository;
import br.com.controlefinanceiro.service.ReceitaService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class ReceitaServiceImpl implements ReceitaService {

    private final ReceitaRepository receitaRepository;

    public ReceitaServiceImpl(ReceitaRepository receitaRepository){
        this.receitaRepository=receitaRepository;
    }
    @Override
    public Receita buscarPorId(Long id) {
        return receitaRepository.findById(id).orElseThrow(() -> new ResouceNotFoundException("Receita não encontrada"));
    }

    @Override
    public List<Receita> listarReceitas() {
        return receitaRepository.findAll();
    }

    @Override
    public List<Receita> listarReceitasPorCategoria(CategoriaReceita categoriaReceita) {
        return receitaRepository.buscarPorCategoria(categoriaReceita);
    }

    @Override
    public List<Receita> listarReceitasPorPeriodo(LocalDate dataInicial, LocalDate dataFinal) {
        return receitaRepository.buscarPorPeriodo(dataInicial, dataFinal);
    }

    @Override
    public Receita cadastrarReceita(Receita novaReceita) {
        return receitaRepository.save(novaReceita);
    }

    @Override
    public Receita atualizarReceita(Long id, Receita receitaAtualizada) {
        Receita receitaExistente = receitaRepository.findById(id).orElseThrow(() -> new ResouceNotFoundException("Receita não encontrada"));
        receitaExistente.setDescricao(receitaAtualizada.getDescricao());
        receitaExistente.setCategoriaReceita(receitaAtualizada.getCategoriaReceita());
        receitaExistente.setData(receitaAtualizada.getData());
        receitaExistente.setValor(receitaAtualizada.getValor());
        return receitaRepository.save(receitaExistente);
    }

    @Override
    public void deletarReceita(Long id) {
        if (!receitaRepository.existsById(id)){
            throw new ResouceNotFoundException("Receita não encontrada");
        }
        receitaRepository.deleteById(id);
    }

    @Override
    public BigDecimal calcularTotalReceitasPorPeriodo(LocalDate dataInicial, LocalDate dataFinal) {
        return receitaRepository.calcularTotalReceitasPorPeriodo(dataInicial, dataFinal);
    }
}
