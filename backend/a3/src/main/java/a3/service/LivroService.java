package a3.service;

import java.util.List;

import org.springframework.stereotype.Service;

import a3.model.Genero;
import a3.model.Livro;
import a3.repository.LivroRepository;

@Service
public class LivroService {

    private final LivroRepository livroRepository;

    // injeção automática do repository
    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public void adicionarLivro(Livro livro) {
        livroRepository.save(livro);
    }

    public List<Livro> listarLivros() {
        return livroRepository.findAll();
    }

    public List<Livro> buscarPorAnoLinear(Integer ano) {
        return livroRepository.findByAno(ano);
    }

    public List<Livro> buscarPorAutorLinear(String autor) {
        return livroRepository.findByAutorIgnoreCase(autor);
    }

    public Livro buscarPorTituloLinear(String titulo) {
        return livroRepository.findByTitulo(titulo);
    }

    public List<Livro> buscarPorGenero(Genero genero) {
        return livroRepository.findByGenero(genero);
    }

    public List<Livro> buscarEOrdenar(String titulo, String autor, Integer ano, Genero genero, String ordenarPor) {
        List<Livro> resultados = livroRepository.findAll();

        // filtros manuais (opcional, igual ao seu código antigo)
        if (titulo != null)
            resultados.removeIf(l -> !l.getTitulo().equalsIgnoreCase(titulo));
        if (autor != null)
            resultados.removeIf(l -> !l.getAutor().equalsIgnoreCase(autor));
        if (ano != null)
            resultados.removeIf(l -> !l.getAno().equals(ano));
        if (genero != null)
            resultados.removeIf(l -> l.getGenero() != genero);

        // ordenação opcional
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
    }
}
