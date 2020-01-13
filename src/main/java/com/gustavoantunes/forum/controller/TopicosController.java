package com.gustavoantunes.forum.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.gustavoantunes.forum.controller.dto.AtualizacaoTopicoFormDTO;
import com.gustavoantunes.forum.controller.dto.DetalhesDoTopicoDTO;
import com.gustavoantunes.forum.controller.dto.TopicoDTO;
import com.gustavoantunes.forum.controller.form.TopicoForm;
import com.gustavoantunes.forum.model.Topico;
import com.gustavoantunes.forum.repository.CursoRepository;
import com.gustavoantunes.forum.repository.TopicoRepository;

// Ao utilizar o RestController o Spring assume que todos os métodos dentro da classe
// irão utilizar o @ResponseBody
@RestController
@RequestMapping(value = "/topicos")
public class TopicosController {

	@Autowired
	private TopicoRepository topicoRepository;

	@Autowired
	private CursoRepository cursoRepository;

	// @RequestParam
	// O uso desta anotação indica ao spring que a váriavel será passada pela URL da
	// requisição
	// Logo também este parâmetro fica obrigatório, para remover a obrigatoriedade
	// pasta passar
	// o parâmetro required false @RequestParam(required = false)

	@GetMapping
	public Page<TopicoDTO> lista(@RequestParam(required = false) String nomeCurso, @RequestParam int pagina,
			@RequestParam int qtd, @RequestParam String ordenacao) {

		Pageable paginacao = PageRequest.of(pagina, qtd, Direction.ASC, ordenacao);

		Page<Topico> topicos;
		if (nomeCurso == null) {
			topicos = topicoRepository.findAll(paginacao);
		} else {
			topicos = topicoRepository.findByCursoNome(nomeCurso, paginacao);
		}
		return TopicoDTO.converter(topicos);
	}

	@PostMapping
	@Transactional
	public ResponseEntity<TopicoDTO> cadastrar(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriBuilder) {
		Topico topico = form.converter(cursoRepository);
		topicoRepository.save(topico);
		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
		return ResponseEntity.created(uri).body(new TopicoDTO(topico));
	}

	@GetMapping("/{id}")
	public ResponseEntity<DetalhesDoTopicoDTO> detalhar(@PathVariable Long id) {
		Optional<Topico> topico = topicoRepository.findById(id);
		if (topico.isPresent()) {
			return ResponseEntity.ok(new DetalhesDoTopicoDTO(topico.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	// O Transactional indica para o Spring que este método é uma transação, então
	// ele irá comitar no final a requisição
	// significa que não precisamos chamar nenhum método do repository para update
	// ou save, pois ao final do método as modificações serão
	// realizadas automaticamente no banco de dados
	@Transactional
	public ResponseEntity<TopicoDTO> atualizar(@PathVariable Long id, @RequestBody AtualizacaoTopicoFormDTO form) {
		// A utilização do Optional impede que o código lance uma exception,
		// Assim podemos validar posteriormente caso não exista o registro na base
		Optional<Topico> optional = topicoRepository.findById(id);
		if (optional.isPresent()) {
			Topico topico = form.atualizar(id, topicoRepository);
			return ResponseEntity.ok(new TopicoDTO(topico));
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Topico> optional = topicoRepository.findById(id);
		if (optional.isPresent()) {
			topicoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
