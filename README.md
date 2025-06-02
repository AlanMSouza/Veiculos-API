# 🚗 Veículos API
Rest API de gerenciamento de veículos com Spring Security. 

Este projeto é uma Rest API desenvolvida com Spring Boot, que permite o cadastro, listagem, atualização e exclusão de veículos e usuários. A aplicação conta com autenticação e autorização por meio de JWT (JSON Web Token), garantindo segurança no acesso aos endpoints.

## 🔧 Tecnologias utilizadas

- Java 17  
- Spring Boot  
- Spring Security  
- JWT (JSON Web Token)  
- PostgreSQL  

---

## 📌 Funcionalidades

- Cadastro de usuários
- Login com geração de token JWT  
- Cadastro de veículos vinculados a um usuário  
- Listagem de veículos e usuários
- Detalhamento de veículo e usuário por ID  
- Exclusão de veículos e usuários

---

## 🔁 Endpoints da API

## 🔐 Autenticação

A autenticação é feita via JWT. Após login, o token deve ser enviado no cabeçalho das requisições protegidas:
```
Authorization: Bearer <seu_token_aqui>
```

#### 👉 POST `/usuarios/login`

Autentica o usuário e retorna o token JWT.

**Body (JSON):**

```json
{
  "login": "usuario@email.com",
  "senha": "123456"
}
```

**Resposta:**

```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

---

### 👤 Usuários

#### 👉 POST `/usuarios`

Cria um novo usuário.

**Body (JSON):**

```json
{
    "nome": "Usuario Da silva",
    "email": "usuario@email.com",
    "senha": "123456",
    "cpf": "111.111.111-11",
    "role": "ADMIN"
}
```

---

#### 👉 GET `/usuarios`

Lista todos os usuários.

---

#### 👉 GET `/usuarios/{id}`

Detalha um usuário pelo ID.

---

#### 👉 DELETE `/usuarios/{id}`

Exclui um usuário pelo ID.

---

### 🚘 Veículos

#### 👉 POST `/veiculos`

Cadastra um novo veículo.

**Body (JSON):**

```json
{
  "modelo": "Civic",
  "marca": "Honda",
  "placa": "ABC1234",
  "ano": "2020",
  "cor": "Preto",
  "usuarioId": 1
}
```

---

#### 👉 GET `/veiculos`

Lista todos os veículos.

---

#### 👉 GET `/veiculos/{id}`

Detalha um veículo pelo ID.

---

#### 👉 DELETE `/veiculos/{id}`

Exclui um veículo pelo ID.

---

# 🗃️ Banco de dados
A aplicação está conectada a um banco de dados PostgreSQL. Certifique-se de configurar o `application.properties` ou `application.yml` corretamente com as credenciais do seu banco:

```properties
spring.application.name=veiculos
spring.datasource.url=jdbc:mysql://localhost/veiculos_api
spring.datasource.username=admin
spring.datasource.password=admin
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
server.error.include-stacktrace=never
api.security.token.secret = ${JWT_SECRET: 1234}
```

---
