# Moto Connect

Projeto **Moto Connect**  
Aplicação Spring Boot + MVC + Gradle — Challenge Mottu / Sprint 3

---

## Índice

1. Sobre o projeto  
2. Tecnologias usadas  
3. Requisitos para rodar  
4. Configuração  
5. Funcionalidades  
6. Login  
7. Fluxo dos dados    
8. Licença  

---

## 1. Sobre o projeto

Este projeto é uma aplicação web baseada em Spring Boot + MVC para gerenciar motos, históricos e RFIDs, servindo como backend/frontend simples para um desafio de Sprint.  
Ele permite operações de CRUD para entidades como RFID, Moto e Histórico, com controle de acesso via perfil ADMIN e USER.

---

## 2. Tecnologias usadas

- Java  
- Spring Boot  
- Spring MVC  
- Spring Security 
- Gradle  
- Thymeleaf  
- Banco de dados (PostgreSQL)
- Docker compose
- Git  

---

## 3. Requisitos para rodar

- JDK 11+ (ou versão que o projeto utiliza)  
- Gradle (wrapper já incluído)  
- Banco de dados configurado
- IDE (IntelliJ, Eclipse, VSCode)
- Docker Desktop

---

## 4. Configuração

1. Clone este repositório:  
   ```bash
   git clone https://github.com/cashot01/moto-connect.git

2. Abrir o Docker Desktop
3. Executar o projeto
4. Acesse a url: localhost:8080/login
5. Faça o login
6. Cadastre um novo Rfid
7. Cadastre uma nova Moto
8. Cadastre um novo Historico

## 5. Funcionalidades 
  

Login / logout

Dashboard ou página inicial

CRUD de RFID

CRUD de Moto

CRUD de Histórico

Visualizações / relacionamentos entre entidades


## 6. Login
  | Perfil | E-mail                                                              | Senha     |
| ------ | ------------------------------------------------------------------- | --------- |
| ADMIN  | cauan.passos@motoconnect.com | admin1234 |
| USER   | mateus.souza@motoconnect.com | senha123 |


## 7. Fluxo dos dados 

Para que o sistema mantenha integridade e relacionamentos corretos, siga esta ordem ao inserir dados:

Criar novo RFID

Vá até a funcionalidade de RFID e crie um novo registro 

Criar nova Moto

Ao cadastrar uma Moto, associe-a ao RFID criado no passo anterior

Criar novo Histórico

Ao gerar um Histórico, associe-o à Moto criada

Esse fluxo garante que cada novo Histórico tenha uma Moto associada, e cada Moto tenha um RFID previamente criado.


## 8. Licença

Este projeto está licenciado sob a MIT License — veja o arquivo LICENSE para mais detalhes.

