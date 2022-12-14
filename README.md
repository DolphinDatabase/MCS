<p align="center">
  <img src="Imagens/LogotipoMCS.png"/>
</p>

![Badge](https://img.shields.io/badge/STATUS-CONCLUÍDO-green?style=flat-square&logo=)


## Tabela de Conteúdos

 * [Descrição](#descrição)
 * [Documentação](#documentação)
 * [Pré-requisitos e como executar a aplicação](#pré-requisitos-e-como-executar-a-aplicação)
 * [Funcionalidades desenvolvidas](#funcionalidades-desenvolvidas)
 * [Ilustração das funcionalidades](#ilustração-das-funcionalidades)
 * [Demonstração](#demonstração)
 * [Manual do Usuário](#manual-do-usuário)
 * [Backlog do Produto](#backlog-do-produto)  
 * [Roadmap](#roadmap)
 * [Gráfico de Burndown](#gráfico-de-burndown)
 * [Cronograma do API](#cronograma-do-api)
 * [Tecnologias](#tecnologias)
 * [Team](#team)
 * [Licença](#licença)

## Descrição

<p align="justify">
A MCS (Management and Control System) é um sistema ERP que visa gerenciar e controlar dados, afim de reduzir custos, facilitar tomadas de decisão, otimizar o tempo de atendimento de chamados e aprimorar o solucionamento destes. 
É composta por níveis de usuários, onde o administrador terá controle sobre todas as funcionalidades existentes, dentre elas o cadastro, edição e exclusão de outros usuários; o suporte ficará responsável pelo CRUD de falhas e soluções genéricas e CRUD de equipamentos; o cliente trará o problema para o suporte e, este ficará responsável por gerenciar o chamado e resolvê-los.
A MCS (Management and Control System) trouxe de uma forma fácil e rápida o mais importante: o mapeamento gráfico de anomalias nas silhuetas.
<br/>
O Mapemamento de anomalias consiste em durante ou após uma inspeção, o suporte conseguirá fazer o upload da silhueta e adicionar as falhas (específicas do chamado) 
encontradas em formas e tamanhos diferentes para uma melhor identificação da posição e tamanho, facilitando na identificação de quantidade e quais materiais serão 
utilizados para a solução dessas falhas e também no cálculo do orçamento.
<br/>
Sua interface web facilita a gestão de dados e dá autonomia aos usuários dessa aplicação para que possam atuar com desenvoltura dentro das permissões concedidas.

## Documentação

![ALERTA](Imagens/Alerta.svg) Para acessar a documentação completa do projeto, clique [aqui](Documentação/SPRINT3/MCS_Documentação.pdf).

## Pré-requisitos e como executar a aplicação

<details><summary>Pré-requisitos</summary>
 
* Instalação [Java](https://docs.oracle.com/en/java/javase/11/install/installation-jdk-microsoft-windows-platforms.html#GUID-E3C75F92-D3B2-421D-A9BE-933C15F7CD1B)
* IDE - sugestão [Eclipse](https://www.eclipse.org/downloads/) 

</details>

<details><summary>Back-end</summary>

```bash 
# via PowerShell
$ git clone https://github.com/DolphinDatabase/MCS/tree/main

cd backend
mvn clean install
mvn spring-boot:run
cd ..
```
</details>

<details><summary>Front-end</summary>
  
```bash
# via PowerShell

cd frontend
yarn install
yarn dev
```

</details>

Com os pré-requisitos prontos,
*acesse no navegador o endereço:
http://localhost:5173*

**LOGIN:**

<details><summary>Administrador</summary>

*E-mail:* adm@email.com
 
*Senha:* 123456

</details>

![ALERTA](Imagens/Alerta.svg) 
Para acessar o manual do usuário, clique [aqui](Manual/MCS_ManualdoUsuário.pdf).

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
- [X] Editar Orçamento
- [X] Editar Data de Serviço
- [X] Editar Responsável pelo Serviço
- [X] Campo de Busca em Tabelas
- [X] Exportar Mapeamento (imagem)
- [X] Limpar Mapeamento
- [X] Adicionar Tamanho Físico por Layer

![ALERTA](Imagens/Alerta.svg) Para mais informações das funcionalidades entregues, acesse as [releases](https://github.com/DolphinDatabase/MCS/releases/tag/Sprint4).

## Ilustração das Funcionalidades

Para acessar nossos *Wireframes*, clique [aqui](https://www.figma.com/file/MM0pLqbdpFde9cqt9upsWx/MCS-Dolphin?node-id=0%3A1).

## Demonstração

Para acessar o vídeo[^1] de demonstração da aplicação em uso, clique [aqui](https://youtu.be/M91pEmLUji8):

[<img src="Imagens/MCS_Youtube.png" width="60%" height="60%">](https://youtu.be/M91pEmLUji8 "MCS vídeo Demonstração")

## Manual do Usuário

![ALERTA](Imagens/Alerta.svg) Para acessar o manual do usuário, clique [aqui](Manual/MCS_ManualdoUsuário.pdf).

## Backlog do Produto

- [X] ![EPIC](Imagens/Epic.svg) **SPRINT 1:**  Base da ferrramenta
- [X] ![STORY](Imagens/Story.svg) Cadastro de usuários
- [X] ![STORY](Imagens/Story.svg) Edição de usuários
- [X] ![STORY](Imagens/Story.svg) Exclusão de usuários
- [X] ![STORY](Imagens/Story.svg) Visualização de usuários
- [X] ![STORY](Imagens/Story.svg) Chamados
- [X] ![STORY](Imagens/Story.svg) Problemas do Chamado
- [X] ![STORY](Imagens/Story.svg) Gerenciamento do orçamento
- [X] ![STORY](Imagens/Story.svg) Soluções
- [X] ![STORY](Imagens/Story.svg) Relatório
- [X] ![EPIC](Imagens/Epic.svg) **SPRINT 2:**  Análise
- [X] ![STORY](Imagens/Story.svg) Melhorias no orçamento
- [X] ![STORY](Imagens/Story.svg) Gerenciamento de falhas/ soluções
- [X] ![STORY](Imagens/Story.svg) Gerenciamento de estoque
- [X] ![STORY](Imagens/Story.svg) Análise de dados
- [X] ![EPIC](Imagens/Epic.svg) **SPRINT 3:**  Mapeamento
- [X] ![STORY](Imagens/Story.svg) Responsável pelo chamado
- [X] ![STORY](Imagens/Story.svg) Data de serviço
- [X] ![STORY](Imagens/Story.svg) Mapeamento gráfico
- [X] ![EPIC](Imagens/Epic.svg) **SPRINT 4:**  Melhorias
- [X] ![STORY](Imagens/Story.svg) Melhorias no Mapeamento de Anomalias
- [X] ![STORY](Imagens/Story.svg) Melhorias nas Tabelas
- [X] ![STORY](Imagens/Story.svg) Melhorias no Relatório

## Roadmap

Para acessar nosso *Roadmap*, clique [aqui](https://dolphin-database.atlassian.net/jira/software/projects/MCS/boards/4/roadmap?shared=&atlOrigin=eyJpIjoiZmEyMzdjZDQyYmVjNDY4MDkzNTU4MjMxNmMxMzQyYTUiLCJwIjoiaiJ9)

## Gráfico de Burndown

Para acessar nosso *Burndown*, clique [aqui](https://docs.google.com/spreadsheets/u/4/d/1-ALcuHKl5cMg0jqN8__-haiC5UYgjIOljdFmY1txpzg/htmlview#gid=0)

## Cronograma do API
 
| Data | Evento |
| -------| --------- |
| 15/08 a 19/08 | Kick-off. |
| 28/08 a 18/09 | [Sprint 1](Sprints/SPRINT1.md). |
| 19/09 a 09/10 | [Sprint 2](Sprints/SPRINT2.md). |
| 17/10 a 06/11 | [Sprint 3](Sprints/SPRINT3.md). |
| 07/11 a 27/11 | [Sprint 4](Sprints/SPRINT4.md). |
| 08/12 às 19h | Feira de Soluções. |

## Tecnologias

<details><summary>Organização e Comunicação</summary>
  
<a href="https://dolphin-database.atlassian.net/jira/software/projects/MCS/boards/4/backlog" target="_blank">![Jira](https://img.shields.io/badge/Jira-blue?style=flat-square&logo=jira) </a>
<a href="https://slack.com/intl/pt-br/" target="_blank"><img src="https://img.shields.io/badge/-Slack-E01563?style=flat-square&logo=Slack&logoColor=white"/></a>
<a href="https://www.notion.so/pt-br" target="_blank"><img src="https://img.shields.io/badge/-Notion-000000?style=flat-square&logo=Notion&logoColor=white"/></a><br/>
<a href="https://www.figma.com" target="_blank">![Figma](https://img.shields.io/badge/Figma-lightgray?style=flat-square&logo=figma)
</a><br/>
  
</details>

<details><summary>Linguagens</summary>

<a href="https://www.java.com/pt-BR/download/help/java8_pt-br.html" target="_blank">![Java](https://img.shields.io/badge/-java-E34A86?style=flat-square&logo=java)</a>
<img width="55" height="20" src="https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white"/>
<img width="55" height="20" src="https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white"/><br/>
![SQL](https://img.shields.io/badge/SQL-blue?style=flat-square&logo=sql)
<a href="https://www.javascript.com" target="_blank">![JS](https://img.shields.io/badge/JavaScript-lightgrey?style=flat-square&logo=javascript)</a>

</details>

<details><summary>Framework</summary>

<a href="https://element-plus.org/en-US/" target="_blank">![Element+](https://img.shields.io/badge/Element+-blue?style=flat-square&logo=element+)</a>
<a href="https://br.vuejs.org/" target="_blank">![Vue](https://img.shields.io/badge/Vue-darkgreen?style=flat-square&logo=vue)</a>
<a href="https://spring.io/projects/spring-boot" target="_blank"><img src="https://img.shields.io/badge/Spring-6DB33F?style=flat-square&logo=spring&logoColor=white"/></a>

</details>


<details><summary>SGBD</summary>

<a href="https://www.oracle.com/br/downloads/">![Oracle Cloud](https://img.shields.io/badge/Oracle-red?style=flat-square&logo=oracle)</a>

</details>


## Team

<a href="https://github.com/beamedeiros" target="_blank"><img src="Imagens/Team/BeatrizMedeiros.png" height="65" width="65" align="left" target="_blank" ></a>
<a href="https://github.com/pdrMottaS" target="_blank"><img src="Imagens/Team/PedroMotta.png" height="65" width="65" align="left" target="_blank" ></a>
<a href="https://github.com/Borgarelli" target="_blank"><img src="Imagens/Team/KauãBorgarelli.png" height="65" width="65" align="left" target="_blank" ></a>
 <br/>
<br></br>
Para mais informações[^2], clique [aqui](https://github.com/DolphinDatabase/MCS/wiki/Development-Team).

## Licença  

Este projeto esta sob licença [MIT](LICENSE).<br/>

[^1]: Vídeo produzido e editado pelos integrantes do grupo.
[^2]: Equipe responsável pelo desenvolvimento do Projeto Integrador.
