# Resgate Animal - Backend
## O Projeto
O projeto Resgate Animal está sendo desenvolvido para ajudar a causa animal em relação ao abandono e maus-tratos de animais de todo tipo, raça e porte.
A proposta deste projeto é desenvolver APIs para que sejam integradas e realizada toda a manipulação de dados necessária para se gerar uma denúncia aos meios responsáveis pelo resgate e cuidado destes animais.

## Modelagem do Projeto
Este projeto já possui alguns diagramas desenhados para sua concepção durante os últimos semestre do curso de Analise e Desenvolvimento de Sistemas, onde os desenvolvi para o trabalho de conclusão de curso.
Os diagramas são: 
+ Caso de Uso
+ Entidade Relacionamento
+ Classes

Para acessar os modelos basta seguir até a pasta **resources/static/diagram**.

## Pré Requisitos
> [!IMPORTANT]
> Para executar este projeto é necessário algumas configurações:
> + Possuir  o [JDK 17](https://download.oracle.com/java/17/latest/jdk-17_windows-x64_bin.exe) instalado.
> + É necessario possuir uma [IDE Java](https://spring.io/tools) de sua preferência. 
> + Ter um banco de dados [PostgreSQL](https://www.postgresql.org/download/) instalado em sua máquina e devidamente configurado no properties do projeto.
> + Subir uma imagem do RabbitMQ via Docker e realizar a configuração dentro do application.properties no projeto.

## Opcionais
> [!NOTE]
> + Instalar o SonarQube localmente e configurar dentro do sonar-project.properties, sendo possível utilizar sem configurações com o plugin SonarLint pela IDE IntelliJ. 

## Etapas do Projeto
 - ✅ Estruturação do projeto com banco de dados Postgres
 - ✅ Inclusao dos diagramas do projeto
 - ✅ Configuração e desenvolvimento dos scripts iniciais do projeto
 - ✅ Implementação do SonarQube
 - ✅ Implementação das Entities
 - ✅ Implementação dos Repositories
 - ✅ Estruturação dos Services
 - ✅ Estruturação dos Controllers
 - 🚧 Implementação dos metodos dos Services 
 - 🚧 Implementação dos metodos dos Controllers 
 - ✅ Implementação do serviço de Mensageria - RabbitMQ
 - 🚧 Testes das APIs via Postman
 - 🚧 Implementação de testes unitários utilizando JUnit e Mockito
 - 📋 Implementação de CORS e Security
