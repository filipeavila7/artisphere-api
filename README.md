# 🎨 Artisphere API

Backend da **Artisphere**, uma rede social voltada para artistas compartilharem suas criações, descobrirem novos trabalhos e se conectarem com outros artistas.

Inspirada no Pinterest, a plataforma combina publicações visuais, um feed personalizado com base nas preferências dos usuários, stories e mensagens em tempo real.

**Status:** Em desenvolvimento, com funcionalidades implementadas e melhorias contínuas.

## 📌 Repositórios

- **Backend (este repositório):** [Artisphere API](https://github.com/filipeavila7/artisphere-api)
- **Frontend:** [Artisphere Frontend](https://github.com/filipeavila7/artisphere-front)

## ✨ Funcionalidades

### Publicações e interações
- Criação de publicações com imagens.
- Curtidas, comentários e respostas a comentários.
- Curtidas em comentários.
- Salvamento e compartilhamento de publicações.
- Upload de arquivos e imagens.

### Feed personalizado
- Feed adaptado às preferências e interações dos usuários.
- Sistema de pontuação baseado em interações com publicações e suas tags.
- Maior relevância para conteúdos associados aos interesses demonstrados pelo usuário.

### Usuários e privacidade
- Cadastro, autenticação e gerenciamento de perfis.
- Autenticação e autorização utilizando JWT.
- Senhas protegidas com BCrypt.
- Sistema de seguidores.
- Solicitações para seguir contas privadas.
- Controle de acesso a conteúdos privados.
- Busca de usuários.

### Stories
- Publicação de stories com expiração automática.
- Stories exclusivos para a lista de melhores amigos.
- Controle de visualização conforme as permissões do usuário.

### Mensagens e notificações
- Mensagens em tempo real utilizando WebSocket e STOMP.
- Sistema de notificações.
- Paginação de resultados.

## 🛠️ Tecnologias utilizadas

### Backend
- Java 17
- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate
- JWT
- BCrypt
- Spring WebSocket
- STOMP
- Bean Validation
- Lombok
- Maven

### Banco de dados e armazenamento
- MySQL 8
- Armazenamento local de arquivos e imagens
- Arquivo `.env` para configuração de variáveis de ambiente

## 🏗️ Arquitetura

O Artisphere utiliza uma arquitetura monolítica organizada por recursos, combinando a separação por domínio com uma arquitetura em camadas.

Cada recurso possui seu próprio pacote, reunindo componentes relacionados à sua funcionalidade, como `Controller`, `Entity`, `Service`, `Mapper` e `DTO`.

Essa organização mantém próximas as responsabilidades relacionadas a cada domínio, facilita a manutenção e permite localizar e evoluir funcionalidades sem concentrar todo o código em estruturas genéricas compartilhadas.

### Organização dos componentes

| Componente | Responsabilidade |
|---|---|
| `Controller` | Receber requisições HTTP e expor os endpoints da API. |
| `Service` | Concentrar as regras de negócio. |
| `Entity` | Representar as entidades persistidas no banco de dados. |
| `DTO` | Definir os objetos utilizados na transferência de dados. |
| `Mapper` | Converter entidades, DTOs e outras representações. |

### Serviços compartilhados

O projeto também possui um `GlobalHelperService`, responsável por disponibilizar métodos utilitários reutilizados em diferentes partes da aplicação.

Essa abordagem ajuda a centralizar operações comuns e reduzir a duplicação de código.

## 🔐 Segurança

A API utiliza mecanismos de autenticação, autorização e validação para proteger os recursos da aplicação.

- **Spring Security:** gerenciamento da segurança e do acesso aos recursos.
- **JWT:** autenticação por tokens.
- **BCrypt:** proteção das senhas armazenadas.
- **Controle de acesso:** restrições relacionadas a contas privadas e operações sobre conteúdos dos usuários.
- **Bean Validation:** validação dos dados de entrada.

## ⚙️ Requisitos

Para executar o projeto localmente, são necessários:

- Java 17.
- MySQL 8.
- Git.
- Maven Wrapper incluído no projeto.
- Um arquivo `.env` configurado de acordo com as propriedades utilizadas pela aplicação.

## 🚀 Como executar localmente

### 1. Clone o repositório

```bash
git clone https://github.com/filipeavila7/artisphere-api.git
cd artisphere-api
```

### 2. Configure o banco de dados

Crie o banco de dados MySQL utilizado pela aplicação:

```sql
CREATE DATABASE artisphere;
```

**Observação:** a aplicação utiliza Hibernate para criar ou atualizar o schema conforme sua configuração. Confira as propriedades de persistência do projeto para verificar o comportamento exato.

### 3. Configure as variáveis de ambiente

Crie um arquivo `.env` na raiz do projeto.

Preencha as variáveis exigidas pela aplicação de acordo com as configurações do ambiente local.

```dotenv
# TODO: preencher com os nomes reais das variáveis utilizadas pelo projeto.
# Não adicione senhas, tokens ou chaves secretas ao repositório.
```

**Pendente:** documentar os nomes exatos das variáveis, incluindo as configurações de conexão com o banco, JWT e demais serviços que realmente dependam do ambiente.

### 4. Execute a aplicação

No Windows, utilizando o terminal na raiz do projeto:

```powershell
.\mvnw.cmd spring-boot:run
```

Em ambientes Linux ou macOS:

```bash
./mvnw spring-boot:run
```

**Pendente de confirmação:** verificar se esses comandos funcionam com a configuração atual do projeto e se existem requisitos adicionais para inicialização.

### 5. Acesse a API

Endereço local previsto:

```text
http://localhost:8080
```

A API não possui Swagger/OpenAPI configurado no momento.

**Pendente:** documentar as rotas disponíveis e seus respectivos métodos HTTP, parâmetros, requisitos de autenticação e formatos de resposta.

## 📡 Documentação dos endpoints

Os endpoints serão documentados com base nos controllers reais do projeto.

A documentação será organizada por recurso, facilitando a consulta das funcionalidades disponíveis.

| Recurso | Documentação |
|---|---|
| Autenticação | A documentar |
| Usuários e perfis | A documentar |
| Publicações e feed | A documentar |
| Comentários e interações | A documentar |
| Seguidores e solicitações | A documentar |
| Stories | A documentar |
| Mensagens | A documentar |
| Notificações | A documentar |
| Upload de arquivos | A documentar |

> **Nota:** esta tabela é um roteiro de documentação, não uma lista confirmada de caminhos de endpoints. Os métodos HTTP e as rotas serão adicionados após a análise dos controllers.

## 🖼️ Demonstração

<!-- TODO: adicionar capturas de tela do frontend e, se disponível, o link para a versão publicada. -->

## 📚 Aprendizados e objetivos do projeto

O desenvolvimento do Artisphere envolve a aplicação prática de conceitos de desenvolvimento backend com Java e Spring Boot, incluindo:

- Construção de APIs REST.
- Organização de código por recursos e camadas.
- Persistência de dados com JPA e Hibernate.
- Autenticação e autorização com Spring Security e JWT.
- Relacionamentos entre entidades e regras de acesso.
- Comunicação em tempo real com WebSocket e STOMP.
- Upload de arquivos e gerenciamento de conteúdos.
- Desenvolvimento de um feed personalizado com base nas interações dos usuários.

## 📄 Licença

<!-- TODO: definir uma licença para o projeto antes de adicionar uma declaração de licença aqui. -->

---

Desenvolvido por **Filipe Pereira Viana Ávila**.

- [GitHub](https://github.com/filipeavila7)
- [Artisphere API](https://github.com/filipeavila7/artisphere-api)
- [Artisphere Frontend](https://github.com/filipeavila7/artisphere-front)
