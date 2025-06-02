package com.veiculos.domain.veiculo;

public record DetalhamentoVeiculoDTO(
    String modelo,
    String marca,
    String placa,
    String cor,
    Long ano,
    Long usuarioId
) {
    public DetalhamentoVeiculoDTO(Veiculo veiculo){
        this(veiculo.getModelo(), veiculo.getMarca(), veiculo.getPlaca(), veiculo.getCor(), veiculo.getAno(), veiculo.getUsuario().getId());
    }
}
