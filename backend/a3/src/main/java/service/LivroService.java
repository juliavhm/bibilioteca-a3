package service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import model.Genero;
import model.Livro;

@Service
public class LivroService {

	private List<Livro> livros = new ArrayList<>();

	public void adicionarLivro(Livro livro) {
		livros.add(livro);
	};

	public List<Livro> listarLivros() {
		return livros;
	};

	public List<Livro> buscarPorAnoLinear(Integer ano) {
		List<Livro> resultados = new ArrayList<>();
		for (Livro livro : livros) {
			if (livro.getAno().equals(ano)) {
				resultados.add(livro);
			}
		}
		return resultados;
	};

	public List<Livro> buscarPorAutorLinear(String autor) {
		List<Livro> resultados = new ArrayList<>();
		for (Livro livro : livros) {
			if (livro.getAutor().equalsIgnoreCase(autor)) {
				resultados.add(livro);
			}
		}
		return resultados;
	};

	public Livro buscarPorTituloLinear(String titulo) {
		for (Livro livro : livros) {
			if (livro.getTitulo().equals(titulo)) {
				return livro;
			}
		}
		return null;
	};

	public List<Livro> buscarPorGenero(Genero genero) {
		List<Livro> resultados = new ArrayList<>();
		for (Livro livro : livros) {
			if (livro.getGenero() == genero) {
				resultados.add(livro);
			}
		}
		return resultados;
	};

	public List<Livro> buscarEOrdenar(String titulo, String autor, Integer ano, Genero genero, String ordenarPor) {
		List<Livro> resultados = new ArrayList<>();
		for (Livro livro : livros) {
			boolean match = true;
			if (titulo != null && !livro.getTitulo().equalsIgnoreCase(titulo))
				match = false;
			if (autor != null && !livro.getAutor().equalsIgnoreCase(autor))
				match = false;
			if (ano != null && !livro.getAno().equals(ano))
				match = false;
			if (genero != null && livro.getGenero() != genero)
				match = false;

			if (match)
				resultados.add(livro);
		}

		if (ordenarPor != null) {
			switch (ordenarPor.toLowerCase()) {
			case "titulo":
				resultados.sort((a, b) -> a.getTitulo().compareToIgnoreCase(b.getTitulo()));
				break;
			case "autor":
				resultados.sort((a, b) -> a.getAutor().compareToIgnoreCase(b.getAutor()));
				break;
			case "ano":
				resultados.sort((a, b) -> a.getAno().compareTo(b.getAno()));
				break;
			}
		}

		return resultados;
	};

}
