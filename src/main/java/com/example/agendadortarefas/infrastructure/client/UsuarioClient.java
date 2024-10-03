package com.example.agendadortarefas.infrastructure.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.usuario.business.dto.in.EnderecoDTORequest;
import com.example.usuario.business.dto.in.LoginDTORequest;
import com.example.usuario.business.dto.in.TelefoneDTORequest;
import com.example.usuario.business.dto.in.UsuarioDTORequest;
import com.example.usuario.business.dto.out.EnderecoDTOResponse;
import com.example.usuario.business.dto.out.TelefoneDTOResponse;
import com.example.usuario.business.dto.out.UsuarioDTOResponse;


@FeignClient(name = "usuario", url = "${usuario.url}")
public interface UsuarioClient {

	@GetMapping
    UsuarioDTOResponse buscaUsuarioPorEmail(@RequestParam("email") String email, @RequestHeader("Authorization") String token);

    @PostMapping
	UsuarioDTOResponse insertUser(@RequestBody UsuarioDTORequest uDTO);
	
	@PostMapping("/login")
    String login(@RequestBody LoginDTORequest loginDTO);

    @DeleteMapping("/{email}")
    void deletaUsuarioPorEmail(@PathVariable String email, @RequestHeader("Authorization") String token);
    
    @PutMapping
    UsuarioDTOResponse atualizaDadosUsuario(@RequestBody UsuarioDTORequest dto, @RequestHeader("Authorization") String token);

    @PutMapping("/endereco")
    EnderecoDTOResponse atualizaEndereco(@RequestBody EnderecoDTORequest dto, @RequestParam("id") Long id, @RequestHeader("Authorization") String token);
    
    @PutMapping("/telefone")
    TelefoneDTOResponse atualizaTelefone(@RequestBody TelefoneDTORequest dto, @RequestParam("id") Long id, @RequestHeader("Authorization") String token);
    
    @PostMapping("/endereco")
    EnderecoDTOResponse cadastraEndereco(@RequestBody EnderecoDTORequest dto, @RequestHeader("Authorization") String token);
    
    @PostMapping("/telefone")
    TelefoneDTOResponse cadastraTelefone(@RequestBody TelefoneDTORequest dto, @RequestHeader("Authorization") String token);

}