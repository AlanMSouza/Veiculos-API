package com.veiculos.domain.veiculo;

import com.veiculos.domain.usuario.Usuario;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Veiculo")
@Table(name = "veiculos")
@EqualsAndHashCode(of = "id")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Veiculo {

    public Veiculo(CadastroVeiculoDTO dto, Usuario usuario) {
        this.modelo = dto.modelo();
        this.marca = dto.marca();
        this.placa = dto.placa();
        this.cor = dto.cor();
        this.ano = dto.ano();
        this.usuario = usuario;
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String modelo;
    private String marca;
    private String placa;
    private String cor;
    private Long ano;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
