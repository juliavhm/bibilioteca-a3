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
	public Livro adicionarLivro(@RequestBody Livro livro) {
		livroService.adicionarLivro(livro);
		return livro;
	}

	@GetMapping("/buscar/titulo")
	public ResponseEntity<?> buscarPorTitulo(@RequestParam String titulo) {
		Livro livro = livroService.buscarPorTituloBinaria(titulo);
		if (livro == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MensagemErro("Livro não encontrado :("));
		}
		return ResponseEntity.ok(livro);
	}

	@GetMapping("/buscar/autor")
	public ResponseEntity<?> buscarPorAutor(@RequestParam String autor) {
		Livro livro = livroService.buscarPorAutorBinaria(autor);
		if (livro == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MensagemErro("Autor não encontrado :("));
		return ResponseEntity.ok(livro);
	}

	@GetMapping("/buscar/ano")
	public ResponseEntity<?> buscarPorAno(@RequestParam Integer ano) {
		Livro livro = livroService.buscarPorAnoBinaria(ano);
		if (livro == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new MensagemErro("Não existe livro com esse ano :("));
		return ResponseEntity.ok(livro);
	}

	@GetMapping("/buscar/genero")
	public ResponseEntity<?> buscarPorGenero(@RequestParam Genero genero) {
		Livro livro = livroService.buscarPorGenero(genero);
		if (livro == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new MensagemErro("Não existe livro com esse gênero :("));
		return ResponseEntity.ok(livro);
	}

}
