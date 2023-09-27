

<p align="center">
  <img src="https://img.shields.io/badge/Kotlin-000?&style=for-the-badge&logo=kotlin&logoColor=yellow"/>
  <img src="https://img.shields.io/badge/Spring-000?style=for-the-badge&logo=spring&logoColor=green"/>
    <img src="https://img.shields.io/badge/Docker-000?style=for-the-badge&logo=docker&logoColor=white">  
<br><img src="http://img.shields.io/static/v1?label=STATUS&message=EM%20DESENVOLVIMENTO&color=red&style=for-the-badge"/>
</p><br>


## Desafio Back-end PicPay

> O desafio da aplicação pode ser visualizado no link: https://github.com/PicPay/picpay-desafio-backend

Realizar um payload de `POST /transaction` seguindo uma lista de regras de critérios e padrões de desenvolvimento

## Resumo da aplicação

API Rest utilizando Kotlin + SpringBoot, com documentação da API realizada na interface web Swagger, capaz de gerar transações e usuários.

## ⚙️ Como configurar para teste da aplicação
### Método 1 - Utilizando Docker (recomendado) - não requer Java e Gradle instalados em sua máquina:
1. Clone este repositório: ``` git clone https://github.com/apicela/picpay_desafio_backend.git ```
2. Abra o terminal no diretório do arquivo `picpay_desafio_backend/picpay/`.
3. Execute o seguinte comando no terminal: ```docker build -t picpay_apicela . && docker run -p 8080:8080 picpay_apicela```
4. Acesse a interface pelo navegador: http://localhost:8080/swagger-ui/index.html
   <br>
### Método 2 - Utilizando Java 17+ e Gradle 6.4+
1. Clone este repositório: ``` git clone https://github.com/apicela/picpay_desafio_backend.git ```
2. Abra o terminal no diretório do arquivo `picpay_desafio_backend/picpay/`.
3. Execute o seguinte comando no terminal: ```./gradlew build && ./gradlew bootRun```
4. Acesse a interface pelo navegador: http://localhost:8080/swagger-ui/index.html
   <br>

## Visão Geral

### 👨‍💻 Status da aplicação

✔️ Documentação da API<br>
✔️  Documentação da Aplicação <br>
✔️ API do CRUD<br>
✔️ Conteinerização <br>
⏳ Testes unitários/integração <br>


### 📋 Pré-requisitos

* Java 17+ <br>
* Gradle 6.4+<br>
### 🛠️ Ferramentas utilizadas

* [SpringBoot](https://spring.io/) - O framework web utilizado
* [Docker](https://www.docker.com/) - Utilizado para build, teste e deploy da aplicação
* [SwaggerUI](https://swagger.io/tools/swagger-ui/) - Interface utilizada para documentação da API
* [Gradle](https://gradle.org/) - Gerenciador de Dependências
* [Banco de dados H2](https://www.h2database.com/html/main.html) - Utilizado para simular um banco de dados
* 
### Interface da aplicação
![image](https://github.com/apicela/picpay_desafio_backend/assets/105384228/bfc42a84-2cf8-4a51-8c27-c10f0841c270)
<br>

### Realizando um POST de uma transferência
![image](https://github.com/apicela/picpay_desafio_backend/assets/105384228/6df4e581-2a78-4ec7-91a9-abf386c74b82)
<br>

### Realizando um GET de usuários
![image](https://github.com/apicela/picpay_desafio_backend/assets/105384228/2d31e41d-5d73-4663-b386-abbfbc696eb4)


