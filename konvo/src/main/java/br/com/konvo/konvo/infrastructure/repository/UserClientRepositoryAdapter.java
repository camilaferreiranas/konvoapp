package br.com.konvo.konvo.infrastructure.repository;

import br.com.konvo.konvo.domain.model.UserClient;
import br.com.konvo.konvo.domain.repository.UserClientRepository;
import br.com.konvo.konvo.infrastructure.persistence.UserClientEntity;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserClientRepositoryAdapter implements UserClientRepository {

    private UserClientJpaRepository repository;
    private final BCryptPasswordEncoder passwordEncoder;


    public UserClientRepositoryAdapter(UserClientJpaRepository repository, BCryptPasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;

    }

    @Override
    public UserClient findById(Long id) {
        return repository.findById(id).map(user ->
                        new UserClient(user.getId(), user.getName(),
                                user.getUsername(), user.getPassword(), user.getEmail()))
                .orElseThrow();
    }

    @Override
    public UserClient findByEmail(String email) {
        return repository.findByEmail(email).map(user ->
                        new UserClient(user.getId(), user.getName(),
                                user.getUsername(), user.getPassword(), user.getEmail()))
                .orElseThrow();
    }

    @Override
    @Transactional
    public void save(UserClient userClient) {
        UserClientEntity user = new UserClientEntity();
        user.setEmail(userClient.getEmail());
        user.setName(userClient.getName());
        user.setUsername(userClient.getUsername());
        user.setPassword(passwordEncoder.encode(userClient.getPassword()));
        repository.save(user);
    }

    @Override
    public List<UserClient> findAll() {
        return repository.findAll().
                stream().map(user -> new UserClient(user.getId(), user.getName(), user.getUsername(), user.getPassword(), user.getEmail()))
                .collect(Collectors.toList());
    }


}
