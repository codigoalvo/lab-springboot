package codigoalvo.lab.springboot.controller;

import codigoalvo.lab.springboot.model.Categoria;
import codigoalvo.lab.springboot.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public List<Categoria> listar() {
        return categoriaRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Categoria> criar(@RequestBody Categoria categoria, HttpServletResponse response) {
       Categoria categoriaSalva = categoriaRepository.save(categoria);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}").buildAndExpand(categoria.getId()).toUri();
        response.setHeader("Location",uri.toASCIIString());
        return ResponseEntity.created(uri).body(categoriaSalva);
    }

    @GetMapping("/{id}")
    public Categoria buscarCategoriaPorId(@PathVariable Long id) {
        Optional<Categoria> optCategoria = categoriaRepository.findById(id);
        return optCategoria.orElse(null);
    }

}
