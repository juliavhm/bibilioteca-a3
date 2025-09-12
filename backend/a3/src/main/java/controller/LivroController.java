package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import dto.MensagemErro;
import model.Genero;
import model.Livro;
import service.LivroService;

@RestController
@RequestMapping("/livros")
public class LivroController {

	@Autowired
	private LivroService livroService;

	@GetMapping
	public List<Livro> listarLivros() {
		return livroService.listaLivros();
	}

	@PostMapping
	public ResponseEntity<Void> adicionarLivros(@RequestBody List<Livro> livros) {
		livros.forEach(livroService::adicionarLivro);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	};

	@GetMapping("titulo/{titulo}")
	public ResponseEntity<?> buscarPorTitulo(@PathVariable String titulo) {
		Livro livro = livroService.buscarPorTituloBinaria(titulo);
		if (livro == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MensagemErro("Livro não encontrado :("));
		}
		return ResponseEntity.ok(livro);
	};

	@GetMapping("/autor/{autor}")
	public ResponseEntity<?> buscarPorAutor(@PathVariable String autor) {
		Livro livro = livroService.buscarPorAutorBinaria(autor);
		if (livro == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MensagemErro("Autor não encontrado :("));
		return ResponseEntity.ok(livro);
	};

	@GetMapping("/ano/{ano}")
	public ResponseEntity<?> buscarPorAno(@PathVariable Integer ano) {
		Livro livro = livroService.buscarPorAnoBinaria(ano);
		if (livro == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new MensagemErro("Não existe livro com esse ano :("));
		return ResponseEntity.ok(livro);
	};

	@GetMapping("/genero/{genero}")
	public ResponseEntity<?> buscarPorGenero(@PathVariable Genero genero) {
	    List<Livro> livros = livroService.buscarPorGenero(genero);
	    
	    if (livros.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                             .body(new MensagemErro("Não existem livros com esse gênero :("));
	    }
	    
	    return ResponseEntity.ok(livros);
	}

};
