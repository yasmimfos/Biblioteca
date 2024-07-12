# Biblioteca

Esse projeto foi pensado um sistema de uma biblioteca onde serão cadastrados livros, autores e editora para que seja possível encontrar livros que estejam disponibilizados para leitura.

## Ferramentas e Linguagens:
- Spring Boot
- JPA Repository
- Postgres

## Endpoints:
### Editora
  - POST/publishing - registrar editora - HTTP 201
  - GET/publishing - buscar todas as editoras - HTTP 200
  - GET/publishing/{id} - buscar editora por id - HTTP 200
  - PUT/publishing/{id} - editar editora - HTTP 200
  - DELETE/publishing/{id} - deletar editora - HTTP 200

  Para cadastro das editoras é necessário o campo:
```
{
	"company": "Companhia das Letras"
}
```
  Resposta esperada: HTTP 201
```
{
	"id_pub": 7,
	"company": "Companhia das Letras"
}
```

  #### Validações:
  - se a editora já estiver cadastrada: HTTP 400 -> The company has already been registered

![image](https://github.com/user-attachments/assets/f07e3f03-6442-4eb2-9de6-aa613bb6abc2)

     
### Autor
  - POST/author - registrar autor - HTTP 201
  - GET/author - buscar todas os autores - HTTP 200
  - GET/author/{id} - buscar autor por id - HTTP 200
  - PUT/author/{id} - editar autor - HTTP 200
  - DELETE/author/{id} - deletar autor - HTTP 200

  Para cadastro dos autores são necessários os campos:
```
{
	"name": "Rachel Smythe",
	"publishingCompany": "Sumario"
}
```
  Resposta esperada: HTTP 201
```
{
	"id_author": 4,
	"name": "Rachel Smythe",
	"publishingCompany": 5
}
```
**-> Nesse caso, a editora é devolvida com o id vinculado na tabela de editoras.**

  #### Validações:
  se o autor já estiver cadastrado: HTTP 400 -> The author has already been registered
  
  ![image](https://github.com/user-attachments/assets/403d874f-d92c-4416-9533-165b45fcf134)


  se a editora vinculada ao autor informada não existir no cadastro: HTTP 404 -> Company not found
 
![image](https://github.com/user-attachments/assets/07501780-f132-49d1-aa69-c4123e517424)


### Livros
  - POST/books - registrar livro - HTTP 201
  - GET/books - buscar todas os livros - HTTP 200
  - GET/books/{id} - buscar livro por id - HTTP 200
  - PUT/books/{id} - editar livro - HTTP 200
  - DELETE/books/{id} - deletar livro - HTTP 200

    Para cadastro dos livros são necessários os campos:
```
{
	"title": "Perdido Sem Você",
	"author": "Lycia Barros",
	"ageRange": 14,
	"pages": 300,
	"release": "2024-11-02",
	"genre": "romance"
}
```
  Resposta esperada: HTTP 201
```
{
	"id_book": 5,
	"title": "Perdido Sem Você",
	"author": 1,
	"publishing": 1,
	"ageRange": 14,
	"pages": 300,
	"release": "2024-11-02",
	"genre": "romance"
}
```
**-> Aqui a editora, apesar de não informada no cadastro é salva através da informação do autor, pois o autor é vinculado a uma editora.**

  #### Validações:
  - se o livro já estiver cadastrado: HTTP 400 -> The book has already been registered

![image](https://github.com/user-attachments/assets/4e226524-1b01-4535-bf20-1f658a7ed56f)

  - se o autor informado não existir no cadastro: HTTP 404 -> Author not found

![image](https://github.com/user-attachments/assets/8b9ea18a-e20b-451e-b85e-e077a503a75d)

  
- Buscas específicas
  - GET/releases-upcoming - próximos lançamentos - HTTP 200
  
  ![image](https://github.com/user-attachments/assets/d94ec419-6e61-406f-a909-0c7e79dc059b)


  - GET/books/findCompany?company= - encontrar livros da editora informada como param - HTTP 200
  - GET/books/findGenre?genre= - encontrar livros do gênero informado como param - HTTP 200
  - GET/books/findAuthor?author= encontrar livros do autor informado como param - HTTP 200
  - GET/books/findTitle?tutle= encontrar livros pelo título informado no param - HTTP 200

**-> Se não houver lista de autores, editoras ou gênero conforme informado, ele retorna um array vazio.**

## Licença:
Desenvolvido por Yasmim Silva (@yasmimfos)

✉️ contato: yasmimoliveira.ad@gmail.com
