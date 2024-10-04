package com.example.bffagendadortarefas.business;

import org.springframework.stereotype.Service;

import com.example.agendadortarefas.infrastructure.client.EmailClient;
import com.example.usuario.business.dto.out.TarefasDTOResponse;

@Service
public class EmailService {

	private EmailClient client;

	public void enviaEmail(TarefasDTOResponse dto) {
		client.enviarEmail(dto);
		;
	}

}
