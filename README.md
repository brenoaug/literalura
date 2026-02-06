# Literalura

![Java](https://img.shields.io/badge/Java-21-orange?logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.10-green?logo=springboot&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-4.0.0-blue?logo=apachemaven&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-17.7-blue?logo=postgresql&logoColor=white)
![Flyway](https://img.shields.io/badge/Flyway-Enabled-CC0200?logo=flyway&logoColor=white)
![JPA](https://img.shields.io/badge/JPA-Hibernate-59666C?logo=hibernate&logoColor=white)

Sistema de catálogo de livros desenvolvido como projeto de estudos. A aplicação consome dados da API Gutendex para buscar informações sobre livros e autores, armazenando-os em um banco de dados PostgreSQL.

## Sobre o Projeto

Literalura é uma aplicação de linha de comando que permite aos usuários pesquisar livros, listar autores e consultar informações detalhadas sobre obras literárias. O projeto foi desenvolvido com foco em boas práticas de programação, utilizando Spring Boot, JPA/Hibernate e Flyway para gerenciamento de migrações.

## Funcionalidades

- **Buscar Livro por Nome**: Pesquisa livros na API Gutendex e exibe informações detalhadas
- **Listar Todos os Livros**: Exibe todos os livros cadastrados no banco de dados
- **Lista de Autores**: Mostra todos os autores disponíveis na API
- **Autores Vivos em Determinado Ano**: Filtra autores que estavam vivos em um período específico
- **Popular Tabelas**: Importa dados da API para o banco de dados local
- **Livros por Idioma**: Agrupa e exibe a quantidade de livros por idioma
- **Consulta de Autores Vivos (Banco de Dados)**: Busca autores vivos em período específico no banco local

## Tecnologias Utilizadas

- **Java 21**: Linguagem de programação principal
- **Spring Boot 3.5.10**: Framework para desenvolvimento da aplicação
- **Spring Data JPA**: Abstração para acesso a dados
- **Hibernate**: Implementação JPA para mapeamento objeto-relacional
- **PostgreSQL**: Sistema de gerenciamento de banco de dados
- **Flyway**: Ferramenta para versionamento e migração de banco de dados
- **Jackson**: Biblioteca para serialização/deserialização JSON
- **Maven**: Gerenciador de dependências e build

## Arquitetura do Projeto

```
literalura/
├── src/
│   ├── main/
│   │   ├── java/com/alura/literalura/
│   │   │   ├── dto/              # Data Transfer Objects
│   │   │   ├── menu/             # Classes de interface do usuário
│   │   │   ├── model/            # Entidades JPA
│   │   │   ├── repository/       # Repositórios Spring Data
│   │   │   └── services/         # Lógica de negócio
│   │   └── resources/
│   │       ├── application.properties
│   │       └── db/migration/     # Scripts Flyway
│   └── test/
└── pom.xml
```

## Pré-requisitos

- Java JDK 21 ou superior
- PostgreSQL 12 ou superior
- Maven 3.6 ou superior

## Configuração do Banco de Dados

1. Crie um banco de dados PostgreSQL:
```sql
CREATE DATABASE literalura_db;
```

2. Configure as credenciais no arquivo `application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/literalura_db
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```

## Instalação e Execução

1. Clone o repositório:
```bash
git clone https://github.com/seu-usuario/literalura.git
cd literalura
```

2. Execute o projeto com Maven:
```bash
mvn spring-boot:run
```

Ou compile e execute o JAR:
```bash
mvn clean package
java -jar target/literalura-0.0.1-SNAPSHOT.jar
```

## Estrutura do Banco de Dados

O projeto utiliza Flyway para gerenciar as migrações. As principais tabelas são:

- **livros**: Armazena informações sobre os livros (título, idioma, número de downloads)
- **autores**: Contém dados dos autores (nome, ano de nascimento, ano de falecimento)
- **livro_autor**: Tabela de relacionamento muitos-para-muitos entre livros e autores

## API Utilizada

O projeto consome a [Gutendex API](https://gutendex.com/), uma API gratuita que fornece acesso ao catálogo do Project Gutenberg, contendo milhares de livros de domínio público.

## Relacionamentos

O sistema implementa um relacionamento **muitos-para-muitos** entre Livros e Autores:
- Um livro pode ter vários autores
- Um autor pode ter escrito vários livros

## Aprendizados

Este projeto de estudos abordou:
- Consumo de APIs REST
- Persistência de dados com JPA/Hibernate
- Relacionamentos complexos em bancos de dados
- Migrações de banco de dados com Flyway
- Injeção de dependências com Spring
- Deserialização JSON com Jackson
- Boas práticas de programação orientada a objetos


## Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.
