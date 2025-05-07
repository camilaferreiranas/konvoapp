package br.com.konvo.konvo.interfaces.dto;

import java.time.Instant;

public record LoginResponseDTO(String acessToken, Instant expiresIn) {
}
