
# Desafio Itaú - API de Transações e Estatísticas

Esse projeto implementa uma API para registrar transações e calcular estatísticas com base nas transações recentes. Para atender os requisitos do teste, o armazenamento é feito em ArrayList.

### Funcionalidades
* **Receber transações:** Permite recepção de transações financeiras no formato JSON.
* **Gerar estatísticas:** Após o processamento, retorna dados estatísticos sobre as transações realizada em determinado período.

### Tecnologias Utilizadas

* Java 21
* Spring Boot
* Spring Web
* Spring Actuator
* Springdoc OpenAPI
* Lombok





## Requisitos

Para rodar esse projeto, você vai precisar das seguintes tecnologias:


`Java: JDK 21+`

`Maven: v3.8.1+`

`Git`

`Docker (opcional)`

## Instalação e configuração (Docker)

**1 - Clone o repositório**

```bash
  git clone https://github.com/Mariano-Rafael/desafio-itau-apirest.git
  cd desafio-itau-apirest
```
**2 - Construa a imagem Docker (necessário ter o Docker instalado)**

```bash
  docker build -t desafio-itau-apirest .
```
**3 - Suba o container**

```bash
  docker run -p 8080:8080 desafio-itau-apirest
```

**4. Executando localmente**

**4.1 - Após clonar o repositório, compile o projeto**

```bash
  mvn clean install
```
**4.2 - Execute o projeto**

```bash
  mvn spring-boot:run
```

## Documentação da API

#### Registra uma nova transação

```http
  POST /transacao
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `valor` | `Double` | **Obrigatório**. Valor da transação. |
| `timestamp` | `OffsetDateTime` | **Obrigatório**. Data/hora que a transação ocorreu. |

#### Deleta todas as transações

```http
  DELETE /transacao
```

#### Retorna estatísticas das transações dos últimos {interval} segundos.

```http
  GET /estatistica
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `interval` | `Integer` | **Não obrigatório**. Default: 60s. |


