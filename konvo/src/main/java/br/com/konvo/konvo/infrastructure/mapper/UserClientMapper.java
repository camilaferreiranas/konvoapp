package br.com.konvo.konvo.infrastructure.mapper;

import br.com.konvo.konvo.domain.model.UserClient;
import br.com.konvo.konvo.infrastructure.persistence.UserClientEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Mapper(componentModel = "spring")
public interface UserClientMapper {


    @Autowired
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Mapping(target = "password", expression = "java(passwordEncoder.encode(userClient.getPassword()))")
    UserClientEntity toEntity(UserClient userClient);
}
