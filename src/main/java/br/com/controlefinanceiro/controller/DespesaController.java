package br.com.controlefinanceiro.controller;

import br.com.controlefinanceiro.enums.CategoriaDespesa;
import br.com.controlefinanceiro.model.Despesa;
import br.com.controlefinanceiro.service.DespesaService;
import org.springframework.cglib.core.Local;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/despesas")
public class DespesaController {

    private final DespesaService despesaService;

    public DespesaController(DespesaService despesaService){
        this.despesaService=despesaService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Despesa>buscarDespesaPorId(@PathVariable Long id){
        var despesaPorId=despesaService.buscarPorId(id);
        return ResponseEntity.ok(despesaPorId);
    }

    @GetMapping
    public ResponseEntity<List<Despesa>>listarDespesas(){
        var listaDespesas=despesaService.listarDespesas();
        return ResponseEntity.ok(listaDespesas);
    }

    @GetMapping("/periodo")
    public ResponseEntity<List<Despesa>> listarDespesasPorPeriodo(@RequestParam("dataInicial")String dataInicial, @RequestParam("dataFinal")String dataFinal){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate dataInicio = LocalDate.parse(dataInicial, formatter);
        LocalDate dataFim = LocalDate.parse(dataFinal, formatter);

        var listaDespessPorPeriodo=despesaService.listarDespesasPorPeriodo(dataInicio, dataFim);

        return ResponseEntity.ok(listaDespessPorPeriodo);
    }

    @GetMapping("/categoria")
    public ResponseEntity<List<Despesa>> listarDespesasPorCategoria(@RequestParam CategoriaDespesa categoriaDespesa){
        var listaDespesasPorCategoria=despesaService.listarDespesasPorCategoria(categoriaDespesa);
        return ResponseEntity.ok(listaDespesasPorCategoria);
    }

    @GetMapping("/totalDespesasPorPeriodo")
    public ResponseEntity<BigDecimal>calcularTotalDespesasPorPeriodo(@RequestParam("dataInicial")String dataInicial, @RequestParam("dataFinal")String dataFinal){
        DateTimeFormatter formatter= DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate dataInicio = LocalDate.parse(dataInicial, formatter);
        LocalDate dataFim = LocalDate.parse(dataFinal, formatter);

        var totalDespesasPorPeriodo = despesaService.calcularTotalDespesasPorPeriodo(dataInicio, dataFim);

        return ResponseEntity.ok(totalDespesasPorPeriodo);
    }

    @PostMapping
    public ResponseEntity<Despesa> cadastrarNovaDespesa(@RequestBody Despesa novaDespesa){
        var despesaSalva=despesaService.cadastrarDespesa(novaDespesa);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(despesaSalva.getId())
                .toUri();
        return ResponseEntity.created(location).body(despesaSalva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Despesa> atualizarDespesa(@PathVariable Long id, @RequestBody Despesa despesa){
        var despesaAtualizada = despesaService.atualizarDespesa(id, despesa);
        return ResponseEntity.ok(despesaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Despesa> deletarDespesa(@PathVariable Long id){
        despesaService.deletarDespesa(id);
        return ResponseEntity.noContent().build();
    }


}
