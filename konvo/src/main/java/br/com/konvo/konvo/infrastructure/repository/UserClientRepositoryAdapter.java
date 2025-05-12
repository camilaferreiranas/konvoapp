package br.com.konvo.konvo.infrastructure.repository;

import br.com.konvo.konvo.domain.exceptions.UserNotFoundException;
import br.com.konvo.konvo.domain.model.UserClient;
import br.com.konvo.konvo.domain.repository.UserClientRepository;
import br.com.konvo.konvo.infrastructure.mapper.UserClientMapper;
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

    private final UserClientJpaRepository repository;
    private final UserClientMapper userClientMapper;


    public UserClientRepositoryAdapter(UserClientJpaRepository repository, UserClientMapper userClientMapper) {
        this.repository = repository;
        this.userClientMapper = userClientMapper;
    }

    @Override
    public UserClient findById(Long id) {
        return repository.findById(id).map(user ->
                        new UserClient(user.getId(), user.getName(),
                                user.getUsername(), user.getPassword(), user.getEmail()))
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    @Override
    public UserClient findByEmail(String email) {
        return repository.findByEmail(email).map(user ->
                        new UserClient(user.getId(), user.getName(),
                                user.getUsername(), user.getPassword(), user.getEmail()))
                .orElseThrow(() -> new UserNotFoundException("User not found with given e-mail"));
    }

    @Override
    @Transactional
    public void save(UserClient userClient) {
        UserClientEntity user = userClientMapper.toEntity(userClient);
        repository.save(user);
    }

    @Override
    public List<UserClient> findAll() {
        return repository.findAll().
                stream().map(user -> new UserClient(user.getId(), user.getName(), user.getUsername(), user.getPassword(), user.getEmail()))
                .collect(Collectors.toList());
    }


}
