# BarbeariaSistema

Sistema desktop para gerenciamento e agendamento de uma barbearia, desenvolvido como projeto acadêmico.

> **Status:** versão 1.0 (versão original). Uma versão com melhorias de usabilidade está em desenvolvimento.

## Funcionalidades
- Cadastro de clientes, barbeiros e serviços
- Agendamento de atendimentos
- Agenda do dia
- Status dos agendamentos

## Telas
<img width="491" height="323" alt="Captura de tela de 2026-10-29 16-15-11" src="https://github.com/user-attachments/assets/740f1076-d92e-4f30-95f7-1075bbcfe9a4" />
<img width="476" height="321" alt="Captura de tela de 2026-10-29 16-14-43" src="https://github.com/user-attachments/assets/efead7a3-c4a6-4ce7-b9fc-e2a4273f8b87" />
<img width="476" height="321" alt="Captura de tela de 2026-10-29 16-14-24" src="https://github.com/user-attachments/assets/5e093033-2eac-48ef-9169-ea3e053effbb" />


## Tecnologias
- Java 17
- Java Swing (interface)
- JDBC
- MySQL (banco local)
- NetBeans
- Arquitetura em camadas: Model / DAO / View

## Como executar
1. Instale o Java 17 e o MySQL.
2. Crie o banco (o script cria o banco `barbearia` e as tabelas, sem dados iniciais): `mysql -u root -p < database/barbearia.sql`
   > **Atenção:** o script recria as tabelas. Se rodar de novo, todos os dados cadastrados serão apagados. Use só na primeira instalação.
3. Configure usuário e senha do MySQL.
4. Abra o projeto no NetBeans e execute.
-Como o banco começa vazio, o primeiro passo depois de entrar no sistema é cadastrar clientes, barbeiros e serviços.

## Limitações conhecidas desta versão
- **Uso local:** o sistema funciona como aplicação desktop com banco MySQL local, então só roda no computador onde o banco está instalado. Não há acesso por outros dispositivos.
- **Sem versão web ou mobile:** o uso é restrito ao desktop.
- **Usabilidade:** a interface original foi analisada no Projeto Integrado da faculdade e há pontos a melhorar. As melhorias estão planejadas para as próximas versões deste repositório.

## Autor
Gustavo Novellini: [LinkedIn](https://www.linkedin.com/in/gustavo-novellini-665a74264)
