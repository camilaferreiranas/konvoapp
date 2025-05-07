package br.com.konvo.konvo.application.usecases;

import br.com.konvo.konvo.domain.repository.UserClientRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginUseCase {

    private UserClientRepository clientRepository;
    private PasswordEncoder passwordEncoder;

}
