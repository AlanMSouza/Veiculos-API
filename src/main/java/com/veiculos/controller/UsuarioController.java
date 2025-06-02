package com.veiculos.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import com.veiculos.domain.usuario.AutenticacaoDTO;
import com.veiculos.domain.usuario.CadastroUsuarioDTO;
import com.veiculos.domain.usuario.DetalhamentoUsuarioDTO;
import com.veiculos.domain.usuario.ListagemUsuarioDTO;
import com.veiculos.domain.usuario.Usuario;
import com.veiculos.domain.usuario.UsuarioRepository;
import com.veiculos.infra.security.TokenJWTDTO;
import com.veiculos.infra.security.TokenService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@EnableMethodSecurity
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping()
    @Transactional
    public ResponseEntity registrar(@RequestBody @Valid CadastroUsuarioDTO dto, UriComponentsBuilder uriBuilder) {
        var usuario = new Usuario(dto);
        usuario.setSenha(passwordEncoder.encode(dto.senha()));
        repository.save(usuario);
        
        var uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();

        return ResponseEntity.created(uri).body(new DetalhamentoUsuarioDTO(usuario));
    }

    @PostMapping("/login")
    @Transactional
    public ResponseEntity efetuarLogin(@RequestBody @Valid AutenticacaoDTO dto){
        var authenticationToken = new UsernamePasswordAuthenticationToken(dto.email(), dto.senha());
        var authentication = manager.authenticate(authenticationToken);
        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());
        
        return ResponseEntity.ok(new TokenJWTDTO(tokenJWT));
    }

    @GetMapping()
    public ResponseEntity<Page<ListagemUsuarioDTO>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = repository.findAll(paginacao).map(ListagemUsuarioDTO::new);    
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var usuario = repository.getReferenceById(id);

        return ResponseEntity.ok(new DetalhamentoUsuarioDTO(usuario));
    }
    

    @DeleteMapping("/{id}")
    public ResponseEntity excluir(@PathVariable Long id){
        if(!repository.existsById(id)){
            return ResponseEntity.notFound().build();
        }

        repository.deleteById(id);
        
        return ResponseEntity.noContent().build();
    }

}

