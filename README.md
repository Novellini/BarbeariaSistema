# BarbeariaSistema

Aplicação desktop Java Swing para organizar a agenda e os atendimentos de uma barbearia. O projeto foi iniciado em um semestre anterior e ganhou uma interface nova, relatórios e controle de horários pela duração dos serviços.

A [versão web](https://github.com/Novellini/barbearia-web) fica em um repositório separado e pode usar o mesmo banco MySQL.

## Funcionalidades

- Cadastro e busca de clientes, barbeiros e serviços.
- Resumo do dia e agenda com confirmação, conclusão e cancelamento de atendimentos.
- Agendamentos com mais de um serviço. A agenda soma as durações, verifica se o atendimento cabe no horário e libera a reserva quando ele é cancelado.
- Registro de pagamentos presenciais por dinheiro, Pix e cartão.
- Relatórios por período com movimento diário, recebimentos, ranking de barbeiros e serviços mais vendidos.

## Como executar

1. Instale o **JDK 21**, o **Apache NetBeans** e o **MySQL 8**.
2. Na primeira instalação, importe o banco com `mysql -u root -p < database/barbearia.sql`. Esse arquivo recria as tabelas. Se você já tem um banco da versão anterior, aplique [a atualização de duração](database/atualizar_duracao_agendamentos.sql) uma vez, com os dois aplicativos fechados.
3. Coloque o MySQL Connector/J 9.7.0 em `lib/mysql-connector-j-9.7.0.jar`, a partir da raiz do projeto.
4. Copie `config/database.properties.exemplo` para `config/database.properties` e preencha a senha do MySQL. Se precisar, ajuste a URL e o usuário. O aplicativo também aceita `DB_URL`, `DB_USER` e `DB_PASSWORD` como variáveis de ambiente.
5. Abra esta pasta como projeto no NetBeans. Use **Clean and Build** e depois **Run Project**. Na instalação local, o login inicial é `admin` / `admin`.

O banco começa vazio. Cadastre um cliente, um barbeiro ativo e um serviço ativo antes de testar o primeiro agendamento.

## Organização

- `src/barbearia/view` contém as telas Swing e os arquivos `.form` editáveis na aba **Design** do NetBeans.
- `src/barbearia/model` contém as classes dos dados cadastrados.
- `src/barbearia/dao` contém as consultas e gravações JDBC.
- `src/barbearia/service` contém as regras de disponibilidade da agenda.
- `database` contém o esquema inicial e a atualização para instalações anteriores.

## Autor

[Gustavo Novellini](https://www.linkedin.com/in/gustavo-novellini-665a74264)
