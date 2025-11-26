package a3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import a3.model.Genero;
import a3.service.LivroService;
import a3.model.Livro;

@RestController
@RequestMapping("/livros")
public class LivroController {

	@Autowired
	private LivroService livroService;

	@GetMapping
	public List<Livro> listarLivros(@RequestParam(name = "ordenar", required = false) String ordenar,
			@RequestParam(name = "direcao", required = false) String direcao) {

		return livroService.listarLivrosOrdenado(ordenar, direcao);
	}

	@PostMapping
	public ResponseEntity<Void> adicionarLivro(@RequestBody Livro livro) {
		livroService.adicionarLivro(livro);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@GetMapping("titulo/{titulo}")
	public ResponseEntity<?> buscarPorTitulo(@PathVariable("titulo") String titulo) {
		Livro livro = livroService.buscarPorTitulo(titulo);
		return ResponseEntity.ok(livro);
	}

	@GetMapping("/autor/{autor}")
	public ResponseEntity<?> buscarPorAutor(@PathVariable("autor") String autor) {
		List<Livro> livro = livroService.buscarPorAutor(autor);
		return ResponseEntity.ok(livro);
	}

	@GetMapping("/ano/{ano}")
	public ResponseEntity<?> buscarPorAno(@PathVariable("ano") Integer ano) {
		List<Livro> livro = livroService.buscarPorAno(ano);
		return ResponseEntity.ok(livro);
	}

	@GetMapping("/genero/{genero}")
	public ResponseEntity<?> buscarPorGenero(@PathVariable("genero") Genero genero) {
		List<Livro> livros = livroService.buscarPorGenero(genero);
		return ResponseEntity.ok(livros);
	}

	@PatchMapping("atualizar/{id}")
	public ResponseEntity<?> atualizarLivro(@PathVariable("id") Integer id, @RequestBody Livro livro) {
		Livro resultado = livroService.atualizarLivro(id, livro);
		return ResponseEntity.ok(resultado);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarLivro(@PathVariable("id") Integer id) {
		livroService.deletarLivro(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/buscar")
	public ResponseEntity<?> buscarEOrdenar(@RequestParam(name = "titulo", required = false) String titulo,
			@RequestParam(name = "autor", required = false) String autor,
			@RequestParam(name = "ano", required = false) Integer ano,
			@RequestParam(name = "genero", required = false) Genero genero,
			@RequestParam(name = "ordenar", required = false) String ordenar) {

		List<Livro> resultados = livroService.buscarEOrdenar(titulo, autor, ano, genero, ordenar);
		return ResponseEntity.ok(resultados);
	}
}