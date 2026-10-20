CREATE DATABASE IF NOT EXISTS db_texasbarbearia;
USE db_texasbarbearia;

CREATE TABLE clientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    email VARCHAR(100)
);

CREATE TABLE servicos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    descricao VARCHAR(80) NOT NULL,
    valor DECIMAL(10, 2) NOT NULL
);