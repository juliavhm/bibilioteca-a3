package service;

import java.util.ArrayList;
import java.util.Comparator;
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

	public List<Livro> listaLivros() {
		return livros;
	};

	public Livro buscarPorTituloBinaria(String titulo) {
		List<Livro> ordenados = ordenarPorTitulo();
		int esquerda = 0;
		int direita = ordenados.size() - 1;

		while (esquerda <= direita) {
			int meio = (esquerda + direita) / 2;
			Livro atual = ordenados.get(meio);
			int comparacao = atual.getTitulo().compareToIgnoreCase(titulo);

			if (comparacao == 0)
				return atual;
			else if (comparacao < 0)
				esquerda = meio + 1;
			else
				direita = meio - 1;
		}
		return null;
	}

	public Livro buscarPorAutorBinaria(String autor) {
		List<Livro> ordenados = ordenarPorAutor();
		int esquerda = 0;
		int direita = ordenados.size() - 1;

		while (esquerda <= direita) {
			int meio = (esquerda + direita) / 2;
			Livro atual = ordenados.get(meio);
			int comparacao = atual.getAutor().compareToIgnoreCase(autor);

			if (comparacao == 0)
				return atual;
			else if (comparacao < 0)
				esquerda = meio + 1;
			else
				direita = meio - 1;
		}
		return null;
	}

	public Livro buscarPorAnoBinaria(Integer ano) {
		List<Livro> ordenados = ordenarPorAno();
		int esquerda = 0;
		int direita = ordenados.size() - 1;

		while (esquerda <= direita) {
			int meio = (esquerda + direita) / 2;
			Livro atual = ordenados.get(meio);
			if (atual.getAno().equals(ano))
				return atual;
			else if (atual.getAno() < ano)
				esquerda = meio + 1;
			else
				direita = meio - 1;
		}
		return null;
	}

	public Livro buscarPorGenero(Genero genero) {
		return livros.stream().filter(l -> l.getGenero() == genero).findFirst().orElse(null);
	}

	private List<Livro> ordenar(Comparator<Livro> comparador) {
		List<Livro> copia = new ArrayList<>(livros);
		int n = copia.size();
		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < n - i - 1; j++) {
				if (comparador.compare(copia.get(j), copia.get(j + 1)) > 0) {
					Livro temp = copia.get(j);
					copia.set(j, copia.get(j + 1));
					copia.set(j + 1, temp);
				}
			}
		}
		return copia;
	}

	public List<Livro> ordenarPorTitulo() {
		return ordenar((a, b) -> a.getTitulo().compareToIgnoreCase(b.getTitulo()));
	}

	public List<Livro> ordenarPorAutor() {
		return ordenar((a, b) -> a.getAutor().compareToIgnoreCase(b.getAutor()));
	}

	public List<Livro> ordenarPorAno() {
		return ordenar((a, b) -> a.getAno().compareTo(b.getAno()));
	}

}
