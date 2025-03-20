package br.com.controlefinanceiro.controller;

import br.com.controlefinanceiro.enums.CategoriaReceita;
import br.com.controlefinanceiro.model.Receita;
import br.com.controlefinanceiro.service.ReceitaService;
import jakarta.servlet.Servlet;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/receitas")
public class ReceitaController {

    private final ReceitaService receitaService;

    public ReceitaController(ReceitaService receitaService){
        this.receitaService=receitaService;
    }

    @GetMapping
    public ResponseEntity<List<Receita>> listarReceitas(){
        var listaReceitas=receitaService.listarReceitas();
        return ResponseEntity.ok(listaReceitas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Receita> buscarReceitaPorId(@PathVariable Long id){
        var receitaPorId=receitaService.buscarPorId(id);
        return ResponseEntity.ok(receitaPorId);
    }

    @GetMapping("/categoria")
    public ResponseEntity<List<Receita>> listarReceitasPorCategoria(@RequestParam CategoriaReceita categoriaReceita){
        var listaReceitasPorCategoria=receitaService.listarReceitasPorCategoria(categoriaReceita);
        return ResponseEntity.ok(listaReceitasPorCategoria);
    }

    @GetMapping("/periodo")
    public ResponseEntity<List<Receita>> listarReceitasPorPeriodo(@RequestParam("dataInicial") String dataInicial, @RequestParam("dataFinal") String dataFinal){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate dataInicio=LocalDate.parse(dataInicial,formatter);
        LocalDate dataFim=LocalDate.parse(dataFinal,formatter);

        var listaReceitasPorPeriodo=receitaService.listarReceitasPorPeriodo(dataInicio, dataFim);
        return ResponseEntity.ok(listaReceitasPorPeriodo);
    }

    @GetMapping("/totalReceitasPorPeriodo")
    public ResponseEntity<BigDecimal> calcularTotalReceitasPorPeriodo(@RequestParam("dataInicial")String dataInicial, @RequestParam("dataFinal") String dataFinal){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate dataInicio=LocalDate.parse(dataInicial, formatter);
        LocalDate dataFim=LocalDate.parse(dataFinal, formatter);

        var totalReceitasPorPeriodo=receitaService.calcularTotalReceitasPorPeriodo(dataInicio,dataFim);
        return ResponseEntity.ok(totalReceitasPorPeriodo);
    }

    @PostMapping
    public ResponseEntity<Receita> cadastrarNovaReceita(@RequestBody Receita novaReceita){
        var receitaSalva=receitaService.cadastrarReceita(novaReceita);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(receitaSalva.getId())
                .toUri();
        return ResponseEntity.created(location).body(receitaSalva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Receita> atualizarReceita(@PathVariable Long id, @RequestBody Receita receita){
        return ResponseEntity.ok(receitaService.atualizarReceita(id, receita));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Receita> deletarReceitaPorId(@PathVariable Long id){
        receitaService.deletarReceita(id);
        return ResponseEntity.noContent().build();
    }
}
