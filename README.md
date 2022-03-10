# Desafio LuizaLabs
### Bruno Yoshikazu Shimada (<https://www.linkedin.com/in/brunoyshimada>)
Para a resolução do desafio criei um app simples usando Spring Boot (v2.6.4) com o java na versão 17

## Setup
Criei todo o código usando a IDE IntelliJ, fiz o teste e foi possível clonar o repositório e rodar a aplicação de dentro da IDE.
Também é possível executar pelo gradle diretamente `./gradlew bootRun`


Para economizar no tamanho do repositório não commitei o arquivo depois do build mas se for preciso através desse comando é possível build o projeto `./gradlew clean test build`
```
To honour the JVM settings for this build a single-use Daemon process will be forked. See https://docs.gradle.org/7.2/userguide/gradle_daemon.html#sec:disabling_the_daemon.
Daemon will be stopped at the end of the build 

> Task :compileJava
/home/brunoshimada/Desktop/Estudos/FIAP/mglu/src/main/java/com/shimada/luizalabs/digitalmaps/config/exceptions/BusinessException.java:12: warning: non-varargs call of varargs method with inexact argument type for last parameter;
        super(MessageFormat.format(message, args));
                                            ^
  cast to Object for a varargs call
  cast to Object[] for a non-varargs call and to suppress this warning
Note: /home/brunoshimada/Desktop/Estudos/FIAP/mglu/src/main/java/com/shimada/luizalabs/digitalmaps/domain/pontointeresse/dao/PontoInteresseDao.java uses or overrides a deprecated API.
Note: Recompile with -Xlint:deprecation for details.
1 warning

> Task :test
2022-03-10 01:15:59.774  INFO 124011 --- [ionShutdownHook] j.LocalContainerEntityManagerFactoryBean : Closing JPA EntityManagerFactory for persistence unit 'default'

BUILD SUCCESSFUL in 11s
8 actionable tasks: 8 executed
```
Execução do build acima

Os dois comandos executando diretamente da raiz do projeto.

O app vai subir na porta `8080`

## Documentação

Para documentar a API fiz uso do [springdoc-openapi(v1.6.6)](https://springdoc.org/), optei por essa lib porque tive alguns problemas em usar o Springfox.
Para acessar a documentação basta acesar por <http://localhost:8080/swagger-ui/index.html>, na autorização o usuário é `bruno` e a senha `128`.

Deixei na raiz do projeto também uma collection do Postman como alternativa para testar as API's.

## Solução desenvolvida
Para atender os requisitos foram criados 3 endpoints:

### 1 - Criar novos pontos de interesse
Aceita uma requisição POST com os dados necessários para criar um novo ponto de interesse. A API valida a consistência dos horários se eles forem informados

### 2 - Listar todos os pontos de interesse
Listar todos os pontos de interesse sem filtros

### 3 - Buscar pontos de interesse a partir de uma localização
Lista todos os pontos que estiverem dentro do raio passado por parâmetro e informa se o ponto está aberto ou fechado

