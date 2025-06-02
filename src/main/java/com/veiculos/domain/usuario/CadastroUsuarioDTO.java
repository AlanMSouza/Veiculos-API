package com.veiculos.domain.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CadastroUsuarioDTO(

    @NotBlank
    String nome,

    @NotBlank
    @Email
    String email,

    @NotBlank
    String senha,

    @NotBlank
    @Pattern(regexp = "\\d{3}\\.?\\d{3}\\.?\\d{3}\\-?\\d{2}")
    String cpf,

    @NotBlank
    String role
    ) {

}
