package com.example.usuario.business.dto.out;

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
public class UsuarioDTOResponse {
	private String nome, email, senha;
	private List<EnderecoDTOResponse> enderecos;
	private List<TelefoneDTOResponse> telefones;

}
