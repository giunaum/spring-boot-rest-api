# CRUD Básico Spring Boot

Crud básico feito em Spring Boot.

## Rodar o projeto

Na raiz do projeto execute o comando `mvn install` pelo terminal ou prompt na raiz do projeto.
Para rodar o projeto use o comando `mvn spring-boot:run` no ambiente de desenvolvimento. Use a URL base `http://localhost:8080/` mais os serviços.

# Serviços do Spring Boot

## Listar -> [url]/pessoas
Método GET que recupera toda a lista de pessoas cadastradas no sitema.

## Recuperar por ID -> [url]/pessoas/{id}
Método GET que recupera a pessoa fornecendo o ID como parâmetro por URI.

## Salvar -> [url]/pessoasS
Método POST que salva a pessoa no sistema fornecendo nome e idade como parâmetros por URI.

## Excluir -> [url]/pessoas/{id}
Método DELETE que exclui uma pessoa conforme o ID fornecido como parâmetro por URI.

## Banco de dados

Para este projeto, foi utilizado o `Postgres` para o desenvolvimento e `H2 Database` para os testes automatizados.

## Tecnologias

Esta aplicação foi construida usando as seguintes tecnologias:

> Java 8

> Spring Boot 2