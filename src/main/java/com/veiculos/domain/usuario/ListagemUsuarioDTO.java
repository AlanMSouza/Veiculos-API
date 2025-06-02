package com.veiculos.domain.usuario;

public record ListagemUsuarioDTO(
    String nome,
    String email,
    String senha,
    String cpf,
    String role
) {

    public ListagemUsuarioDTO(Usuario usuario){
        this(usuario.getNome(), usuario.getEmail(), usuario.getSenha(), usuario.getCpf(), usuario.getRole());
    }

}
