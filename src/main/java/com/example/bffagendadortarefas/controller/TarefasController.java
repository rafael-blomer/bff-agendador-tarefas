package com.example.bffagendadortarefas.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.example.agendadortarefas.infrastructure.security.SecurityConfig;
import com.example.bffagendadortarefas.business.TarefasService;
import com.example.usuario.business.dto.in.TarefasDTORequest;
import com.example.usuario.business.dto.out.TarefasDTOResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/tarefas")
@Tag(name = "Tarefas", description = "Cadastro e login de Tarefas")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class TarefasController {

	@Autowired
	private TarefasService service;

	@PostMapping
	@Operation(summary = "Salvar Tarefa", description = "Cria uma nova Tarefa")
	@ApiResponse(responseCode = "200", description = "Tarefa salvo com sucesso")
	@ApiResponse(responseCode = "400", description = "Tarefa já cadastrado")
	@ApiResponse(responseCode = "500", description = "Erro de servidor")
	public ResponseEntity<TarefasDTOResponse> gravarTarefa(@RequestBody TarefasDTORequest dto,
			@RequestHeader(name = "Authorization", required = false) String token) {
		return ResponseEntity.ok(service.gravarTarefa(token, dto));
	}

	@GetMapping("/eventos")
	@Operation(summary = "Busca Tarefas por tempo", description = "Busca uma lista de tarefas cadastrada durante um periodo de tempo")
	@ApiResponse(responseCode = "200", description = "Tarefas encontradas")
	@ApiResponse(responseCode = "500", description = "Erro de servidor")
	public ResponseEntity<List<TarefasDTOResponse>> buscaListaDeTarefasPorPeriodo(
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
			@RequestHeader(name = "Authorization", required = false) String token) {
		return ResponseEntity.ok(service.buscarTarefasAgendadasPorPeriodo(dataInicial, dataFinal, token));
	}

	@GetMapping
	@Operation(summary = "Busca Tarefas por email", description = "busca uma lista de tarefas por usuário")
	@ApiResponse(responseCode = "200", description = "Tarefas encontradas")
	@ApiResponse(responseCode = "500", description = "Erro de servidor")
	public ResponseEntity<List<TarefasDTOResponse>> buscaTarefasPorEmail(@RequestHeader(name = "Authorization", required = false) String token) {
		return ResponseEntity.ok(service.buscaTarefasPorEmail(token));
	}

	@DeleteMapping
	@Operation(summary = "Delete Tarefa por ID", description = "Deleta tarefas cadastradas por ID")
	@ApiResponse(responseCode = "200", description = "Tarefa deletada com sucesso")
	@ApiResponse(responseCode = "500", description = "Erro de servidor")
	public ResponseEntity<Void> deletaTarefaPorId(@RequestParam("id") String id,
			@RequestHeader(name = "Authorization", required = false) String token) {
		service.deletaTarefaPorId(id, token);
		return ResponseEntity.ok().build();
	}

	@PatchMapping
	@Operation(summary = "Altera status de Tarefa", description = "Altera o status de notificação de tarefa")
	@ApiResponse(responseCode = "200", description = "Status de Tarefa alterado")
	@ApiResponse(responseCode = "500", description = "Erro de servidor")
	public ResponseEntity<TarefasDTOResponse> alteraStatusNotificacao(@RequestParam("status") StatusNotificacaoEnum status,
			@RequestParam("id") String id, @RequestHeader(name = "Authorization", required = false) String token) {
		return ResponseEntity.ok(service.alteraStatus(status, id, token));
	}

	@PutMapping
	@Operation(summary = "Atualiza dados de Tarefa", description = "Altera o dados de tarefa")
	@ApiResponse(responseCode = "200", description = "Dados de Tarefa alterado")
	@ApiResponse(responseCode = "500", description = "Erro de servidor")
	public ResponseEntity<TarefasDTOResponse> updateTarefas(@RequestBody TarefasDTORequest dto, @RequestParam("id") String id,
			@RequestHeader(name = "Authorization", required = false) String token) {
		return ResponseEntity.ok(service.updateTarefas(dto, id, token));
	}
}
