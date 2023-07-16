# Processo Seletivo Java

Este projeto é uma aplicação Api Rest feita com Spring Boot, que lista os usuários e filtrar suas transações bancárias. O objetivo principal é fornecer as transações e as filtrar.

## Instalação

<details>
    <summary>
        <b>
            Iniciando o projeto com Maven
        </b>
    </summary>
    <br>

1. Clonando o Projeto do GitHub

```bash
  git clone git@github.com:Pedro0505/PS-Java-React.git

  cd PS-Java-React-Front
```

1. Clonando o Projeto do GitHub

```bash
  git clone git@github.com:Pedro0505/PS-Java-React.git

  cd PS-Java-React-Front
```

2. Fazendo o build do projeto

```bash
  # Para linux
  ./mvnw clean package

  # Para windows
  .\mvnw clean package
```

3. Iniciando o projeto

```bash
  java -jar <...caminhoParaSeuJar>
```

<br>

</details>

<details>
    <summary>
        <b>
            Iniciando o projeto com Docker Compose 🐳
        </b>
    </summary>
    <br>

  ***⚠️ Para garantir um bom funcionamento é necessário que tenha instalado o docker e o docker-compose nas versões 20.10.16 e 1.29 ou superior respectivamente***
    
<br>

1. Clonando o Projeto do GitHub

```bash
  git clone git@github.com:Pedro0505/PS-Java-React.git

  cd PS-Java-React-Front
```

2. Suba os containers

```bash
  docker-compose -f docker-compose.dev.yml up --build -d
```

3. Quando o processo dos containers estiver acabado acesse a aplicação usando o seguinte endereço

```bash
  http://localhost:3000
```

4. Para derrubar os containers

```bash
  docker-compose -f docker-compose.dev.yml down --rmi all --volumes --remove-orphans
```

<br>

</details>

## Query Strings das Rotas do Projeto

### Rota: `/transferencias`

Esta rota lista todas as transações bancárias disponíveis, permitindo a filtragem por diferentes critérios.

- **Query Strings:**

  - `initialDate` (Data Inicial - Opcional): Permite definir a data de início do período de filtragem. As transações exibidas serão aquelas realizadas após essa data.

  - `finalDate` (Data Final - Opcional): Permite definir a data de término do período de filtragem. As transações exibidas serão aquelas realizadas antes dessa data.

  - `operatorName` (Nome do Operador - Opcional): Permite filtrar as transações pelo nome da pessoa que realizou a operação. Apenas as transações feitas pelo operador com esse nome serão exibidas.

  - `accountId` (ID da Conta - Obrigatório): Permite filtrar as transações associadas a uma conta específica. Apenas as transações relacionadas ao ID da conta informado serão exibidas.

### Observações:

1. Se a `initialDate` for fornecida, a `finalDate` também deve ser fornecida. Além disso, a `initialDate` não pode ser maior que a `finalDate`.

2. O filtro por `accountId` é obrigatório e deve ser informado para realizar a filtragem.

### Exemplos de Uso:

1. Rota: `http://localhost:8080/transferencias?initialDate=2018-01-01&finalDate=2023-07-15&operatorName=Beltrano&accountId=1`

   Descrição: Esta rota filtra as transações bancárias com as seguintes condições:
   - Data de início: 1º de janeiro de 2018.
   - Data de término: 15 de julho de 2023.
   - Nome do operador: Beltrano.

   O retorno corresponderá ao período entre a data de início e a data de término (inclusive) para a conta específica associada ao `accountId` fornecido.

2. Rota: `http://localhost:8080/transferencias?initialDate=2018-01-01&finalDate=2023-07-15&accountId=1`

   Descrição: Esta rota filtra as transações bancárias com as seguintes condições:
   - Data de início: 1º de janeiro de 2018.
   - Data de término: 15 de julho de 2023.
   - ID da conta: 1.

   O retorno corresponderá ao período entre a data de início e a data de término (inclusive) e estará associada ao `accountId`.

3. Rota: `http://localhost:8080/transferencias?operatorName=Beltrano&accountId=1`

   Descrição: Esta rota filtra as transações bancárias realizadas por Beltrano e associada ao `accountId`. Não há restrição de datas.

4. Rota: `http://localhost:8080/transferencias?accountId=1`

   Descrição: Esta rota lista todas as transações associadas ao `accountId`. Não há restrição de datas ou operador. O `accountId` é obrigatório.

## Stack utilizada

- Java
- Spring Boot
- Hibernate
- Jpa
- Junit
- Maven
