# Guia de Contribuição e Setup do Projeto Biblioteca Spring Boot

Este guia vai te ajudar a configurar o projeto, rodar a aplicação e testar os endpoints.

---

## Pré-requisitos

Antes de começar, você precisa ter instalado:

1. **Java JDK 17 ou superior**  
   - [Baixar JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)  
   - Configure a variável de ambiente `JAVA_HOME`.  
   - Verifique no terminal:
     ```bash
     java -version
     ```

2. **Spring Tools Suite (STS)**  
   - [Baixar STS](https://spring.io/tools)  
   - Alternativa: Eclipse com plugin Spring Tools.

3. **Postman**  
   - [Baixar Postman](https://www.postman.com/downloads/)  
   - Usado para testar os endpoints REST.

4. **Git**  
   - Para clonar o repositório:
     ```bash
     git clone <URL_DO_REPO>
     ```

## ENDPOINTS DISPONÍVEIS

| Método | URL                             | Descrição                                                                          |     |                            |
| ------ | ------------------------------- | ---------------------------------------------------------------------------------- | --- | -------------------------- |
| GET    | /livros                         | Lista todos os livros                                                              |     |                            |
| POST   | /livros                         | Adiciona livros (body em JSON)                                                     |     |                            |
| GET    | /livros/buscar                  | Busca livros com filtros opcionais (`titulo`, `autor`, `ano`, `genero`, `ordenar`) |     |                            |
| GET    | /livros/titulo/{titulo}         | Busca livro pelo título                                                            |     |                            |
| GET    | /livros/autor/{autor}           | Busca livros pelo autor                                                            |     |                            |
| GET    | /livros/ano/{ano}               | Busca livros pelo ano                                                              |     |                            |
| GET    | /livros/genero/{genero}         | Busca livros pelo gênero                                                           |     |                            |
| GET    | /livros/ordenar?criterio=titulo | autor                                                                              | ano | Ordena livros por critério |


* A aplicação rodará em http://localhost:8080/.