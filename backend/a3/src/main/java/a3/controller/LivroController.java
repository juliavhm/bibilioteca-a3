package a3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import a3.dto.MensagemErro;
import a3.model.Genero;
import a3.service.LivroService;
import a3.model.Livro;

@RestController
@RequestMapping("/livros")
public class LivroController {

	@Autowired
	private LivroService livroService;

	@GetMapping
	public List<Livro> listarLivros() {
		return livroService.listarLivros();
	}

	@PostMapping
	public ResponseEntity<Void> adicionarLivros(@RequestBody List<Livro> livros) {
		livros.forEach(livroService::adicionarLivro);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	};

	@GetMapping("titulo/{titulo}")
	public ResponseEntity<?> buscarPorTitulo(@PathVariable String titulo) {
		Livro livro = livroService.buscarPorTituloLinear(titulo);
		if (livro == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MensagemErro("Livro não encontrado :("));
		}
		return ResponseEntity.ok(livro);
	};

	@GetMapping("/autor/{autor}")
	public ResponseEntity<?> buscarPorAutor(@PathVariable String autor) {
		List<Livro> livro = livroService.buscarPorAutorLinear(autor);
		if (livro.isEmpty())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MensagemErro("Autor não encontrado :("));
		return ResponseEntity.ok(livro);
	};

	@GetMapping("/ano/{ano}")
	public ResponseEntity<?> buscarPorAno(@PathVariable Integer ano) {
		List<Livro> livro = livroService.buscarPorAnoLinear(ano);
		if (livro.isEmpty())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MensagemErro("Nenhum livro com esse ano :("));
		return ResponseEntity.ok(livro);
	};

	@GetMapping("/genero/{genero}")
	public ResponseEntity<?> buscarPorGenero(@PathVariable Genero genero) {
		List<Livro> livros = livroService.buscarPorGenero(genero);

		if (livros.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new MensagemErro("Não existe livros com esse gênero :("));
		}

		return ResponseEntity.ok(livros);
	};

	@GetMapping("/buscar")
	public ResponseEntity<?> buscarEOrdenar(@RequestParam(required = false) String titulo,
			@RequestParam(required = false) String autor, @RequestParam(required = false) Integer ano,
			@RequestParam(required = false) Genero genero, @RequestParam(required = false) String ordenar) {

		List<Livro> resultados = livroService.buscarEOrdenar(titulo, autor, ano, genero, ordenar);

		if (resultados.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new MensagemErro("Nenhum livro encontrado com os critérios fornecidos :("));
		}

		return ResponseEntity.ok(resultados);
	};

};
