package br.com.konvo.konvo.usecases;

import br.com.konvo.konvo.application.usecases.FindAllWalletsUseCase;
import br.com.konvo.konvo.domain.model.Wallet;
import br.com.konvo.konvo.domain.repository.WalletRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

public class FindAllWalletsUseCaseTest {

    @Mock
    private WalletRepository repository;

    @InjectMocks
    private FindAllWalletsUseCase findAllWalletsUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testExecute_shouldCallFindAllWallets() {
        Wallet wallet = new Wallet(1L, "Retirement", "A wallet for retirement");
        List<Wallet> wallets = Arrays.asList(wallet);
        Mockito.when(repository.findAll()).thenReturn(wallets);
        List<Wallet> result = findAllWalletsUseCase.execute();
        Assertions.assertEquals(wallets, result);
    }
}
