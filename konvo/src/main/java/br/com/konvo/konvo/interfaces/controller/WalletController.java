package br.com.konvo.konvo.interfaces.controller;

import br.com.konvo.konvo.application.usecases.BuyStockUseCase;
import br.com.konvo.konvo.application.usecases.DeleteWalletUseCase;
import br.com.konvo.konvo.application.usecases.FindWalletsByUserUseCase;
import br.com.konvo.konvo.application.usecases.SaveWalletUseCase;
import br.com.konvo.konvo.domain.model.StockPosition;
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

    public WalletController(SaveWalletUseCase saveWalletUseCase, FindWalletsByUserUseCase findWalletsByUserUseCase, DeleteWalletUseCase deleteWalletUseCase, BuyStockUseCase buyStockUseCase) {
        this.saveWalletUseCase = saveWalletUseCase;
        this.findWalletsByUserUseCase = findWalletsByUserUseCase;
        this.deleteWalletUseCase = deleteWalletUseCase;
        this.buyStockUseCase = buyStockUseCase;
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
    public ResponseEntity<Void> buyStocks(@RequestBody List<StockPosition> stocks, @PathVariable("id") Long id) {
        buyStockUseCase.execute(stocks, id);
        return ResponseEntity.noContent().build();
    }
}
