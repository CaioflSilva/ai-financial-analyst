# AI Financial Analyst 💰🤖

> Plataforma de análise financeira pessoal com Inteligência Artificial

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.14-brightgreen)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-blue)
![Docker](https://img.shields.io/badge/Docker-Compose-2496ED)
![Status](https://img.shields.io/badge/Status-Em%20Desenvolvimento-yellow)

---

## 📌 Sobre o Projeto

O **AI Financial Analyst** é uma API REST que permite ao usuário controlar suas finanças pessoais e receber análises e recomendações geradas por Inteligência Artificial.

---

## 🚀 Tecnologias

- Java 21
- Spring Boot 3.5.14
- Spring Security + JWT
- Spring AI
- PostgreSQL 16
- Flyway
- Docker + Docker Compose
- Lombok
- Maven

---

## 🏗️ Arquitetura

O projeto segue os princípios da **Clean Architecture**:

com.aifinancialanalyst
├── domain          # Entidades e contratos de negócio
├── application     # Casos de uso
├── infrastructure  # JPA, segurança, integrações
├── presentation    # Controllers e DTOs
├── configuration   # Configurações do Spring
└── shared          # Utilitários transversais

---

## ✅ Funcionalidades

- [x] Cadastro de usuários
- [x] Autenticação JWT
- [ ] Cadastro de transações (receitas e despesas)
- [ ] Categorias financeiras
- [ ] Dashboard financeiro
- [ ] Relatórios
- [ ] Análise de gastos com IA
- [ ] Recomendações financeiras
- [ ] Assistente conversacional financeiro

---

## ⚙️ Como Executar

### Pré-requisitos
- Java 21
- Docker Desktop

### Passos

```bash
# Clone o repositório
git clone https://github.com/CaioflSilva/ai-financial-analyst.git

# Entre na pasta
cd ai-financial-analyst

# Suba o banco de dados
docker compose up -d

# Execute o projeto
.\mvnw spring-boot:run
```

---

## 📡 Endpoints Disponíveis

### Autenticação
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/api/v1/auth/register` | Cadastro de usuário |
| POST | `/api/v1/auth/login` | Login |

---

## 👨‍💻 Autor

**Caio Filipe**

[![LinkedIn](https://img.shields.io/badge/LinkedIn-Caio%20Filipe-blue)](https://www.linkedin.com/in/caiofldsilva)
[![GitHub](https://img.shields.io/badge/GitHub-CaioflSilva-black)](https://github.com/CaioflSilva)

---

## 📄 Licença

Este projeto está sob a licença MIT.