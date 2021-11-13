# :lion: Leon backend

## Como executar

- **Pelo IntelliJ**

Inicie o banco de dados
```shell
docker run -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgr
es -e POSTGRES_DB=leon -p 5432:5432 -d postgres
```

Em seguida, selecione o perfil `LeonApplication` e pressione o botão de debug.

- **Pelo docker**

```shell
docker-compose up
```

Os endpoints seguirão o formato `localhost:8080/endpoint`.

Para descobrir as rotas acesse o [Swagger](https://swagger.io/) em `localhost:8080/swagger-ui`.
> **OBS.:** Por alguma razão o Swagger não está funcionando dentro do Docker.
 
