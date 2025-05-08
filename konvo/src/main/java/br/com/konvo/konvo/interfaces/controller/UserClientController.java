package br.com.konvo.konvo.interfaces.controller;

import br.com.konvo.konvo.application.usecases.FindUserByEmailUseCase;
import br.com.konvo.konvo.application.usecases.FindUserByIdUseCase;
import br.com.konvo.konvo.application.usecases.ListAllUsersUseCase;
import br.com.konvo.konvo.application.usecases.SaveUserUseCase;
import br.com.konvo.konvo.domain.model.UserClient;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/users")
public class UserClientController {


    private SaveUserUseCase saveUserUseCase;
    private ListAllUsersUseCase listAllUsersUseCase;
    private FindUserByEmailUseCase findUserByEmailUseCase;
    private FindUserByIdUseCase findUserByIdUseCase;


    public UserClientController(SaveUserUseCase saveUserUseCase, ListAllUsersUseCase listAllUsersUseCase,
                                FindUserByEmailUseCase findUserByEmailUseCase, FindUserByIdUseCase findUserByIdUseCase) {
        this.saveUserUseCase = saveUserUseCase;
        this.listAllUsersUseCase = listAllUsersUseCase;
        this.findUserByEmailUseCase = findUserByEmailUseCase;
        this.findUserByIdUseCase = findUserByIdUseCase;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody UserClient user) {
        saveUserUseCase.execute(user);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<UserClient>> findAll() {
        return ResponseEntity.ok(listAllUsersUseCase.execute());
    }

    @GetMapping("email/{email}")
    public ResponseEntity<UserClient> findByEmail(@PathVariable("email") @Valid String email) {
        return ResponseEntity.ok(findUserByEmailUseCase.execute(email));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserClient> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(findUserByIdUseCase.execute(id));
    }
}
