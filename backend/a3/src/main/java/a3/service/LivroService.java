package a3.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

import a3.model.Genero;
import a3.model.Livro;
import a3.repository.LivroRepository;
import a3.utils.RecursoNaoEncontradoException;


@Service
public class LivroService {

	@Autowired
	private LivroRepository livroRepository;

	public List<Livro> listarLivros() {
		List<Livro> livros = livroRepository.findAll();

		if (livros.isEmpty()) {
			throw new RecursoNaoEncontradoException("Lista vazia, adicione algum livro.");
		}

		return livros;

	}

	public List<Livro> listarLivrosDecrescenteId() {
		Sort sortByIdDesc = Sort.by(Sort.Direction.DESC, "id");
		List<Livro> livrosDesc = livroRepository.findAll(sortByIdDesc);
		
		if (livrosDesc.isEmpty()) {
			throw new RecursoNaoEncontradoException("Lista vazia, adicione algum livro.");
		}
		
		return livrosDesc;
	}

	public void adicionarLivro(Livro livro) {

		if (livro.getTitulo() == null || livro.getTitulo().isBlank()) {
			throw new IllegalArgumentException("Título é obrigatório");
		}
		if (livro.getAutor() == null || livro.getAutor().isBlank()) {
			throw new IllegalArgumentException("Autor é obrigatório");
		}
		if (livro.getAno() == null) {
			throw new IllegalArgumentException("Ano é obrigatório");
		}
		if (livro.getGenero() == null) {
			throw new IllegalArgumentException("Gênero é obrigatório");
		}

		livroRepository.save(livro);
	}

	public Livro buscarPorTitulo(String titulo) {
		return livroRepository.findByTitulo(titulo).orElseThrow(
				() -> new RecursoNaoEncontradoException("Livro com título '" + titulo + "' não encontrado"));
	}

	public List<Livro> buscarPorAutor(String autor) {
		List<Livro> livro = livroRepository.findByAutorIgnoreCase(autor);
		if (livro.isEmpty()) {
			throw new RecursoNaoEncontradoException("Autor com nome '" + autor + "' não encontrado");
		}
		return livro;
	}

	public List<Livro> buscarPorAno(Integer ano) {
		List<Livro> livro = livroRepository.findByAno(ano);
		if (livro.isEmpty()) {
			throw new RecursoNaoEncontradoException("Não há nenhum livro com o ano '" + ano + "' disponível");
		}
		return livro;
	}

	public List<Livro> buscarPorGenero(Genero genero) {
		List<Livro> livro = livroRepository.findByGenero(genero);
		if (livro.isEmpty()) {
			throw new RecursoNaoEncontradoException("Nenhum livro com o gênero '" + genero + "' disponível");
		}
		return livroRepository.findByGenero(genero);
	}

	public Livro atualizarLivro(Integer id, Livro livroAtualizado) {
		Optional<Livro> livro = livroRepository.findById(id);

		if (livro.isEmpty()) {
			throw new RecursoNaoEncontradoException("Livro com ID " + id + " não encontrado");
		}

		Livro livroExistente = livro.get();

		if (livroAtualizado.getTitulo() != null && !livroAtualizado.getTitulo().isBlank()) {
			livroExistente.setTitulo(livroAtualizado.getTitulo());
		}

		if (livroAtualizado.getAutor() != null && !livroAtualizado.getAutor().isBlank()) {
			livroExistente.setAutor(livroAtualizado.getAutor());
		}
		return livroRepository.save(livroExistente);
	}

	public void deletarLivro(Integer id) {
		Optional<Livro> livro = livroRepository.findById(id);

		if (livro.isEmpty()) {
			throw new RecursoNaoEncontradoException("Livro com ID " + id + " não encontrado");
		}

		livroRepository.delete(livro.get());
	}

	public List<Livro> buscarEOrdenar(String titulo, String autor, Integer ano, Genero genero, String ordenar) {
		List<Livro> livros = livroRepository.findAll();

		List<Livro> filtrados = livros.stream().filter(l -> (titulo == null || l.getTitulo().equalsIgnoreCase(titulo)))
				.filter(l -> (autor == null || l.getAutor().equalsIgnoreCase(autor)))
				.filter(l -> (ano == null || (l.getAno() != null && l.getAno().equals(ano))))
				.filter(l -> (genero == null || l.getGenero() == genero)).collect(Collectors.toList());

		// Ordenação opcional
		if (ordenar != null) {
			switch (ordenar.toLowerCase()) {
			case "titulo":
				filtrados.sort(Comparator.comparing(Livro::getTitulo, String.CASE_INSENSITIVE_ORDER));
				break;
			case "autor":
				filtrados.sort(Comparator.comparing(Livro::getAutor, String.CASE_INSENSITIVE_ORDER));
				break;
			case "ano":
				filtrados.sort(Comparator.comparing(Livro::getAno));
				break;
			default:
				break;
			}
		}

		return filtrados;
	}
}
