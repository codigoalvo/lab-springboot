package codigoalvo.lab.springboot.controller;

import codigoalvo.lab.springboot.event.RecursoCriadoEvento;
import codigoalvo.lab.springboot.exception.ResourceNotFoundException;
import codigoalvo.lab.springboot.model.Categoria;
import codigoalvo.lab.springboot.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categorias")
public class CategoriaController implements BaseController {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	public List<Categoria> listarCategorias() {
		return categoriaRepository.findAll();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Categoria> criarCategoria(@Valid @RequestBody Categoria categoria, HttpServletResponse response) {
		Categoria categoriaSalva = categoriaRepository.save(categoria);
		publisher.publishEvent(new RecursoCriadoEvento(this, response, categoria.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Categoria> buscarCategoriaPorId(@PathVariable Long id) {
		Optional<Categoria> optCategoria = categoriaRepository.findById(id);
		if (optCategoria.isPresent()) {
			return ResponseEntity.ok(optCategoria.get());
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removerCategoriaComId(@PathVariable Long codigo) {
		categoriaRepository.deleteById(codigo);
	}

	@PutMapping("/{codigo}")
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
