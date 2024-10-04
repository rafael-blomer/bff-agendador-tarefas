package com.example.bffagendadortarefas.business;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.agendadortarefas.infrastructure.client.TarefasClient;
import com.example.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.example.usuario.business.dto.in.TarefasDTORequest;
import com.example.usuario.business.dto.out.TarefasDTOResponse;

@Service
public class TarefasService {

	private TarefasClient client;	
	
	public TarefasDTOResponse gravarTarefa(String token, TarefasDTORequest dto) {
		return client.gravarTarefa(null, token);
	}
	
	public List<TarefasDTOResponse> buscarTarefasAgendadasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal, String token){
		return client.buscaListaDeTarefasPorPeriodo(dataInicial, dataFinal, token);
	}
	
	public List<TarefasDTOResponse> buscaTarefasPorEmail(String token) {
		return client.buscaTarefasPorEmail(token);
	}
	
	public void deletaTarefaPorId(String id, String token) {
		client.deletaTarefaPorId(id, token);
	}
	
	public TarefasDTOResponse alteraStatus(StatusNotificacaoEnum status, String id, String token) {
		return client.alteraStatusNotificacao(status, id, token);
	}
	
	public TarefasDTOResponse updateTarefas(TarefasDTORequest dto, String id, String token) {
		return client.updateTarefas(dto, id, token);
	}
}
