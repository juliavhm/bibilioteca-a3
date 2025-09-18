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

}
