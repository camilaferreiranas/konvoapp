package br.com.konvo.konvo.application.usecases;

import br.com.konvo.konvo.domain.model.UserClient;
import br.com.konvo.konvo.domain.repository.UserClientRepository;
import br.com.konvo.konvo.interfaces.dto.LoginRequestDTO;
import br.com.konvo.konvo.interfaces.dto.LoginResponseDTO;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class LoginUseCase {

    private final UserClientRepository clientRepository;
    private final JwtEncoder jwtEncoder;

    public LoginUseCase(UserClientRepository clientRepository, JwtEncoder jwtEncoder) {
        this.clientRepository = clientRepository;
        this.jwtEncoder = jwtEncoder;
    }


    public LoginResponseDTO execute(LoginRequestDTO login) {
        UserClient user = clientRepository.findByEmail(login.email());
        if(user == null) {
            throw new BadCredentialsException("User not valid");
        }

        Instant now = Instant.now();
        Instant expiresIn = now.plusSeconds(3000L);

        var claims = JwtClaimsSet.builder()
                .issuer("konvoapp")
                .subject(user.getUsername())
                .subject(user.getEmail())
                .issuedAt(now)
                .expiresAt(expiresIn)
                .build();
        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
        return new LoginResponseDTO(jwtValue, expiresIn);
    }

}
