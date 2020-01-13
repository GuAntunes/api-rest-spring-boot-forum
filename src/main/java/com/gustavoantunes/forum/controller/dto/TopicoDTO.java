package com.gustavoantunes.forum.controller.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.gustavoantunes.forum.model.Topico;

public class TopicoDTO {

	private Long id;
	private String titulo;
	private String mensagem;
	private LocalDateTime data;

	public TopicoDTO(Topico topico) {
		super();
		this.id = topico.getId();
		this.titulo = topico.getTitulo();
		this.mensagem = topico.getMensagem();
		this.data = topico.getDataCriacao();
	}

	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public LocalDateTime getData() {
		return data;
	}

	public static List<TopicoDTO> converter(List<Topico> topicos) {

		return topicos.stream().map(TopicoDTO::new).collect(Collectors.toList());
	}

}
