package com.veiculos.domain.veiculo;

public record ListagemVeiculoDTO(
    String modelo,
    String marca,
    String placa,
    Long ano,
    String cor,
    Long usuarioId
    ) {

    public ListagemVeiculoDTO(Veiculo veiculo){
        this(veiculo.getModelo(), veiculo.getMarca(), veiculo.getPlaca(), veiculo.getAno(), veiculo.getCor(), veiculo.getUsuario().getId());
    }
}
