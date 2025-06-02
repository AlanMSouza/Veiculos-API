package com.veiculos.domain.veiculo;

public record CadastroVeiculoDTO(
    String modelo,
    String marca,
    String placa,
    String cor,
    Long ano,
    Long usuarioId
) {

}
