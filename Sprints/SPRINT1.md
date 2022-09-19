<p align="center">
  <img src="https://github.com/DolphinDatabase/MCS/blob/sprint-1/Imagens/MCS(logo).png" />
</p>

![Badge](https://img.shields.io/badge/STATUS-EM_ANDAMENTO-yellow?style=flat-square&logo=)


## Tabela de Conteúdos

 * [Descrição](#descrição)
 * [Documentação](#documentação)
 * [Funcionalidades desenvolvidas](#funcionalidades-desenvolvidas)
 * [Ilustração das funcionalidades](#ilustração-das-funcionalidades)
 * [Demonstração](#demonstração)
 * [Manual do Usuário](#manual-do-usuário)
 * [Backlog da Sprint 1](#backlog-da-sprint-1) 
 * [Story Cards](#story-cards)  
 * [User Story](#user-story)   
 * [Roadmap](#roadmap)
 * [Gráfico de Burndown](#gráfico-de-burndown)
 * [Team](#team)
 * [Licença](#licença)

## Descrição
<p align="justify">
A MCS (Management and Control System) é um sistema ERP que visa gerenciar e controlar dados, afim de reduzir custos, facilitar tomadas de decisão, otimizar o tempo de atendimento de chamados e aprimorar o solucionamento destes. 
É composta por níveis de usuários, onde o administrador terá controle sobre todas as funcionalidades existentes, dentre elas o cadastro, edição e exclusão de outros usuários; o cliente trará o problema para o suporte e, este ficará responsável por gerenciar chamados e resolvê-los.
<br/>
Sua interface web facilita a gestão de dados e dá autonomia aos usuários dessa aplicação para que possam atuar com desenvoltura dentro das permissões concedidas.
  
## Documentação
  
  <details><summary>Modelo Conceitual</summary>
  
  <img src="https://github.com/DolphinDatabase/MCS/blob/main/Documenta%C3%A7%C3%A3o/SPRINT1/Modelos_Banco/conceitual.png">
 
  </details>
  
  <details><summary>Modelo Relacional</summary>
  
  <img src="https://github.com/DolphinDatabase/MCS/blob/main/Documenta%C3%A7%C3%A3o/SPRINT1/Modelos_Banco/logico.png">
  
  </details>
  
   <details><summary>Modelo Físico</summary>
  
  ```bash
  Create table Niveis(
	nvl_id Number (6),
	nvl_niveis Varchar (10),
	constraint pk_nvl Primary key (nvl_id)
);

Create Usuario(
	user_id Number (6),
	user_senha Number (16),
	user_email Varchar (50),
	user_nome Varchar (60),
	user_nivel Number (6),
	constraint pk_usuario Primary key (user_id),
	constraint fk_user_nvl foreign key (user_nivel) references Niveis (nvl_id)
);

Create table Localizacao(
	loc_id Number (8),
	loc_cep Number (9),
	loc_cidade Varchar (60),
	loc_bairro Varchar (60),
	loc_complemento Varchar (60),
	loc_uf Varchar (60),
	loc_num Number (20),
	constraint pk_local Primary key (loc_id)
);

Create table Chamado(
	chama_id Number (9),
	chama_status Number (1) constraint ck_chama_status Check (chama_status in (0,1)),
	chama_data_inico date default System,
	chama_data_termino date default System,
	chama_descricao text,
	chama_usuario Number (6),
	chama_localizacao Number (6),
	constraint pk_chama Primary key (chama_id),
	constraint fk_chama_usuario foreign key (chama_usuario) references Usuario (user_id),
	constraint fk_chama_localizacao foreign key (chama_localizacao) references Localizacao (loc_id)
);

Create table Orcamento(
	orc_id Number (6),
	orc_desc Text,
	orc_total Number (8,2),
	orc_chama Number (8),
	contraint pk_orc Primary key (orc_id),
	constraint fk_orc_chama foreign key (orc_chama) references Chamado (chama_id)
);


Create table Problema(
	prob_id Number (6),
	prob_nome Varchar (60),
	prob_descricao Text,
	constraint pk_prob Primary key (prob_id)
);

Create table chamado/problema(
	CP_id Number (6),
	CP_chamado Number (6),
	CP_problema Number (6),
	constraint pk_CP Primary key (CP_id),
	constraint fk_CP_chamado foreign key (CP_chamado) references Chamado (chama_id),
	constraint fk_CP_problema foreign key (CP_problema) references Problema (prob_id)
);

Create table Solucao(
	soluc_id Number (6),
	soluc_nome Varchar (60),
	soluc_prob Number (6),
	constraint pk_soluc Primary key (soluc_id),
	constraint fk_soluc_prob foreign key (soluc_prob) references Problema (prob_id)
);
  ```
  
  </details>
  
![ALERTA](https://github.com/DolphinDatabase/MCS/blob/sprint-1/Imagens/Alerta.svg) 
Para acessar a documentação completa do projeto, clique [aqui](#).

## Funcionalidades desenvolvidas 

- [X] Cadastro de Usuários
- [X] Visualização de Usuários
- [X] Edição de Usuários
- [X] Exclusão de Usuários
- [X] Criação de Chamados
- [X] Adição de Problemas
- [X] Adição de Soluções
- [X] Identificação de Soluções
- [X] Gerar Orçamentos
- [X] Criar Relatórios

![ALERTA](https://github.com/DolphinDatabase/MCS/blob/sprint-1/Imagens/Alerta.svg) Para mais informações das funcionalidades entregues, acesse a [release](#).

## Ilustração das funcionalidades

Para acessar nossos *Wireframes*, clique [aqui](https://www.figma.com/file/OL07HIxLkiXBkHdiCrxey5/MCS?node-id=0%3A1).
 
## Demonstração
 
Para acessar o vídeo[^1] de demonstração da aplicação em uso, clique [aqui](https://youtu.be/omSyXxA3AYI):

[<img src="https://github.com/DolphinDatabase/MCS/blob/main/Imagens/MCS(youtube).png" width="65%" height="65%">](# "MCS vídeo Demonstração")

## Manual do Usuário

Para acessar o manual do usuário, clique [aqui](https://github.com/DolphinDatabase/MCS/blob/sprint-1/Manual/ManualUsuario_MCS.pdf).

## Backlog da Sprint 1

- [x] ![EPIC](https://github.com/DolphinDatabase/MCS/blob/sprint-1/Imagens/Epic.svg) **SPRINT 1:**  Base da ferrramenta
- [X] ![STORY](https://github.com/DolphinDatabase/MCS/blob/sprint-1/Imagens/Story.svg) Cadastro de usuários
- [X] ![STORY](https://github.com/DolphinDatabase/MCS/blob/sprint-1/Imagens/Story.svg) Edição de usuários
- [X] ![STORY](https://github.com/DolphinDatabase/MCS/blob/sprint-1/Imagens/Story.svg) Exclusão de usuários
- [X] ![STORY](https://github.com/DolphinDatabase/MCS/blob/sprint-1/Imagens/Story.svg) Visualização de usuários
- [X] ![STORY](https://github.com/DolphinDatabase/MCS/blob/sprint-1/Imagens/Story.svg) Chamados
- [X] ![STORY](https://github.com/DolphinDatabase/MCS/blob/sprint-1/Imagens/Story.svg) Problemas do Chamado
- [X] ![STORY](https://github.com/DolphinDatabase/MCS/blob/sprint-1/Imagens/Story.svg) Gerenciamento do orçamento
- [X] ![STORY](https://github.com/DolphinDatabase/MCS/blob/sprint-1/Imagens/Story.svg) Soluções
- [X] ![STORY](https://github.com/DolphinDatabase/MCS/blob/sprint-1/Imagens/Story.svg) Relatório

## Story Cards

<p align="center">
  <img src="https://github.com/DolphinDatabase/MCS/blob/sprint-1/Documenta%C3%A7%C3%A3o/SPRINT1/StoryCards/StoryCards1.gif"/>
</p>

## User Story
  
<details><summary>Story 1 - Cadastro de Usuários</summary>

| Como | Eu quero | Para que |
| ------- | ------- | ------- |
| Usuário administrador do sistema. | Cadastrar usuários. | Seja possivel identificar o seu nível e o que faz. |
  
</details>

<details><summary>Story 2 - Edição de Usuários</summary>
  
| Como | Eu quero | Para que |
| ------- | ------- | ------- |
| Usuário dministrador do sistema. | Editar usuários já cadastrados. | Seja possível atualizar seus dados. |
  
</details>


<details><summary>Story 3 - Exclusão de Usuário</summary>
  
| Como | Eu quero | Para que |
| ------- | ------- | ------- |
| Usuário administrador do sistema. | Excluir usuários. | Exista a possibilidade de retirar do sistema usuários que não fazem mais uso da ferramenta. |
  
</details>


<details><summary>Story 4 - Visualização de Usuários</summary>
  
| Como | Eu quero | Para que |
| ------- | ------- | ------- |
| Usuário administrador do sistema. | Visualizar os usuários. | haja um gerenciamento. |
  
</details>

<details><summary>Story 5 - Chamados</summary>
  
| Como | Eu quero | Para que |
| ------- | ------- | ------- |
| Usuário do sistema. | Adicionar um novo chamado. | Seja possível dar andamento às necessidades dos clientes. |
  
</details>

<details><summary>Story 6 - Problemas do Chamado</summary>
  
| Como | Eu quero | Para que |
| ------- | ------- | ------- |
| Usuário suporte/ administrador do sistema. | Adicionar problemas encontrados em um chamado. | Seja possível identificar soluções. |
  
</details>

<details><summary>Story 7 - Gerenciamento de Orçamento</summary>
  
| Como | Eu quero | Para que |
| ------- | ------- | ------- |
| Usuário financeiro do sistema. | Criar um orçamento baseado nos chamados e seus requisitos. | O orçamento possa ser encaminhado para o cliente. |
  
</details>


<details><summary>Story 8 - Soluções</summary>
  
| Como | Eu quero | Para que |
| ------- | ------- | ------- |
| Usuário suporte/ administrador do sistema. | Adicionar soluções para problemas. | As soluções de prolemas conhecidos fiquem salvas. |

</details>

<details><summary>Story 9 - Relatório</summary>
  
| Como | Eu quero | Para que |
| ------- | ------- | ------- |
| Usuário suporte/ administrador do sistema. | Poder registrar soluções para os problemas encontrados durante os chamados. | Seja possível facilitar e agilizar possíveis futuros chamados. |
  
</details>

## Roadmap

Para acessar nosso *Roadmap*, clique [aqui](https://dolphin-database.atlassian.net/jira/software/projects/MCS/boards/4/roadmap?shared=&atlOrigin=eyJpIjoiZmEyMzdjZDQyYmVjNDY4MDkzNTU4MjMxNmMxMzQyYTUiLCJwIjoiaiJ9).

## Gráfico de Burndown

Para acessar nosso *Burndown*, clique [aqui](https://dolphin-database.atlassian.net/jira/software/projects/MCS/boards/4/reports/burndown).
 
## Team

<a href="https://github.com/beamedeiros" target="_blank"><img src="https://github.com/DolphinDatabase/MCS/blob/sprint-1/Imagens/Team/BeatrizMedeiros.png" height="65" width="65" align="left" target="_blank" ></a>
<a href="https://github.com/pdrMottaS" target="_blank"><img src="https://github.com/DolphinDatabase/MCS/blob/sprint-1/Imagens/Team/PedroMotta.png" height="65" width="65" align="left" target="_blank" ></a>
<a href="https://github.com/Borgarelli" target="_blank"><img src="https://github.com/DolphinDatabase/MCS/blob/sprint-1/Imagens/Team/Kau%C3%A3Borgarelli.png" height="65" width="65" align="left" target="_blank" ></a>
 <br/>
<br></br>
Para mais informações[^2], clique [aqui](https://github.com/DolphinDatabase/MCS/wiki/Development-Team).


## Licença  

Este projeto esta sob licença [MIT](https://github.com/DolphinDatabase/MCS/blob/sprint-1/LICENSE).<br/>

[^1]: Vídeo produzido e editado pelos integrantes do grupo.
[^2]: Equipe responsável pelo desenvolvimento do Projeto Integrador.
