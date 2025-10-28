package a3.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import a3.model.Genero;
import a3.model.Livro;
import a3.repository.LivroRepository;

@Service
public class LivroService {

	@Autowired
	private LivroRepository livroRepository;

	public List<Livro> listarLivros() {
		return livroRepository.findAll();
	}

	public void adicionarLivro(Livro livro) {
		livroRepository.save(livro);
	}

	public Livro buscarPorTituloLinear(String titulo) {
		List<Livro> livros = livroRepository.findAll();
		for (Livro livro : livros) {
			if (livro.getTitulo().equalsIgnoreCase(titulo)) {
				return livro;
			}
		}
		return null;
	}

	public List<Livro> buscarPorAutorLinear(String autor) {
		List<Livro> livros = livroRepository.findAll();
		List<Livro> resultado = new ArrayList<>();

		for (Livro livro : livros) {
			if (livro.getAutor().equalsIgnoreCase(autor)) {
				resultado.add(livro);
			}
		}
		return resultado;
	}

	// 🔹 Busca linear por ano
	public List<Livro> buscarPorAnoLinear(Integer ano) {
		List<Livro> livros = livroRepository.findAll();
		List<Livro> resultado = new ArrayList<>();

		for (Livro livro : livros) {
			if (livro.getAno() != null && livro.getAno().equals(ano)) {
				resultado.add(livro);
			}
		}
		return resultado;
	}

	public List<Livro> buscarPorGenero(Genero genero) {
		List<Livro> livros = livroRepository.findAll();
		List<Livro> resultado = new ArrayList<>();

		for (Livro livro : livros) {
			if (livro.getGenero() == genero) {
				resultado.add(livro);
			}
		}
		return resultado;
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

	public Livro atualizarLivro(Integer id, Livro livroAtualizado) {
		Optional<Livro> livro = livroRepository.findById(id);

		if (livro.isEmpty()) {
			return null;
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

	// 🔹 Deletar livro
	public void deletarLivro(Integer id) {
		if (livroRepository.findById(id) != null) {
			livroRepository.deleteById(id);
		}

	}
}
