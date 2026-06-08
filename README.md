# AI Financial Analyst 💰🤖

> Plataforma de análise financeira pessoal com Inteligência Artificial

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.14-brightgreen)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-blue)
![Docker](https://img.shields.io/badge/Docker-Compose-2496ED)
![Spring AI](https://img.shields.io/badge/Spring%20AI-1.0.0-green)
![CI](https://github.com/CaioflSilva/ai-financial-analyst/actions/workflows/ci.yml/badge.svg)
![Status](https://img.shields.io/badge/Status-Em%20Desenvolvimento-yellow)

---

## 📌 Sobre o Projeto

O **AI Financial Analyst** é uma API REST que permite ao usuário controlar suas finanças pessoais e receber análises e recomendações geradas por Inteligência Artificial usando Spring AI e LLaMA 3.3.

---

## 🚀 Tecnologias

- Java 21
- Spring Boot 3.5.14
- Spring Security + JWT
- Spring AI 1.0.0
- Groq (LLaMA 3.3 70B)
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
├── infrastructure  # JPA, segurança, IA, integrações
├── presentation    # Controllers e DTOs
├── configuration   # Configurações do Spring
└── shared          # Utilitários transversais

---

## 🔐 Segurança

- Autenticação JWT com blacklist de tokens
- BCrypt para criptografia de senhas
- CORS configurado
- Rate Limiting nos endpoints de autenticação
- Logs seguros sem dados sensíveis
- Auditoria de acesso — todas as ações são registradas

---

## ✅ Funcionalidades

- [x] Cadastro de usuários
- [x] Autenticação JWT
- [x] Logout com invalidação de token
- [x] Categorias financeiras — CRUD completo
- [x] Transações financeiras — CRUD completo
- [x] Dashboard financeiro com saldo e totais
- [x] Análise de gastos com IA (Spring AI + Groq LLaMA 3.3)
- [x] Assistente conversacional financeiro com IA
- [x] Testes unitários (JUnit 5 + Mockito) — 11 testes
- [x] CI/CD com GitHub Actions
- [x] Auditoria de acesso

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
| Método | Endpoint | Descrição | Auth |
|--------|----------|-----------|------|
| POST | `/api/v1/auth/register` | Cadastro de usuário | ❌ |
| POST | `/api/v1/auth/login` | Login | ❌ |
| POST | `/api/v1/auth/logout` | Logout | ✅ |

### Categorias
| Método | Endpoint | Descrição | Auth |
|--------|----------|-----------|------|
| POST | `/api/v1/categories` | Criar categoria | ✅ |
| GET | `/api/v1/categories` | Listar categorias | ✅ |
| PUT | `/api/v1/categories/{id}` | Atualizar categoria | ✅ |
| DELETE | `/api/v1/categories/{id}` | Deletar categoria | ✅ |

### Transações
| Método | Endpoint | Descrição | Auth |
|--------|----------|-----------|------|
| POST | `/api/v1/transactions` | Criar transação | ✅ |
| GET | `/api/v1/transactions` | Listar transações | ✅ |
| PUT | `/api/v1/transactions/{id}` | Atualizar transação | ✅ |
| DELETE | `/api/v1/transactions/{id}` | Deletar transação | ✅ |

### Dashboard
| Método | Endpoint | Descrição | Auth |
|--------|----------|-----------|------|
| GET | `/api/v1/dashboard` | Resumo financeiro | ✅ |

### Inteligência Artificial
| Método | Endpoint | Descrição | Auth |
|--------|----------|-----------|------|
| GET | `/api/v1/ai/analyze` | Análise financeira com IA | ✅ |
| POST | `/api/v1/ai/chat` | Assistente conversacional | ✅ |

---

## 👨‍💻 Autor

**Caio Filipe**

[![LinkedIn](https://img.shields.io/badge/LinkedIn-Caio%20Filipe-blue)](https://www.linkedin.com/in/caiofldsilva)
[![GitHub](https://img.shields.io/badge/GitHub-CaioflSilva-black)](https://github.com/CaioflSilva)

---

## 📄 Licença

Este projeto está sob a licença MIT.