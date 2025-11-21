package a3.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import a3.model.Genero;
import a3.model.Livro;
import a3.repository.LivroRepository;

@Component
public class Mocks implements CommandLineRunner {

	@Autowired
	LivroRepository livroRepository;

	@Override
	public void run(String... args) throws Exception {
		List<Livro> livros = List.of(

				new Livro("A Hipótese do Amor", "Ali Hazelwood", 2021, Genero.ROMANCE),

				new Livro("Orgulho e Preconceito", "Jane Austen", 1813, Genero.ROMANCE),

				new Livro("O Hobbit", "J.R.R. Tolkien", 1937, Genero.FANTASIA),

				new Livro("O Senhor dos Anéis", "J.R.R. Tolkien", 1954, Genero.FANTASIA),

				new Livro("Duna", "Frank Herbert", 1965, Genero.FICCAO_CIENTIFICA),

				new Livro("Harry Potter e a Pedra Filosofal", "J.K. Rowling", 1997, Genero.FANTASIA),

				new Livro("A Revolução dos Bichos", "George Orwell", 1945, Genero.FICCAO_CIENTIFICA),

				new Livro("A Revolução dos Bichos", "George Orwell", 1945, Genero.FICCAO_CIENTIFICA),

				new Livro("1984", "George Orwell", 1949, Genero.FICCAO_CIENTIFICA),

				new Livro("O messias de Duna", "Frank Herbert", 1968, Genero.FICCAO_CIENTIFICA));

		livroRepository.saveAll(livros);
	}
}
