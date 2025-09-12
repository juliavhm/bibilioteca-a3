package model;

public class Livro {
	private String titulo;
	private String autor;
	private Integer ano;
	private Genero genero;

	public Livro() {
	}

	public Livro(String titulo, String autor, Integer ano, Genero genero) {
		this.titulo = titulo;
		this.autor = autor;
		this.ano = ano;
		this.genero = genero;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Genero getGenero() {
		return genero;
	}

	public Genero setGenero(Genero genero) {
		return this.genero = genero;
	}

}
