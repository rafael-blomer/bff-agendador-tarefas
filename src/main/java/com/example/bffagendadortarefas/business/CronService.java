package com.example.bffagendadortarefas.business;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.example.usuario.business.dto.in.LoginDTORequest;
import com.example.usuario.business.dto.out.TarefasDTOResponse;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CronService {

	private TarefasService tarefaService;
	private EmailService emailService;
	private UsuarioService userService;
	
	@Value("${usuario.email}")
	private String email;
	
	@Value("${usuario.senha}")
	private String senha;
	
	@Scheduled(cron = "${cron.horario}")
	public void buscaTarefasDaProximaHora() {
		String token = login(converterParaRequestDTO());
		log.info("Iniciada a busca de tarefas.");
		LocalDateTime horaFutura = LocalDateTime.now().plusHours(1);
		LocalDateTime horaFuturaMaisCinco = LocalDateTime.now().plusHours(1).plusMinutes(5);
		List<TarefasDTOResponse> list = tarefaService.buscarTarefasAgendadasPorPeriodo(horaFutura, horaFuturaMaisCinco, token);
		log.info("Tarefas encontradas" + list);
		list.forEach(tarefa -> {emailService.enviaEmail(tarefa);
			log.info("Email enviado para usuário" + tarefa.getEmailUsuario());
			tarefaService.alteraStatus(StatusNotificacaoEnum.NOTIFICADO, tarefa.getId(), token);});
		log.info("Finalizada a busca por notificação de tarefas.");
	}
	
	public String login(LoginDTORequest dto) {
		return userService.loginUsuario(dto);
	}
	
	public LoginDTORequest converterParaRequestDTO() {
		return LoginDTORequest.builder()
				.email(email)
				.senha(senha)
				.build();
	}
}
