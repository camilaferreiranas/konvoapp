package br.com.konvo.konvo.interfaces.controller;

import br.com.konvo.konvo.application.usecases.LoginUseCase;
import br.com.konvo.konvo.interfaces.dto.LoginRequestDTO;
import br.com.konvo.konvo.interfaces.dto.LoginResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(path = "/login")
@RestController
public class TokenController {


    private final LoginUseCase loginUseCase;

    public TokenController(LoginUseCase loginUseCase) {
        this.loginUseCase = loginUseCase;
    }

    @PostMapping
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO login) {
        return ResponseEntity.ok(loginUseCase.execute(login));
    }
}
