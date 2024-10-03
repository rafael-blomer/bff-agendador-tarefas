package com.example.agendadortarefas.infrastructure.client;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.example.usuario.business.dto.in.TarefasDTORequest;
import com.example.usuario.business.dto.out.TarefasDTOResponse;

@FeignClient(name = "agendador-tarefas", url = "${agendador-tarefas.url}")
public interface TarefasClient {

	@PostMapping
	TarefasDTOResponse gravarTarefa(@RequestBody TarefasDTORequest dto, @RequestHeader("Authorization") String token);

	@GetMapping("/eventos")
	List<TarefasDTOResponse> buscaListaDeTarefasPorPeriodo(
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
			@RequestHeader("Authorization") String token);

	@GetMapping
	List<TarefasDTOResponse> buscaTarefasPorEmail(@RequestHeader("Authorization") String token);

	@DeleteMapping
	void deletaTarefaPorId(@RequestParam("id") String id, @RequestHeader("Authorization") String token);

	@PatchMapping
	TarefasDTOResponse alteraStatusNotificacao(@RequestParam("status") StatusNotificacaoEnum status,
			@RequestParam("id") String id, @RequestHeader("Authorization") String token);

	@PutMapping
	TarefasDTOResponse updateTarefas(@RequestBody TarefasDTORequest dto, @RequestParam("id") String id,
			@RequestHeader("Authorization") String token);

}