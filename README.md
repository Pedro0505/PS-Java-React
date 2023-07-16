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

## Stack utilizada

- Java
- Spring Boot
- Hibernate
- Jpa
- Junit
- Maven
