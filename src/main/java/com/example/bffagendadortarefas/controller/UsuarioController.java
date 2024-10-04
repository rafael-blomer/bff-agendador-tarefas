package com.example.bffagendadortarefas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.agendadortarefas.infrastructure.security.SecurityConfig;
import com.example.bffagendadortarefas.business.UsuarioService;
import com.example.usuario.business.dto.in.EnderecoDTORequest;
import com.example.usuario.business.dto.in.LoginDTORequest;
import com.example.usuario.business.dto.in.TelefoneDTORequest;
import com.example.usuario.business.dto.in.UsuarioDTORequest;
import com.example.usuario.business.dto.out.EnderecoDTOResponse;
import com.example.usuario.business.dto.out.TelefoneDTOResponse;
import com.example.usuario.business.dto.out.UsuarioDTOResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/usuario")
@Tag(name = "Usuário", description = "Cadastro e login de Usuários")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class UsuarioController {

	@Autowired
	private UsuarioService service;

	@PostMapping
	@Operation(summary = "Salvar Usuários", description = "Cria um novo usuário")
	@ApiResponse(responseCode = "200", description = "Usuário salvo com sucesso")
	@ApiResponse(responseCode = "400", description = "Usuário já cadastrado")
	@ApiResponse(responseCode = "500", description = "Erro de servidor")
	public ResponseEntity<UsuarioDTOResponse> insertUser(@RequestBody UsuarioDTORequest uDTO) {
		return ResponseEntity.ok(service.insertUsuario(uDTO));
	}

	@PostMapping("/login")
	@Operation(summary = "Login de Usuários", description = "Faz login de usuário")
	@ApiResponse(responseCode = "200", description = "Usuário logado com sucesso")
	@ApiResponse(responseCode = "401", description = "Credenciais inválidas")
	@ApiResponse(responseCode = "500", description = "Erro de servidor")
	public String login(@RequestBody LoginDTORequest loginDTO) {
		return service.loginUsuario(loginDTO);
	}

	@GetMapping
	@Operation(summary = "Buscar dados de usuário por email", description = "busca todos os dados do usuário")
	@ApiResponse(responseCode = "200", description = "Usuário encontrado")
	@ApiResponse(responseCode = "404", description = "Usuário não encontrado")
	@ApiResponse(responseCode = "500", description = "Erro de servidor")
	public ResponseEntity<UsuarioDTOResponse> buscaUsuarioPorEmail(@RequestParam("email") String email,
			@RequestHeader(name = "Authorization", required = false) String token) {
		return ResponseEntity.ok(service.buscarUsuarioPorEmail(email, token));
	}

	@DeleteMapping("/{email}")
	@Operation(summary = "Deletar Usuários", description = "Deleta um usuário por ID")
	@ApiResponse(responseCode = "200", description = "Usuário deletado")
	@ApiResponse(responseCode = "404", description = "Usuário não encontrado")
	@ApiResponse(responseCode = "500", description = "Erro de servidor")
	public ResponseEntity<Void> deletaUsuarioPorEmail(@PathVariable String email,
			@RequestHeader(name = "Authorization", required = false) String token) {
		service.deletaUsuarioPorEmail(email, token);
		return ResponseEntity.ok().build();
	}

	@PutMapping
	@Operation(summary = "Atualizar dados de usuário", description = "Atualiza um usuário por ID")
	@ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso")
	@ApiResponse(responseCode = "404", description = "Usuário não encontrado")
	@ApiResponse(responseCode = "500", description = "Erro de servidor")
	public ResponseEntity<UsuarioDTOResponse> atualizaDadosUsuario(@RequestBody UsuarioDTORequest dto,
			@RequestHeader(name = "Authorization", required = false) String token) {
		return ResponseEntity.ok(service.atualizaDadosUsuario(token, dto));
	}

	@PutMapping("/endereco")
	@Operation(summary = "Atualiza dados de endereço do usuário", description = "Atualiza dados do endereço cadastrado para um usuário")
	@ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso")
	@ApiResponse(responseCode = "404", description = "Endereço não encotrado")
	@ApiResponse(responseCode = "500", description = "Erro de servidor")
	public ResponseEntity<EnderecoDTOResponse> atualizaEndereco(@RequestBody EnderecoDTORequest dto, @RequestParam("id") Long id,
			@RequestHeader(name = "Authorization", required = false) String token) {
		return ResponseEntity.ok(service.atualizaEndereco(id, dto, token));
	}

	@PutMapping("/telefone")
	@Operation(summary = "Atualiza dados de telefone do usuário", description = "atualiza dados do telefone cadastrado para um usuário")
	@ApiResponse(responseCode = "200", description = "Telefone atualizado com sucesso")
	@ApiResponse(responseCode = "404", description = "Telefone já cadastrado")
	@ApiResponse(responseCode = "500", description = "Erro de servidor")
	public ResponseEntity<TelefoneDTOResponse> atualizaTelefone(@RequestBody TelefoneDTORequest dto, @RequestParam("id") Long id,
			@RequestHeader(name = "Authorization", required = false) String token) {
		return ResponseEntity.ok(service.atualizaTelefone(id, dto, token));
	}

	@PostMapping("/endereco")
	@Operation(summary = "Cadastra endereço do usuário", description = "Cadastra dados do endereço para um usuário")
	@ApiResponse(responseCode = "200", description = "Endereço criado com sucesso")
	@ApiResponse(responseCode = "404", description = "Endereço não encontrado")
	@ApiResponse(responseCode = "500", description = "Erro de servidor")
	public ResponseEntity<EnderecoDTOResponse> cadastraEndereco(@RequestBody EnderecoDTORequest dto,
			@RequestHeader(name = "Authorization", required = false) String token) {
		return ResponseEntity.ok(service.cadastraEndereco(token, dto));
	}

	@PostMapping("/telefone")
	@Operation(summary = "Cadastra Telefone do usuário", description = "Cadastra dados do telefone para um usuário")
	@ApiResponse(responseCode = "200", description = "Telefone criado com sucesso")
	@ApiResponse(responseCode = "404", description = "Telefone não encontrado")
	@ApiResponse(responseCode = "500", description = "Erro de servidor")
	public ResponseEntity<TelefoneDTOResponse> cadastraTelefone(@RequestBody TelefoneDTORequest dto,
			@RequestHeader(name = "Authorization", required = false) String token) {
		return ResponseEntity.ok(service.cadastraTelefone(token, dto));
	}
}
