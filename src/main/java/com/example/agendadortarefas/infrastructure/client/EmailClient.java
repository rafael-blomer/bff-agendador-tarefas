package com.example.agendadortarefas.infrastructure.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.usuario.business.dto.out.TarefasDTOResponse;


@FeignClient(name = "notificacao", url = "${notificacao.url}")
public interface EmailClient {

	@PostMapping
	void enviarEmail(@RequestBody TarefasDTOResponse dto);

}