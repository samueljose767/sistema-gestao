# 💈 Sistema de Gestão - Texas Barbearia

Um sistema de gestão back-end desenvolvido em Java para a barbearia Texas Barbearia de Santa Maria DF. Este projeto foi criado como parte do meu portfólio na área de Análise e Desenvolvimento de Sistemas (ADS), com foco na aplicação de conceitos de Programação Orientada a Objetos (POO), arquitetura em camadas e integração com bases de dados relacionais.

## 🚀 Tecnologias Utilizadas
- **Java (JDK 17+)**
- **Maven** (Gestão de dependências)
- **MySQL** (Base de Dados Relacional)
- **JDBC** (Java Database Connectivity)

## 🏗️ Arquitetura do Projeto
O projeto foi estruturado utilizando o padrão de camadas para garantir uma melhor organização e manutenção do código:
- `config/`: Responsável pela fábrica de ligações (`ConexaoFactory`) que estabelece a comunicação com o MySQL.
- `model/`: Classes de domínio que representam as entidades do negócio (ex: `Cliente`, `Servico`).
- `dao/`: Classes *Data Access Object* responsáveis pelas operações na base de dados (INSERT, SELECT, etc.), isolando a lógica de persistência.

## ⚙️ Como executar o projeto
1. Clone este repositório para a sua máquina local.
2. Abra o MySQL Workbench e execute os scripts de criação da base de dados e das tabelas (ou importe o ficheiro `schema.sql`).
3. No ficheiro `src/main/java/br/com/texasbarbearia/config/ConexaoFactory.java`, altere a variável `SENHA` para a palavra-passe do seu servidor MySQL local.
4. Execute a classe `App.java` na raiz do projeto para iniciar o sistema e testar o registo de clientes.

---
*Desenvolvido por Samuel José dos Santos - Estudante de ADS na UNICEPLAC e Desenvolvedor Back-end.*