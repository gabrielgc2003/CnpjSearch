# Documentação do Projeto: CnpjSearch

## 1. Visão Geral

**CnpjSearch** é uma aplicação desenvolvida para consultar dados de empresas a partir do CNPJ, utilizando uma API pública(https://www.cnpj.ws) e permitindo o registro de informações adicionais em um banco de dados PostgreSQL. A aplicação foi construída utilizando Spring Boot para o backend, JSF para a interface do usuário, e Docker para containerização.

## 2. Tecnologias Utilizadas

- **Backend**: Spring Boot (Java)
- **Frontend**: JSF (JavaServer Faces)
- **Banco de Dados**: PostgreSQL
- **API de Terceiros**: Retrofit para consumo da API externa de CNPJ
- **Documentação da API**: Springdoc OpenAPI (Swagger)
- **Containerização**: Docker

## 3. Arquitetura do Projeto

- O projeto segue uma arquitetura de camadas, separando as responsabilidades em:
    - **Controllers**: Responsáveis por gerenciar as requisições HTTP.
    - **Services**: Contêm a lógica de negócios.
    - **Repositories**: Realizam a persistência dos dados.
    - **DTOs**: Utilizados para transferência de dados entre as camadas.
    - **Configurações**: Configurações para Retrofit.

## 4. Configuração do Ambiente

### 4.1. Requisitos

- **Java 17** ou superior
- **Maven** para gerenciamento de dependências
- **Docker** para containerização
- **PostgreSQL** banco de dados


## 5. Instruções de Execução

### 5.1. Clonar o Repositório

```bash
git clone https://github.com/gabrielgc2003/CnpjSearch.git
cd CnpjSearch
```

### 5.2. Abra o terminal na raiz do projeto
- Garanta que seu terminal seja aberto na pasta raíz, onde esteja acessivel as pastas CnpjSearch, CnpjSearchApi e ainda todos os dockerfiles e o docker-compose.
### 5.3. Executar com Maven

```bash
cd CnpjSearch
mvn clean package
cd ../CnpjSearchApi
mvn clean package
```

### 5.4. Executar com Docker

1. **Executar o Docker**
    
    ```bash
    cd ..
    docker compose up --build    
    ```
   
    

## 6. Acessando a Documentação da API

Para acessar a página de consulta de cnpj, abra o navegador e vá para:

```bash
http://localhost:8080/CnpjSearch-1/
```


Para acessar a documentação interativa da API via Swagger, abra o navegador e vá para:

```bash
http://localhost:8081/swagger-ui.html
```

## 7. Contato
Link dos arquivos de docker no Google Drive: https://drive.google.com/drive/folders/1innt3Y223_vV03OIEwqeYLMpeSXd5mvJ?usp=sharing
Caso tenha dúvidas ou precise de mais informações sobre o projeto, sinta-se à vontade para entrar em contato.
