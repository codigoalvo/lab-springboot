package codigoalvo.lab.springboot.controller;

import codigoalvo.lab.springboot.event.RecursoCriadoEvento;
import codigoalvo.lab.springboot.exception.ResourceNotFoundException;
import codigoalvo.lab.springboot.model.Categoria;
import codigoalvo.lab.springboot.repository.CategoriaRepository;
import codigoalvo.lab.springboot.security.service.CustomUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/categorias")
public class CategoriaController implements BaseController {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	@PreAuthorize("hasAuthority('CATEGORIA_CONSULTAR') and #oauth2.hasScope('read')")
	public List<Categoria> listarCategorias() {
		log.debug("CategoriaController.listarCategorias() -> Usuario autenticado: "+ customUserDetailsService.getAuthenticated());
		return categoriaRepository.findAll();
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('CATEGORIA_CONSULTAR') and #oauth2.hasScope('read')")
	public ResponseEntity<Categoria> buscarCategoriaPorId(@PathVariable Long id) {
		Optional<Categoria> optCategoria = categoriaRepository.findById(id);
		if (optCategoria.isPresent()) {
			return ResponseEntity.ok(optCategoria.get());
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasAuthority('CATEGORIA_ALTERAR') and #oauth2.hasScope('write')")
	public ResponseEntity<Categoria> criarCategoria(@Valid @RequestBody Categoria categoria, HttpServletResponse response) {
		Categoria categoriaSalva = categoriaRepository.save(categoria);
		publisher.publishEvent(new RecursoCriadoEvento(this, response, categoria.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
	}

	@DeleteMapping("/{codigo}")
	@PreAuthorize("hasAuthority('CATEGORIA_ALTERAR') and #oauth2.hasScope('write')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removerCategoriaComId(@PathVariable Long codigo) {
		categoriaRepository.deleteById(codigo);
	}

	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('CATEGORIA_ALTERAR') and #oauth2.hasScope('write')")
	public ResponseEntity<Categoria> alterarCategoriaComId(@PathVariable Long codigo, @Valid @RequestBody Categoria categoria, HttpServletResponse response) {
		Optional<Categoria> optCategoria = categoriaRepository.findById(codigo);
		if (optCategoria.isPresent()) {
			Categoria categoriaSalva = optCategoria.get();
			categoriaSalva.setNome(categoria.getNome());
			categoria = categoriaRepository.save(categoriaSalva);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(categoria);
		}
		throw new ResourceNotFoundException(Categoria.class, codigo);
	}

	//TODO: Parcial Update with Patch (RFC 7396) -> https://tools.ietf.org/html/rfc7396 <-

}
