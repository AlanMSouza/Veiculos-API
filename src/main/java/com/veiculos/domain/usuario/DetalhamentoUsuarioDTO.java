package com.veiculos.domain.usuario;

public record DetalhamentoUsuarioDTO(Long id, String nome, String email, String cpf, String role) {
    public DetalhamentoUsuarioDTO(Usuario usuario){
        this(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getCpf(), usuario.getRole());
    }
}
