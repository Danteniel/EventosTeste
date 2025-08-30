# Sistema de Gerenciamento de Eventos

**Este é um projeto simples de estudo e aprendizado de Java.** O objetivo principal é praticar conceitos fundamentais da linguagem Java. 

Este sistema simula o gerenciamento de eventos, permitindo que usuários se cadastrem, participem de eventos e visualizem informações sobre eventos passados, presentes e futuros.

## Funcionalidades

- **Cadastro de Usuário**: Permite que um usuário se registre no sistema com nome, e-mail, idade, telefone e senha.
- **Cadastro de Evento**: Criação de eventos com nome, endereço, categoria, data e descrição.
- **Participação em Eventos**: Usuários podem se inscrever em eventos e cancelar sua inscrição.
- **Exibição de Eventos**: O sistema permite listar eventos por categoria, eventos passados, presentes ou futuros.
- **Persistência de Dados**: Todos os dados são salvos em arquivos `.data` para garantir que as informações sejam persistidas entre as execuções do programa.

## Objetivo

Este projeto foi criado para aprender e aplicar conceitos de programação em Java. Não é um sistema completo de produção, mas uma prática com foco em:

- Manipulação de dados em memória.
- Trabalhar com **listas**, **arquivos de texto** e **estrutura de dados**.
- Implementação do **paradigma orientado a objetos (OOP)**.

## Estrutura do Projeto

O projeto está estruturado da seguinte forma:

- **SistemaEventos**: Responsável pela lógica principal do sistema, como cadastrar usuários e eventos, além de controlar a participação.
- **Usuario**: Representa o usuário do sistema, com seus dados e eventos em que está participando.
- **Evento**: Contém informações sobre os eventos cadastrados, como nome, endereço, categoria e data.
- **Repositorio**: Gerencia a leitura e escrita dos dados no arquivo.
