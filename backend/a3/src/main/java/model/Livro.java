package model;

public class Livro {
	private Integer id;
	private String titulo;
	private String autor;
	private Integer ano;
	private String genero;

	public Livro() {
	}

	public Livro(String titulo, String autor, Integer ano, String genero) {
		this.titulo = titulo;
		this.autor = autor;
		this.ano = ano;
		this.genero = genero;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

}