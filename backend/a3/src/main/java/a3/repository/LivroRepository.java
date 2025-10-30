package a3.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import a3.model.Genero;
import a3.model.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Integer> {
    Optional<Livro> findByTitulo(String titulo);
    List<Livro> findByAutorIgnoreCase(String autor);
    List<Livro> findByAno(Integer ano);
    List<Livro> findByGenero(Genero genero);
}
