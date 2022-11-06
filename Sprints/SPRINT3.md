<p align="center">
  <img src="https://github.com/DolphinDatabase/MCS/blob/sprint-2/Imagens/LogoMCS.png"/>
</p>

![Badge](https://img.shields.io/badge/STATUS-EM_ANDAMENTO-yellow?style=flat-square&logo=)


## Tabela de Conteúdos

 * [Descrição](#descrição)
 * [Documentação](#documentação)
 * [Funcionalidades desenvolvidas](#funcionalidades-desenvolvidas)
 * [Ilustração das funcionalidades](#ilustração-das-funcionalidades)
 * [Demonstração](#demonstração)
 * [Manual do Usuário](#manual-do-usuário)
 * [Backlog da Sprint 3](#backlog-da-sprint-3) 
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
  
  <img src="https://github.com/DolphinDatabase/MCS/blob/sprint-3/Documenta%C3%A7%C3%A3o/SPRINT3/Modelos_Banco/Conceitual.png">
 
  </details>
  
  <details><summary>Modelo Relacional</summary>
  
  <img src="https://github.com/DolphinDatabase/MCS/blob/sprint-3/Documenta%C3%A7%C3%A3o/SPRINT3/Modelos_Banco/L%C3%B3gico.png">
  
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
	user_cpf Varchar (12),
	user_cnpj Varchar (15),
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
	loc_ende Varchar (50),
	constraint pk_local Primary key (loc_id)
);

Create table Equipamento(
	equip_id Number (6),
	equip_num Varchar (12),
	equip_modelo Varchar (50),
	equip_desc Text,
	constraint pk_equip Primary Key (equip_id),
	constraint uk_num Unique Key (equip_num)
);

Create table Chamado(
	chama_id Number (9),
	chama_status Number (1) constraint ck_chama_status Check (chama_status in (0,1)),
	chama_data_inico date default System,
	chama_data_termino date default System,
	chama_descricao text,
	chama_usuario Number (6),
	chama_localizacao Number (6),
	chama_equip Number (6),
	constraint pk_chama Primary key (chama_id),
	constraint fk_chama_usuario foreign key (chama_usuario) references Usuario (user_id),
	constraint fk_chama_localizacao foreign key (chama_localizacao) references Localizacao (loc_id),
	constraint fk_chama_equip foreign key (chama_equip) references Equipamento (equip_id)
);

Create table Mapeamento(
	map_id Number (9),
	map_nome Varchar (160),
	map_link Varchar (200),
	constraint pk_map Primary key (map_id)
);


Create table Layer(
	layer_id Number (6),
 	layer_x Bigint,
	layer_y Bigint,
	layer_color varchar (60),
	layer_chama Number (9),
	layer_map Number (9),
	layer_size Varchar (250),
	constraint pk_layer Primary key (layer_id),
	constraint fk_layer_chama Foreign key (layer_chama) references Chamado (chama_id),
	constraint fk_layer_map Foreign key (layer_map) references Mapeamento (map_id)
	
);

Create table Orcamento(
	orc_id Number (6),
	orc_obs Text,
	orc_valor Number (8,2),
	orc_chama Number (8),
	contraint pk_orc Primary key (orc_id),
	constraint fk_orc_chama foreign key (orc_chama) references Chamado (chama_id)
);

Create table Falhas(
	falha_id Number (6),
	falha_nome Varchar (60),
	falha_nivel Varchar constraint ck_falha_nivel Check (falha_nivel in('Baixa', 'Média', 'Alta')),
	falha_cor Varchar (30)
	constraint pk_falha Primary key (falha_id)
);

Create table chamado/falha(
	CF_id Number (6),
	CF_chamado Number (6),
	CF_medidas Varchar (80),
	CF_problema Number (6),
	constraint pk_CF Primary key (CF_id),
	constraint fk_CF_chamado foreign key (CF_chamado) references Chamado (chama_id),
	constraint fk_CF_problema foreign key (CF_problema) references Problema (prob_id)
);

Create table Solucao(
	soluc_id Number (6),
	soluc_nome Varchar (60),
	constraint pk_soluc Primary key (soluc_id),
);

Create table falhas/solucoes(
	FS_id Number (6),
	FS_falha_id Number (6),
	FS_soluc_id Number (6),
	FS_falha Number (20),
	FS_layer_id Number (6),
	FS_desc Text,
	FS_prioridade Varchar (10)
constraint ck_FS_prioridade Check (FS_prioridade in ('Baixa', 'Média', 'Alta')),
	FS_data date default System,
	constraint pk_FS Primary key (FS_id),
	constraint fk_FS_falha_id foreign key (FS_falha_id) references Falhas (falha_id),
	constraint fk_FS_soluc_id foreign key (FS_soluc_id) references Solucoes (soluc_id),
	constraint fk_FS_layer foreign key (FS_layer_id) references Layer
);

 ```
  
  </details>
  
![ALERTA](https://github.com/DolphinDatabase/MCS/blob/sprint-3/Imagens/Alerta.svg) 
Para acessar a documentação completa do projeto, clique [aqui](https://github.com/DolphinDatabase/MCS/blob/sprint-3/Documenta%C3%A7%C3%A3o/SPRINT3/Documenta%C3%A7%C3%A3o_MCS.pdf).

## Funcionalidades desenvolvidas 

- [X] Cadastro de Usuários
- [X] Visualização de Usuários
- [X] Edição de Usuários
- [X] Exclusão de Usuários
- [X] Cadastro de Equipamentos
- [X] Visualização de Equipamentos
- [X] Edição de Equipamentos
- [X] Exclusão de Equipamentos
- [X] Cadastro de Falhas e Soluções
- [X] Visualização de Falhas e Soluções
- [X] Exclusão de Falhas e Soluções
- [X] Criação de Chamados
- [X] Adição de Falhas e Soluções no Chamado
- [X] Adição de Equipamentos no Chamado
- [X] Adição de Orçamento no Chamado
- [X] Criação de Orçamento
- [X] Adição de Responsável pelo Serviço
- [X] Adição de Data de Serviço
- [X] Upload de Imagem (Silhueta)
- [X] Adição de Silhuetas Já Conhecidas
- [X] Adição de Anomalias nas Silhuetas
- [X] Visualização de Anomalias nas Silhuetas


![ALERTA](https://github.com/DolphinDatabase/MCS/blob/sprint-3/Imagens/Alerta.svg) Para mais informações das funcionalidades entregues, acesse a [release](https://github.com/DolphinDatabase/MCS/releases/tag/Sprint3).

## Ilustração das funcionalidades

Para acessar nossos *Wireframes*, clique [aqui](https://www.figma.com/file/MM0pLqbdpFde9cqt9upsWx/MCS-Dolphin?node-id=0%3A1).
 
## Demonstração
 
Para acessar o vídeo[^1] de demonstração da aplicação em uso, clique [aqui](https://youtu.be/omSyXxA3AYI):

[<img src="https://github.com/DolphinDatabase/MCS/blob/sprint-3/Imagens/MCS_Youtube.png" width="65%" height="65%">](# "MCS vídeo Demonstração")

## Manual do Usuário

Para acessar o manual do usuário, clique [aqui](https://github.com/DolphinDatabase/MCS/blob/sprint-3/Manual/MCS_ManualdoUsu%C3%A1rio.pdf).

## Backlog da Sprint 3

- [x] ![EPIC](https://github.com/DolphinDatabase/MCS/blob/sprint-3/Imagens/Epic.svg) **SPRINT 3:**  Mapeamento
- [X] ![STORY](https://github.com/DolphinDatabase/MCS/blob/sprint-3/Imagens/Story.svg) Responsável pelo Serviço
- [X] ![STORY](https://github.com/DolphinDatabase/MCS/blob/sprint-3/Imagens/Story.svg) Data de Serviço
- [X] ![STORY](https://github.com/DolphinDatabase/MCS/blob/sprint-3/Imagens/Story.svg) Mapeamento Gráfico de Falhas
	
## Story Cards

<p align="center">
  <img src="https://github.com/DolphinDatabase/MCS/blob/sprint-3/Documenta%C3%A7%C3%A3o/SPRINT3/StoryCards/StoryCards.gif"/>
</p>

## User Story

<details><summary>Story 14 - Responsável pelo Serviço</summary>
  
| Como | Eu quero | Para que |
| ------- | ------- | ------- |
| Usuário administrador e suporte do sistema. | Atribuir um responsável para a execução de serviço. | O cliente tenha ciência de quem realizará o serviço. |
  
</details>


<details><summary>Story 15 - Data de Serviço</summary>
  
| Como | Eu quero | Para que |
| ------- | ------- | ------- |
| Usuário administrador e suporte do sistema. | Adicionar a data de execução do serviço. | Além do controle da agenda de serviço, o cliente fique ciente de quando será realizado. |
  
</details>


<details><summary>Story 16 - Mapeamento Gráfico de Falhas</summary>
  
| Como | Eu quero | Para que |
| ------- | ------- | ------- |
| Usuário suporte do sistema. | Mapear falhas de forma gráfica. | O cliente possa vizualizá-las. |
  
</details>

## Roadmap

Para acessar nosso *Roadmap*, clique [aqui](https://dolphin-database.atlassian.net/jira/software/projects/MCS/boards/4/roadmap?shared=&atlOrigin=eyJpIjoiZmEyMzdjZDQyYmVjNDY4MDkzNTU4MjMxNmMxMzQyYTUiLCJwIjoiaiJ9).

## Gráfico de Burndown

Para acessar nosso *Burndown*, clique [aqui](https://dolphin-database.atlassian.net/jira/software/projects/MCS/boards/4/reports/burndown).
 
## Team

<a href="https://github.com/beamedeiros" target="_blank"><img src="https://github.com/DolphinDatabase/MCS/blob/sprint-2/Imagens/Team/BeatrizMedeiros.png" height="65" width="65" align="left" target="_blank" ></a>
<a href="https://github.com/pdrMottaS" target="_blank"><img src="https://github.com/DolphinDatabase/MCS/blob/sprint-2/Imagens/Team/PedroMotta.png" height="65" width="65" align="left" target="_blank" ></a>
<a href="https://github.com/Borgarelli" target="_blank"><img src="https://github.com/DolphinDatabase/MCS/blob/sprint-2/Imagens/Team/Kau%C3%A3Borgarelli.png" height="65" width="65" align="left" target="_blank" ></a>
 <br/>
<br></br>
Para mais informações[^2], clique [aqui](https://github.com/DolphinDatabase/MCS/wiki/Development-Team).


## Licença  

Este projeto esta sob licença [MIT](https://github.com/DolphinDatabase/MCS/blob/main/LICENSE).<br/>

[^1]: Vídeo produzido e editado pelos integrantes do grupo.
[^2]: Equipe responsável pelo desenvolvimento do Projeto Integrador.
