package codigoalvo.lab.springboot.controller;

import codigoalvo.lab.springboot.event.RecursoCriadoEvento;
import codigoalvo.lab.springboot.exception.ResourceNotFoundException;
import codigoalvo.lab.springboot.model.Usuario;
import codigoalvo.lab.springboot.repository.UsuarioRepository;
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
import java.util.Objects;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/usuario")
public class UsuarioController implements BaseController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	@PreAuthorize("hasAuthority('USUARIO_CONSULTAR') and #oauth2.hasScope('read')")
	public List<Usuario> listarUsuarios() {
		log.info("UsuarioController.listarUsuarios() -> Usuario autenticado: "+ customUserDetailsService.getAuthenticated());
		return usuarioRepository.findAll();
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('USUARIO_CONSULTAR') and #oauth2.hasScope('read')")
	public ResponseEntity<Usuario> buscarUsuarioPorId(@PathVariable Long id) {
		Optional<Usuario> optUsuario = usuarioRepository.findById(id);
		if (optUsuario.isPresent()) {
			return ResponseEntity.ok(optUsuario.get());
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasAuthority('USUARIO_ALTERAR') and #oauth2.hasScope('write')")
	public ResponseEntity<Usuario> criarUsuario(@Valid @RequestBody Usuario usuario, HttpServletResponse response) {
		Usuario usuarioSalva = usuarioRepository.save(usuario);
		publisher.publishEvent(new RecursoCriadoEvento(this, response, usuario.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSalva);
	}

	@DeleteMapping("/{codigo}")
	@PreAuthorize("hasAuthority('USUARIO_ALTERAR') and #oauth2.hasScope('write')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removerUsuarioComId(@PathVariable Long codigo) {
		usuarioRepository.deleteById(codigo);
	}

	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('USUARIO_ALTERAR') and #oauth2.hasScope('write')")
	public ResponseEntity<Usuario> alterarUsuarioComId(@PathVariable Long codigo, @Valid @RequestBody Usuario usuario, HttpServletResponse response) {
		Optional<Usuario> optUsuario = usuarioRepository.findById(codigo);
		log.info("UsuarioController.alterarUsuarioComId("+codigo+") - usuarioBanco: "+optUsuario.get());
		log.info("UsuarioController.alterarUsuarioComId("+codigo+") - usuarioRequest: "+usuario);
		if (optUsuario.isPresent()) {
			Usuario usuarioSalva = optUsuario.get();

			if (Objects.nonNull(usuario.getEmail())) {
				usuarioSalva.setEmail(usuario.getEmail());
			}
			if (Objects.nonNull(usuario.getPassword())) {
				usuarioSalva.changePassword(usuario.getPassword());
			}

			if (Objects.nonNull(usuario.getTelefone())) {
				usuarioSalva.setTelefone(usuario.getTelefone());
			}
			if (Objects.nonNull(usuario.getNome())) {
				usuarioSalva.setNome(usuario.getNome());
			}
			usuario = usuarioRepository.save(usuarioSalva);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(usuario);
		}
		throw new ResourceNotFoundException(Usuario.class, codigo);
	}

	//TODO: Parcial Update with Patch (RFC 7396) -> https://tools.ietf.org/html/rfc7396 <-

}
