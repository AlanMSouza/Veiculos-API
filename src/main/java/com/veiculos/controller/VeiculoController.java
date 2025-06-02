package com.veiculos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.UriComponentsBuilder;

import com.veiculos.domain.usuario.Usuario;
import com.veiculos.domain.usuario.UsuarioRepository;
import com.veiculos.domain.veiculo.CadastroVeiculoDTO;
import com.veiculos.domain.veiculo.DetalhamentoVeiculoDTO;
import com.veiculos.domain.veiculo.ListagemVeiculoDTO;
import com.veiculos.domain.veiculo.Veiculo;
import com.veiculos.domain.veiculo.VeiculoRepository;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private VeiculoRepository veiculoRepository;

    @PostMapping()
    public ResponseEntity cadastrar(@RequestBody @Valid CadastroVeiculoDTO dto, UriComponentsBuilder uriBuilder) {
        Usuario usuario = usuarioRepository.findById(dto.usuarioId()).orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
        
        var veiculo = new Veiculo(dto, usuario);
        veiculoRepository.save(veiculo);

        var uri = uriBuilder.path("/veiculos/{id}").buildAndExpand(veiculo.getId()).toUri();

        return ResponseEntity.created(uri).body(new DetalhamentoVeiculoDTO(veiculo));
    }

    @GetMapping()
    public ResponseEntity<Page<ListagemVeiculoDTO>> listar(@PageableDefault(size = 10, sort = {"modelo"}) Pageable paginacao) {
        var page = veiculoRepository.findAll(paginacao).map(ListagemVeiculoDTO::new);
        
        return ResponseEntity.ok(page);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluir(@PathVariable Long id){
        if (!veiculoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        veiculoRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var veiculo = veiculoRepository.getReferenceById(id);

        return ResponseEntity.ok(new DetalhamentoVeiculoDTO(veiculo));
    }
    

    
}
