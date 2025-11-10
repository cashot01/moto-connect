# Moto Connect

Sistema de gerenciamento de motocicletas desenvolvido com Spring Boot para controle de frota, manutenção e rastreamento via RFID.

## Sobre o Projeto

O Moto Connect é uma aplicação web full-stack desenvolvida para gerenciar motocicletas, seus históricos de manutenção e localização através de tags RFID. O sistema oferece controle de acesso baseado em perfis (ADMIN e USER), permitindo operações CRUD completas sobre as entidades principais: RFID, Moto e Histórico.

### Características Principais

- Gerenciamento completo de frota de motocicletas
- Controle de manutenção e histórico de serviços
- Rastreamento de localização através de tags RFID
- Dashboard com estatísticas em tempo real
- Sistema de autenticação e autorização baseado em perfis
- Interface web responsiva com Thymeleaf
- Internacionalização (i18n) em português e inglês
- Migração automática de banco de dados com Flyway

## Equipe

- Cauan Passos - RM555466 - 2TDSPG
- Mateus Henrique de Souza - RM558424 - 2TDSPG
- Lucas Fialho - RM557884 - 2TDSPV

## Tecnologias Utilizadas

### Backend
- **Java 17** - Linguagem de programação
- **Spring Boot 3.5.4** - Framework principal
- **Spring MVC** - Arquitetura web
- **Spring Security** - Autenticação e autorização
- **Spring Data JPA** - Persistência de dados
- **Spring Validation** - Validação de dados
- **Flyway** - Migração de banco de dados

### Frontend
- **Thymeleaf** - Engine de templates
- **Bootstrap 5.3.0** - Framework CSS
- **Font Awesome 6.0.0** - Ícones

### Banco de Dados
- **PostgreSQL** - Sistema de gerenciamento de banco de dados relacional

### Ferramentas
- **Gradle** - Gerenciador de dependências e build
- **Lombok** - Redução de boilerplate
- **Docker Compose** - Orquestração de containers
- **Docker** - Containerização

## Requisitos do Sistema

- **JDK 17** ou superior
- **Gradle 8.10** (wrapper incluído no projeto)
- **Docker Desktop** (para execução com Docker Compose)
- **PostgreSQL** (se executar sem Docker)
- **IDE** (IntelliJ IDEA, Eclipse, VS Code ou similar)

## Estrutura do Projeto

```
moto-connect/
├── src/
│   ├── main/
│   │   ├── java/br/com/fiap/motoconnect/
│   │   │   ├── config/          # Configurações (Security, i18n)
│   │   │   ├── controller/      # Controladores MVC
│   │   │   ├── model/           # Entidades JPA
│   │   │   ├── repository/      # Repositórios Spring Data
│   │   │   ├── service/         # Lógica de negócio
│   │   │   └── MotoConnectApplication.java
│   │   └── resources/
│   │       ├── db/migration/   # Scripts Flyway
│   │       ├── static/css/      # Arquivos estáticos
│   │       ├── templates/       # Templates Thymeleaf
│   │       └── application.properties
│   └── test/                    # Testes unitários
├── build.gradle                 # Configuração Gradle
├── compose.yaml                 # Docker Compose
├── Dockerfile                   # Imagem Docker
└── README.md
```

## Modelo de Dados

### Entidades Principais

**Usuario**
- Gerencia usuários do sistema com perfis ADMIN e USER
- Campos: id, nome, email, senha, role

**Rfid**
- Representa tags RFID para rastreamento de localização
- Campos: id, nomeArea, latitude, longitude

**Moto**
- Representa motocicletas da frota
- Campos: id, modelo, placa, dataCadastro, status, rfid (FK), usuario (FK)
- Status: NAO_INICIADO, MANUTENCAO, REVISADA

**HistoricoMoto**
- Registra histórico de manutenção e serviços
- Campos: id, parte, descricao, moto (FK), usuario (FK)

### Relacionamentos

- Uma Moto pertence a um Usuario
- Uma Moto pode ter um Rfid associado
- Um HistoricoMoto pertence a uma Moto e a um Usuario

## Configuração e Instalação

### Opção 1: Execução com Docker Compose (Recomendado)

1. Clone o repositório:
```bash
git clone https://github.com/cashot01/moto-connect.git
cd moto-connect
```

2. Certifique-se de que o Docker Desktop está em execução

3. Configure as variáveis de ambiente:
```bash
export SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/motoconnect
export SPRING_DATASOURCE_USERNAME=motoconnect
export SPRING_DATASOURCE_PASSWORD=motoconnect
```

4. Execute o Docker Compose para iniciar o PostgreSQL:
```bash
docker compose up -d
```

5. Execute a aplicação:
```bash
./gradlew bootRun
```

6. Acesse a aplicação em: `http://localhost:8080/login`

### Opção 2: Execução Local (Sem Docker)

1. Clone o repositório:
```bash
git clone https://github.com/cashot01/moto-connect.git
cd moto-connect
```

2. Configure um banco PostgreSQL local:
   - Crie um banco de dados chamado `motoconnect`
   - Configure usuário e senha conforme necessário

3. Configure as variáveis de ambiente no `application.properties` ou como variáveis de sistema:
```bash
export SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/motoconnect
export SPRING_DATASOURCE_USERNAME=seu_usuario
export SPRING_DATASOURCE_PASSWORD=sua_senha
```

4. Execute a aplicação:
```bash
./gradlew bootRun
```

5. Acesse a aplicação em: `http://localhost:8080/login`

### Variáveis de Ambiente

| Variável | Descrição | Valor Padrão (Docker) |
|----------|-----------|----------------------|
| `SPRING_DATASOURCE_URL` | URL de conexão do banco de dados | `jdbc:postgresql://localhost:5432/motoconnect` |
| `SPRING_DATASOURCE_USERNAME` | Usuário do banco de dados | `motoconnect` |
| `SPRING_DATASOURCE_PASSWORD` | Senha do banco de dados | `motoconnect` |

## Migração do Banco de Dados

O projeto utiliza Flyway para gerenciar migrações do banco de dados. As migrações são executadas automaticamente na inicialização da aplicação e estão localizadas em `src/main/resources/db/migration/`:

- `V1__create_usuario.sql` - Criação da tabela de usuários
- `V2__create_rfid.sql` - Criação da tabela de RFIDs
- `V3__create_moto.sql` - Criação da tabela de motos
- `V4__create_historico.sql` - Criação da tabela de histórico
- `V5__inserts.sql` - Dados iniciais
- `V6__procedures.sql` - Procedures e funções

## Funcionalidades

### Autenticação e Autorização

- Login e logout de usuários
- Controle de acesso baseado em perfis (ADMIN e USER)
- Proteção de rotas através do Spring Security

### Dashboard

- Visualização de estatísticas em tempo real:
  - Total de motos cadastradas
  - Motos não iniciadas
  - Motos em manutenção
  - Motos revisadas
  - Total de RFIDs cadastrados
- Acesso rápido às principais funcionalidades

### Gerenciamento de RFIDs

- Criar novo RFID com nome da área e coordenadas (latitude/longitude)
- Listar todos os RFIDs cadastrados
- Editar informações de RFIDs existentes
- Excluir RFIDs (com validação de relacionamentos)

### Gerenciamento de Motos

- Criar nova moto com:
  - Modelo (enum)
  - Placa (validação de formato)
  - Data de cadastro
  - Status (NAO_INICIADO, MANUTENCAO, REVISADA)
  - Associação com RFID
  - Associação com usuário
- Listar todas as motos com filtros e busca
- Editar informações de motos
- Excluir motos (com validação de relacionamentos)

### Gerenciamento de Histórico

- Criar novo registro de histórico com:
  - Parte da moto
  - Descrição do serviço/manutenção
  - Associação com moto
  - Associação com usuário responsável
- Listar histórico completo
- Editar registros de histórico
- Excluir registros de histórico

### Perfil do Usuário

- Visualização de informações do usuário logado
- Edição de dados pessoais

## Credenciais de Acesso

 | Perfil | E-mail                                                              | Senha     |
| ------ | ------------------------------------------------------------------- | --------- |
| ADMIN  | cauan.passos@motoconnect.com | admin1234 |
| ADMIN  | mateus.souza@motoconnect.com | senha123 |
| ADMIN  | lucas.fialho@motoconnect.com | password987 |
| USER | arthur.bento@motoconnect.com | bento2025 |
| USER | maria.clara@motoconnect.com | clara1235 |

## Fluxo de Dados Recomendado

Para manter a integridade referencial e os relacionamentos corretos, siga esta ordem ao inserir dados:

1. **Criar RFID**
   - Acesse a funcionalidade de RFID e crie um novo registro com nome da área e coordenadas

2. **Criar Moto**
   - Ao cadastrar uma moto, associe-a ao RFID criado no passo anterior
   - Defina o modelo, placa, status e usuário responsável

3. **Criar Histórico**
   - Ao gerar um histórico de manutenção, associe-o à moto criada
   - Informe a parte da moto e a descrição do serviço realizado

Este fluxo garante que cada histórico tenha uma moto associada e cada moto tenha um RFID previamente cadastrado.

## Rotas Principais

| Rota | Descrição | Autenticação |
|------|-----------|--------------|
| `/login` | Página de login | Pública |
| `/dashboard` | Dashboard principal | Autenticada |
| `/motos` | Lista de motos | Autenticada |
| `/motos/nova` | Formulário de nova moto | Autenticada |
| `/rfid` | Lista de RFIDs | Autenticada |
| `/rfid/novo` | Formulário de novo RFID | Autenticada |
| `/historico` | Lista de histórico | Autenticada |
| `/historico/novo` | Formulário de novo histórico | Autenticada |
| `/perfil` | Perfil do usuário | Autenticada |
| `/usuarios` | Lista de usuários (ADMIN) | ADMIN |

## Build e Deploy

### Build do Projeto

```bash
./gradlew build
```

### Gerar JAR Executável

```bash
./gradlew bootJar
```

O arquivo JAR será gerado em `build/libs/moto-connect-0.0.1-SNAPSHOT.jar`

### Executar JAR

```bash
java -jar build/libs/moto-connect-0.0.1-SNAPSHOT.jar
```

### Build com Docker

```bash
docker build -t moto-connect:latest .
```

### Executar Container Docker

```bash
docker run -p 8080:8080 \
  -e SPRING_DATASOURCE_URL=jdbc:postgresql://host.docker.internal:5432/motoconnect \
  -e SPRING_DATASOURCE_USERNAME=motoconnect \
  -e SPRING_DATASOURCE_PASSWORD=motoconnect \
  moto-connect:latest
```

## Internacionalização (i18n)

O projeto suporta múltiplos idiomas através de arquivos de propriedades:
- `messages_pt_BR.properties` - Português (Brasil)
- `messages_en_US.properties` - Inglês (Estados Unidos)

A configuração de i18n está em `I18nConfiguration.java` e permite alternar entre idiomas na interface.

## Testes

Execute os testes unitários com:

```bash
./gradlew test
```

## Segurança

- Autenticação baseada em sessão HTTP
- Senhas armazenadas em texto plano (não recomendado para produção)
- Proteção CSRF habilitada
- Controle de acesso baseado em roles
- Validação de entrada em todos os formulários

**Nota de Segurança**: Para ambientes de produção, implemente:
- Hash de senhas (BCrypt)
- HTTPS obrigatório
- Validação adicional de entrada
- Rate limiting
- Logs de auditoria

## Desenvolvimento

### Executar em Modo Desenvolvimento

O projeto inclui Spring Boot DevTools para recarregamento automático:

```bash
./gradlew bootRun
```

Alterações em classes Java serão detectadas e a aplicação será reiniciada automaticamente.

### Estrutura de Código

- **Controllers**: Gerenciam requisições HTTP e retornam views
- **Services**: Contêm a lógica de negócio
- **Repositories**: Interface com o banco de dados através do Spring Data JPA
- **Models**: Entidades JPA com validações
- **Config**: Configurações do Spring (Security, i18n)

## Troubleshooting

### Erro de Conexão com Banco de Dados

- Verifique se o PostgreSQL está em execução
- Confirme as variáveis de ambiente
- Verifique se a porta 5432 está disponível

### Erro de Migração Flyway

- Verifique se o banco de dados existe
- Confirme as credenciais de acesso
- Limpe o schema se necessário: `DROP SCHEMA public CASCADE; CREATE SCHEMA public;`

### Porta 8080 em Uso

Altere a porta no `application.properties`:
```properties
server.port=8081
```

## Licença

Este projeto está licenciado sob a MIT License. Veja o arquivo `LICENSE` para mais detalhes.

## Contribuição

Este é um projeto acadêmico desenvolvido para o Challenge Mottu / Sprint 3 da FIAP.

## Contato

Para dúvidas ou sugestões, entre em contato com a equipe de desenvolvimento através dos e-mails cadastrados no sistema.
