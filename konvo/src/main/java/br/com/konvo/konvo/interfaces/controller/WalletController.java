package br.com.konvo.konvo.interfaces.controller;

import br.com.konvo.konvo.application.usecases.*;
import br.com.konvo.konvo.domain.model.Stock;
import br.com.konvo.konvo.domain.model.Wallet;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/wallet")
public class WalletController {


    private final SaveWalletUseCase saveWalletUseCase;
    private final FindWalletsByUserUseCase findWalletsByUserUseCase;
    private final DeleteWalletUseCase deleteWalletUseCase;
    private final BuyStockUseCase buyStockUseCase;
    private final FindAllWalletsUseCase findAllWalletsUseCase;

    public WalletController(SaveWalletUseCase saveWalletUseCase, FindWalletsByUserUseCase findWalletsByUserUseCase, DeleteWalletUseCase deleteWalletUseCase, BuyStockUseCase buyStockUseCase, FindAllWalletsUseCase findAllWalletsUseCase) {
        this.saveWalletUseCase = saveWalletUseCase;
        this.findWalletsByUserUseCase = findWalletsByUserUseCase;
        this.deleteWalletUseCase = deleteWalletUseCase;
        this.buyStockUseCase = buyStockUseCase;
        this.findAllWalletsUseCase = findAllWalletsUseCase;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody Wallet wallet) {
        saveWalletUseCase.execute(wallet);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("client/{id}")
    public ResponseEntity<List<Wallet>> findByUser(@PathVariable("id") Long id) {
        return ResponseEntity.ok(findWalletsByUserUseCase.execute(id));
    }

    @DeleteMapping("inactive/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        deleteWalletUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/buystocks/{id}")
    public ResponseEntity<Void> buyStocks(@RequestBody List<Stock> stocks, @PathVariable("id") Long id) {
        buyStockUseCase.execute(stocks, id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Wallet>> findAll() {
        return ResponseEntity.ok(findAllWalletsUseCase.execute());
    }
}
