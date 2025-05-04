package br.com.konvo.konvo.interfaces.controller;

import br.com.konvo.konvo.application.usecases.FindAllStockUseCase;
import br.com.konvo.konvo.application.usecases.FindStockByCodeUseCase;
import br.com.konvo.konvo.application.usecases.FindStockByIdUseCase;
import br.com.konvo.konvo.application.usecases.SaveStockUseCase;
import br.com.konvo.konvo.domain.model.Stock;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "/stocks")
@RestController
public class StockController {

    private FindAllStockUseCase findAllStockUseCase;
    private FindStockByCodeUseCase findStockByCodeUseCase;
    private FindStockByIdUseCase findStockByIdUseCase;
    private SaveStockUseCase saveStockUseCase;


    public StockController(FindAllStockUseCase findAllStockUseCase,
                           FindStockByCodeUseCase findStockByCodeUseCase,
                           FindStockByIdUseCase findStockByIdUseCase,
                           SaveStockUseCase saveStockUseCase) {
        this.findAllStockUseCase = findAllStockUseCase;
        this.findStockByCodeUseCase = findStockByCodeUseCase;
        this.findStockByIdUseCase = findStockByIdUseCase;
        this.saveStockUseCase = saveStockUseCase;
    }

    @GetMapping
    public ResponseEntity<List<Stock>> findAll() {
        return ResponseEntity.ok(findAllStockUseCase.findAll());
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody  Stock stock) {
        saveStockUseCase.execute(stock);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("code/{code}")
    public ResponseEntity<Stock> findByCode(@PathVariable("code") String code) {
        return ResponseEntity.ok(findStockByCodeUseCase.execute(code));
    }
}
