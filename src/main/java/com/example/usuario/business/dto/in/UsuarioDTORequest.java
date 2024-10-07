package com.example.usuario.business.dto.in;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDTORequest {
	private String nome, email, senha;
	private List<EnderecoDTORequest> enderecos;
	private List<TelefoneDTORequest> telefones;

}
