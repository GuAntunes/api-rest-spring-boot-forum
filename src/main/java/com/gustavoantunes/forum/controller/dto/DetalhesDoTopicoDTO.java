package com.gustavoantunes.forum.controller.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.gustavoantunes.forum.model.StatusTopico;
import com.gustavoantunes.forum.model.Topico;

public class DetalhesDoTopicoDTO {
	private Long id;
	private String titulo;
	private String mensagem;
	private LocalDateTime data;
	private String autor;
	private StatusTopico status;
	private List<RespostaDTO> respostas;

	public DetalhesDoTopicoDTO(Topico topico) {
		this.id = topico.getId();
		this.titulo = topico.getTitulo();
		this.mensagem = topico.getMensagem();
		this.data = topico.getDataCriacao();
		this.autor = topico.getAutor().getNome();
		this.status = topico.getStatus();
		this.respostas = new ArrayList<>();
		this.respostas.addAll(topico.getRespostas().stream().map(RespostaDTO::new).collect(Collectors.toList()));
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

	public String getAutor() {
		return autor;
	}

	public StatusTopico getStatus() {
		return status;
	}

	public List<RespostaDTO> getRespostas() {
		return respostas;
	}

}
