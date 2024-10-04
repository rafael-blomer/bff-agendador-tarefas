package com.example.bffagendadortarefas.business;

import org.springframework.stereotype.Service;

import com.example.agendadortarefas.infrastructure.client.UsuarioClient;
import com.example.usuario.business.dto.in.EnderecoDTORequest;
import com.example.usuario.business.dto.in.LoginDTORequest;
import com.example.usuario.business.dto.in.TelefoneDTORequest;
import com.example.usuario.business.dto.in.UsuarioDTORequest;
import com.example.usuario.business.dto.out.EnderecoDTOResponse;
import com.example.usuario.business.dto.out.TelefoneDTOResponse;
import com.example.usuario.business.dto.out.UsuarioDTOResponse;

@Service
public class UsuarioService {
	
	private UsuarioClient client;

	public UsuarioDTOResponse insertUsuario(UsuarioDTORequest uDTO) {
		return client.insertUser(uDTO);
	}
	
	public String loginUsuario(LoginDTORequest dto) {
		return client.login(dto);
	}

	public UsuarioDTOResponse buscarUsuarioPorEmail(String email, String token) {
		return client.buscaUsuarioPorEmail(email, token);
	}

	public void deletaUsuarioPorEmail(String email, String token) {
		client.deletaUsuarioPorEmail(email, token);;
	}
	
	public UsuarioDTOResponse atualizaDadosUsuario(String token, UsuarioDTORequest dto){
        return client.atualizaDadosUsuario(dto, token);
    }
	
	public EnderecoDTOResponse atualizaEndereco(Long idEndereco, EnderecoDTORequest dto, String token) {
		return client.atualizaEndereco(dto, idEndereco, token);
	}
	
	public TelefoneDTOResponse atualizaTelefone(Long idTelefone, TelefoneDTORequest dto, String token) {
		return client.atualizaTelefone(dto, idTelefone, token);
	}
	
	public EnderecoDTOResponse cadastraEndereco(String token, EnderecoDTORequest dto) {
		return client.cadastraEndereco(dto, token);
	}
	
	public TelefoneDTOResponse cadastraTelefone(String token, TelefoneDTORequest dto) {
		return client.cadastraTelefone(dto, token);
	}
}
