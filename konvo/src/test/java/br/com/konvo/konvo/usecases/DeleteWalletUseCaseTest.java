package br.com.konvo.konvo.usecases;

import br.com.konvo.konvo.application.usecases.DeleteWalletUseCase;
import br.com.konvo.konvo.domain.repository.WalletRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class DeleteWalletUseCaseTest {

    @Mock
    private WalletRepository repository;

    @InjectMocks
    private DeleteWalletUseCase deleteWalletUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExecute_shouldCallDeleteWalletWithValidId() {
        Long walletId = 1L;

        deleteWalletUseCase.execute(walletId);
        verify(repository, times(1)).delete(walletId);
    }
}
